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

/** SWIFT-specific payment attributes */
public class SWIFTSpecific {

    @Data
    @Builder
    public static class SWIFTPayment {
        /** SWIFT message type (MT103, MT202, etc.) */
        private String messageType;
        
        /** Bank Operation Code */
        private String bankOperationCode;
        
        /** Instruction Code */
        private String instructionCode;
        
        /** Sender to Receiver Information */
        private String senderToReceiverInfo;
        
        /** Regulatory Reporting */
        private String regulatoryReporting;
        
        /** Remittance Information */
        private String remittanceInformation;
        
        /** Intermediary bank details */
        private IntermediaryBank intermediaryBank;
        
        /** Charges instruction (OUR, SHA, BEN) */
        private ChargesInstruction chargesInstruction;
    }

    @Data
    @Builder
    public static class IntermediaryBank {
        private String swiftCode;
        private String name;
        private String accountNumber;
    }

    public enum ChargesInstruction {
        OUR,  // All charges paid by sender
        SHA,  // Shared charges
        BEN   // All charges paid by beneficiary
    }
}
