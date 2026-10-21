/*
 * Copyright 2025 Firefly Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.fireflysoftware.rails.service;

import io.fireflysoftware.rails.domain.RailType;
import io.fireflysoftware.rails.exceptions.RailException;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

class AbstractRailServiceTest {
    
    private TestRailService railService;
    private MeterRegistry meterRegistry;
    
    @BeforeEach
    void setUp() {
        meterRegistry = new SimpleMeterRegistry();
        railService = new TestRailService(RailType.ACH, meterRegistry);
    }
    
    @Test
    void testGetRailType() {
        assertThat(railService.getRailType()).isEqualTo(RailType.ACH);
    }
    
    @Test
    void testGetLogger() {
        assertThat(railService.getLogger()).isNotNull();
    }
    
    @Test
    void testGetMeterRegistry() {
        assertThat(railService.getMeterRegistry()).isEqualTo(meterRegistry);
    }
    
    @Test
    void testExecuteWithResilienceSuccess() {
        Mono<String> result = railService.testExecuteWithResilience("testOp", 
            () -> Mono.just("success"));
        
        StepVerifier.create(result)
            .expectNext("success")
            .verifyComplete();
        
        // Verify metrics were recorded
        Counter successCounter = meterRegistry.find("rail.operation.success")
            .tag("rail", "ACH")
            .tag("operation", "testOp")
            .counter();
        
        assertThat(successCounter).isNotNull();
        assertThat(successCounter.count()).isEqualTo(1.0);
    }
    
    @Test
    void testExecuteWithResilienceError() {
        Mono<String> result = railService.testExecuteWithResilience("testOp",
            () -> Mono.error(new RuntimeException("Test error")));
        
        StepVerifier.create(result)
            .expectErrorMatches(throwable -> 
                throwable instanceof RailException &&
                throwable.getMessage().contains("Rail operation failed"))
            .verify();
        
        // Verify error metrics were recorded
        Counter errorCounter = meterRegistry.find("rail.operation.error")
            .tag("rail", "ACH")
            .tag("operation", "testOp")
            .counter();
        
        assertThat(errorCounter).isNotNull();
        assertThat(errorCounter.count()).isEqualTo(1.0);
    }
    
    @Test
    void testExecuteFluxWithResilienceSuccess() {
        Flux<String> result = railService.testExecuteFluxWithResilience("testFluxOp",
            () -> Flux.just("one", "two", "three"));
        
        StepVerifier.create(result)
            .expectNext("one", "two", "three")
            .verifyComplete();
        
        // Verify metrics
        Counter successCounter = meterRegistry.find("rail.operation.success")
            .tag("rail", "ACH")
            .tag("operation", "testFluxOp")
            .counter();
        
        assertThat(successCounter).isNotNull();
        assertThat(successCounter.count()).isEqualTo(1.0);
    }
    
    @Test
    void testExecuteFluxWithResilienceError() {
        Flux<String> result = railService.testExecuteFluxWithResilience("testFluxOp",
            () -> Flux.error(new RuntimeException("Test error")));
        
        StepVerifier.create(result)
            .expectErrorMatches(throwable -> 
                throwable instanceof RailException &&
                throwable.getMessage().contains("Rail operation failed"))
            .verify();
    }
    
    @Test
    void testMapExceptionRailException() {
        RailException original = new RailException("Original");
        Throwable mapped = railService.testMapException(original);
        
        assertThat(mapped).isSameAs(original);
    }
    
    @Test
    void testMapExceptionOtherException() {
        RuntimeException original = new RuntimeException("Other error");
        Throwable mapped = railService.testMapException(original);
        
        assertThat(mapped).isInstanceOf(RailException.class);
        assertThat(mapped.getMessage()).contains("Rail operation failed");
        assertThat(mapped.getCause()).isSameAs(original);
    }
    
    @Test
    void testRecordMetric() {
        railService.testRecordMetric("test.metric", 42.0, "key1", "value1");
        
        // Note: Gauge metrics don't work the same way in SimpleMeterRegistry
        // This test verifies the method executes without errors
    }
    
    @Test
    void testLogOperation() {
        // Test that logging operations don't throw exceptions
        railService.testLogOperation("INFO", "testOp", "Test message");
        railService.testLogOperation("WARN", "testOp", "Test warning");
        railService.testLogOperation("ERROR", "testOp", "Test error");
        railService.testLogOperation("DEBUG", "testOp", "Test debug");
    }
    
    @Test
    void testTimerMetrics() {
        Mono<String> result = railService.testExecuteWithResilience("timedOp",
            () -> Mono.just("timed"));
        
        StepVerifier.create(result)
            .expectNext("timed")
            .verifyComplete();
        
        // Verify timer was recorded
        Timer timer = meterRegistry.find("rail.operation")
            .tag("rail", "ACH")
            .tag("operation", "timedOp")
            .tag("status", "success")
            .timer();
        
        assertThat(timer).isNotNull();
        assertThat(timer.count()).isEqualTo(1L);
    }
    
    /**
     * Test implementation of AbstractRailService
     */
    static class TestRailService extends AbstractRailService {
        
        public TestRailService(RailType railType, MeterRegistry meterRegistry) {
            super(railType, meterRegistry);
        }
        
        // Expose protected methods for testing
        public <T> Mono<T> testExecuteWithResilience(String operationName, 
                java.util.function.Supplier<Mono<T>> operation) {
            return executeWithResilience(operationName, operation);
        }
        
        public <T> Flux<T> testExecuteFluxWithResilience(String operationName,
                java.util.function.Supplier<Flux<T>> operation) {
            return executeFluxWithResilience(operationName, operation);
        }
        
        public Throwable testMapException(Throwable throwable) {
            return mapException(throwable);
        }
        
        public void testRecordMetric(String metricName, double value, String... tags) {
            recordMetric(metricName, value, tags);
        }
        
        public void testLogOperation(String level, String operation, String message) {
            logOperation(level, operation, message);
        }
    }
}
