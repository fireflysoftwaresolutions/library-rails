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


package io.fireflysoftware.rails.domain.railspecific;

import lombok.Builder;
import lombok.Data;

/** ACH-specific payment attributes */
public class ACHSpecific {

    @Data
    @Builder
    public static class ACHPayment {
        /** SEC Code (WEB, PPD, CCD, etc.) */
        private SECCode secCode;
        
        /** Company Entry Description */
        private String companyEntryDescription;
        
        /** Company Discretionary Data */
        private String companyDiscretionaryData;
        
        /** Individual ID Number */
        private String individualIdNumber;
        
        /** Individual Name */
        private String individualName;
        
        /** Addenda records */
        private String addenda;
        
        /** Same-day ACH flag */
        private boolean sameDayAch;
    }

    public enum SECCode {
        WEB,  // Internet-initiated
        PPD,  // Prearranged Payment & Deposit (consumer)
        CCD,  // Corporate Credit or Debit
        TEL,  // Telephone-initiated
        ARC,  // Accounts Receivable Conversion
        BOC,  // Back Office Conversion
        POP,  // Point of Purchase
        RCK,  // Re-presented Check
        CTX,  // Corporate Trade Exchange
        IAT   // International ACH Transaction
    }
}
