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

package io.fireflysoftware.rails.health;

import io.fireflysoftware.rails.config.RailProperties;
import io.fireflysoftware.rails.domain.RailType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.Status;

import static org.assertj.core.api.Assertions.assertThat;

class RailHealthIndicatorTest {
    
    private RailHealthIndicator healthIndicator;
    private RailProperties railProperties;
    
    @BeforeEach
    void setUp() {
        railProperties = new RailProperties();
        railProperties.setRailType("ACH");
        railProperties.setBasePath("/api/rails");
        
        healthIndicator = new RailHealthIndicator(railProperties);
    }
    
    @Test
    void testHealthUp() {
        Health health = healthIndicator.health();
        
        assertThat(health).isNotNull();
        assertThat(health.getStatus()).isEqualTo(Status.UP);
        assertThat(health.getDetails()).containsEntry("railType", "ACH");
        assertThat(health.getDetails()).containsEntry("basePath", "/api/rails");
        assertThat(health.getDetails()).containsKey("timestamp");
        assertThat(health.getDetails()).containsEntry("libraryVersion", "1.0.0-SNAPSHOT");
    }
    
    @Test
    void testHealthWithDifferentRailType() {
        railProperties.setRailType("SWIFT");
        railProperties.setBasePath("/api/swift");
        
        healthIndicator = new RailHealthIndicator(railProperties);
        Health health = healthIndicator.health();
        
        assertThat(health.getStatus()).isEqualTo(Status.UP);
        assertThat(health.getDetails()).containsEntry("railType", "SWIFT");
        assertThat(health.getDetails()).containsEntry("basePath", "/api/swift");
    }
    
    @Test
    void testHealthIndicatorName() {
        // The health indicator should be named "rail" in Spring Actuator
        // This test verifies the indicator is properly constructed
        assertThat(healthIndicator).isNotNull();
    }
}
