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
import java.time.Instant;

@Data
@Builder
public class AuthorizationResponse {
    /** Authorization ID (used for confirmation) */
    private String authorizationId;
    
    /** Authorization status */
    private AuthorizationStatus status;
    
    /** Whether SCA is required */
    private boolean scaRequired;
    
    /** Authentication challenge (if SCA required) */
    private AuthenticationChallenge authenticationChallenge;
    
    /** Reserved amount */
    private Money reservedAmount;
    
    /** Authorization expiry time */
    private Instant expiresAt;
    
    /** Additional information */
    private String message;
    
    public enum AuthorizationStatus {
        AUTHORIZED,
        PENDING_AUTHENTICATION,
        DECLINED,
        EXPIRED
    }
    
    @Data
    @Builder
    public static class AuthenticationChallenge {
        private AuthenticationMethod method;
        private String challengeToken;
        private String redirectUrl;
        private String qrCode;
        private Instant expiresAt;
    }
}
