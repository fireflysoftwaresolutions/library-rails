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
 * Abstract REST controllers providing zero-boilerplate API endpoints.
 * 
 * <p>This package contains 9 abstract controller classes that automatically
 * expose REST APIs for all banking rail operations. Rail implementations
 * simply extend these controllers to inherit complete REST endpoint functionality.
 * 
 * <h2>Abstract Controllers (48 Total Endpoints)</h2>
 * <ul>
 *   <li>{@link io.fireflysoftware.rails.adapter.web.AbstractPaymentRailController} - 13 payment endpoints with SCA support</li>
 *   <li>{@link io.fireflysoftware.rails.adapter.web.AbstractStatusController} - 3 status inquiry endpoints</li>
 *   <li>{@link io.fireflysoftware.rails.adapter.web.AbstractSettlementController} - 4 settlement reporting endpoints</li>
 *   <li>{@link io.fireflysoftware.rails.adapter.web.AbstractMandateController} - 5 mandate management endpoints</li>
 *   <li>{@link io.fireflysoftware.rails.adapter.web.AbstractBulkPaymentController} - 3 bulk payment endpoints</li>
 *   <li>{@link io.fireflysoftware.rails.adapter.web.AbstractReconciliationController} - 3 reconciliation endpoints</li>
 *   <li>{@link io.fireflysoftware.rails.adapter.web.AbstractScheduledPaymentController} - 9 scheduled payment endpoints</li>
 *   <li>{@link io.fireflysoftware.rails.adapter.web.AbstractComplianceController} - 6 compliance endpoints</li>
 *   <li>{@link io.fireflysoftware.rails.adapter.web.AbstractRailSpecificController} - 2 custom operation endpoints</li>
 * </ul>
 * 
 * <h2>Zero Boilerplate Principle</h2>
 * <p>Rail implementations get fully functional REST APIs by simply extending
 * the appropriate controller with a single constructor:
 * 
 * <pre>{@code
 * @RestController
 * @RequestMapping("/api/ach")
 * public class ACHPaymentController extends AbstractPaymentRailController {
 *     public ACHPaymentController(RailAdapter railAdapter) {
 *         super(railAdapter);
 *     }
 *     // That's it! 13 endpoints are now available automatically
 * }
 * }</pre>
 * 
 * <h2>Features</h2>
 * <ul>
 *   <li><strong>Reactive</strong> - All endpoints return {@code Mono} or {@code Flux}</li>
 *   <li><strong>Logging</strong> - Automatic request/response logging</li>
 *   <li><strong>Validation</strong> - Automatic DTO validation with {@code @Valid}</li>
 *   <li><strong>Error Handling</strong> - Consistent error responses</li>
 *   <li><strong>OpenAPI</strong> - Compatible with SpringDoc for API documentation</li>
 * </ul>
 * 
 * @see io.fireflysoftware.rails.adapter.RailAdapter
 * @see io.fireflysoftware.rails.adapter.ports
 * @since 1.0.0
 */
package io.fireflysoftware.rails.adapter.web;
