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
 * Core domain models for banking operations.
 * 
 * <p>This package contains immutable, type-safe domain models that represent
 * fundamental banking concepts. These models enforce business rules and
 * validation at the domain level.
 * 
 * <h2>Domain Models</h2>
 * <ul>
 *   <li>{@link io.fireflysoftware.rails.domain.Money} - Immutable monetary value with currency</li>
 *   <li>{@link io.fireflysoftware.rails.domain.BankAccount} - Bank account supporting IBAN, Account/Routing, and SWIFT</li>
 *   <li>{@link io.fireflysoftware.rails.domain.Currency} - ISO 4217 currency codes</li>
 *   <li>{@link io.fireflysoftware.rails.domain.PaymentStatus} - Payment lifecycle states</li>
 *   <li>{@link io.fireflysoftware.rails.domain.RailType} - Banking rail types (ACH, SWIFT, SEPA, etc.)</li>
 * </ul>
 * 
 * <h2>Design Principles</h2>
 * <ul>
 *   <li><strong>Immutability</strong> - All domain objects are immutable</li>
 *   <li><strong>Validation</strong> - Business rules enforced at construction time</li>
 *   <li><strong>Type Safety</strong> - Strong typing prevents errors</li>
 *   <li><strong>Ubiquitous Language</strong> - Domain-driven design terminology</li>
 * </ul>
 * 
 * <h2>Examples</h2>
 * <pre>{@code
 * // Creating monetary values
 * Money amount = new Money(new BigDecimal("1000.00"), Currency.USD);
 * long cents = amount.toCents(); // 100000
 * 
 * // Creating bank accounts
 * BankAccount ibanAccount = BankAccount.fromIban(
 *     "John Doe",
 *     "DE89370400440532013000",
 *     "COBADEFFXXX"
 * );
 * 
 * BankAccount achAccount = BankAccount.fromAccountNumber(
 *     "Jane Smith",
 *     "123456789",
 *     "021000021"
 * );
 * }</pre>
 * 
 * @since 1.0.0
 */
package io.fireflysoftware.rails.domain;
