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

/** Faster Payments (UK) specific attributes */
public class FPSSpecific {

    @Data
    @Builder
    public static class FPSPayment {
        /** Sort code */
        private String sortCode;
        
        /** Account number */
        private String accountNumber;
        
        /** Payment scheme (FPS) */
        private String paymentScheme;
        
        /** Confirmation of Payee (CoP) result */
        private ConfirmationOfPayee confirmationOfPayee;
    }

    @Data
    @Builder
    public static class ConfirmationOfPayee {
        private CoPResult result;
        private String matchedName;
        private String reason;
    }

    public enum CoPResult {
        MATCH,
        NO_MATCH,
        CLOSE_MATCH,
        NOT_CHECKED
    }
}
