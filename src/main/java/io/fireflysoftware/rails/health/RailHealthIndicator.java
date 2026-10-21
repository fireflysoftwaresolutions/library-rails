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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Health indicator for banking rail connectivity.
 * 
 * <p>Integrates with Spring Boot Actuator to provide health check endpoints.
 * The health check reports the rail type and basic configuration status.
 * 
 * <p>Implementations should provide their own custom health checks by
 * implementing additional HealthIndicator beans that check actual
 * connectivity to external rail systems.
 * 
 * @see HealthIndicator
 * @see org.springframework.boot.health.contributor.Health
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RailHealthIndicator implements HealthIndicator {

    private final RailProperties railProperties;

    @Override
    public Health health() {
        try {
            Map<String, Object> details = new HashMap<>();
            details.put("railType", railProperties.getRailType());
            details.put("basePath", railProperties.getBasePath());
            details.put("timestamp", Instant.now());
            details.put("libraryVersion", "1.0.0-SNAPSHOT");
            
            // Basic health check - configuration is loaded
            return Health.up()
                    .withDetails(details)
                    .build();
                    
        } catch (Exception e) {
            log.error("Health check failed for rail: {}", railProperties.getRailType(), e);
            
            return Health.down()
                    .withDetail("railType", railProperties.getRailType())
                    .withDetail("error", e.getMessage())
                    .withException(e)
                    .build();
        }
    }
}
