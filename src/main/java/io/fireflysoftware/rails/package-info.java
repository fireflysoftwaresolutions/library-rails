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

/**
 * Firefly Banking Rails Library.
 * 
 * <p>A comprehensive Banking Payment Rails abstraction library providing unified,
 * type-safe interfaces for integrating with multiple Banking Payment Rails
 * (ACH, SWIFT, SEPA, FPS, RTP, etc.) while maintaining clean hexagonal architecture
 * and complete rail independence.
 * 
 * <h2>Key Features</h2>
 * <ul>
 *   <li><strong>9 Port Interfaces</strong> - Complete banking rail operation coverage</li>
 *   <li><strong>Zero Boilerplate</strong> - Abstract controllers with auto-configured REST APIs</li>
 *   <li><strong>Reactive & Non-Blocking</strong> - Built on Project Reactor</li>
 *   <li><strong>Type-Safe</strong> - Strong typing throughout with domain models</li>
 *   <li><strong>Enterprise Resilience</strong> - Circuit breakers, retries, bulkheads</li>
 *   <li><strong>Spring Boot Integration</strong> - Auto-configuration and health checks</li>
 *   <li><strong>SCA/PSD2 Compliant</strong> - Two-phase authentication support</li>
 * </ul>
 * 
 * <h2>Architecture</h2>
 * <p>The library follows hexagonal (ports and adapters) architecture:
 * <ul>
 *   <li><strong>Ports</strong> - {@link io.fireflysoftware.rails.adapter.ports} - Define business operations</li>
 *   <li><strong>Adapters</strong> - {@link io.fireflysoftware.rails.adapter} - Rail-specific implementations</li>
 *   <li><strong>Domain</strong> - {@link io.fireflysoftware.rails.domain} - Core business models</li>
 *   <li><strong>DTOs</strong> - {@link io.fireflysoftware.rails.dtos} - Data transfer objects</li>
 * </ul>
 * 
 * <h2>Quick Start</h2>
 * <pre>{@code
 * @Service
 * public class PaymentService {
 *     @Autowired
 *     private RailAdapter railAdapter;
 *     
 *     public Mono<PaymentResponse> initiatePayment(PaymentRequest request) {
 *         return railAdapter.payments()
 *             .initiatePayment(InitiatePaymentRequest.builder()
 *                 .amount(new Money(request.getAmount(), Currency.USD))
 *                 .debtorAccount(request.getDebtorAccount())
 *                 .creditorAccount(request.getCreditorAccount())
 *                 .build())
 *             .map(ResponseEntity::getBody);
 *     }
 * }
 * }</pre>
 * 
 * @see io.fireflysoftware.rails.adapter.RailAdapter
 * @see io.fireflysoftware.rails.adapter.ports
 * @see io.fireflysoftware.rails.domain
 * @since 1.0.0
 */
package io.fireflysoftware.rails;
