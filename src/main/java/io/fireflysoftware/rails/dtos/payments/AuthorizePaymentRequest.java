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


package io.fireflysoftware.rails.dtos.payments;

import io.fireflysoftware.rails.domain.*;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
public class AuthorizePaymentRequest {
    private Money amount;
    private BankAccount debtorAccount;
    private BankAccount creditorAccount;
    private String endToEndReference;
    private String remittanceInformation;
    private TransactionType transactionType;
    private SettlementSpeed settlementSpeed;
    private LocalDate requestedExecutionDate;
    
    /** Authentication context for SCA */
    private AuthenticationContext authenticationContext;
    
    /** Whether to skip SCA if exemption applies */
    private boolean requestScaExemption;
    
    /** Additional metadata */
    private Map<String, String> metadata;
}
