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
 * Banking Rail adapters implementing the hexagonal architecture pattern.
 * 
 * <p>This package contains the primary adapter interface ({@link io.fireflysoftware.rails.adapter.RailAdapter})
 * that defines the standardized contract for all banking rail implementations.
 * 
 * <p>Each banking rail (ACH, SWIFT, SEPA, FPS, RTP, etc.) should implement the
 * {@code RailAdapter} interface to provide rail-specific functionality while
 * maintaining a consistent API across all rails.
 * 
 * <h2>Key Components</h2>
 * <ul>
 *   <li>{@link io.fireflysoftware.rails.adapter.RailAdapter} - Main adapter interface</li>
 *   <li>{@link io.fireflysoftware.rails.adapter.ports} - Port interfaces defining operations</li>
 *   <li>{@link io.fireflysoftware.rails.adapter.web} - Abstract REST controllers</li>
 * </ul>
 * 
 * <h2>Example Implementation</h2>
 * <pre>{@code
 * @Component
 * public class ACHRailAdapter implements RailAdapter {
 *     
 *     @Override
 *     public PaymentRailPort payments() {
 *         return achPaymentPort;
 *     }
 *     
 *     @Override
 *     public String getRailType() {
 *         return "ach";
 *     }
 *     
 *     // ... implement other ports
 * }
 * }</pre>
 * 
 * @see io.fireflysoftware.rails.adapter.RailAdapter
 * @see io.fireflysoftware.rails.adapter.ports
 * @since 1.0.0
 */
package io.fireflysoftware.rails.adapter;
