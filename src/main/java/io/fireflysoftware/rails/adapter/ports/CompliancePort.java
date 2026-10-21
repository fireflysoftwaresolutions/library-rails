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

package io.fireflysoftware.rails.adapter.ports;

import io.fireflysoftware.rails.dtos.compliance.*;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * Port interface for compliance and regulatory checks.
 * 
 * Handles AML (Anti-Money Laundering), KYC (Know Your Customer),
 * sanctions screening, and other regulatory requirements.
 */
public interface CompliancePort {

    /**
     * Perform AML/KYC check on a payment.
     * Screens against sanctions lists, PEP lists, and checks transaction patterns.
     *
     * @param request compliance check request
     * @return reactive publisher with compliance result
     */
    Mono<ResponseEntity<ComplianceCheckResponse>> performComplianceCheck(ComplianceCheckRequest request);

    /**
     * Screen a beneficiary against sanctions lists.
     * Checks OFAC, UN, EU sanctions lists.
     *
     * @param request sanctions screening request
     * @return reactive publisher with screening result
     */
    Mono<ResponseEntity<SanctionsScreeningResponse>> screenSanctions(SanctionsScreeningRequest request);

    /**
     * Verify customer identity (KYC).
     * Validates customer information and documents.
     *
     * @param request KYC verification request
     * @return reactive publisher with verification result
     */
    Mono<ResponseEntity<KYCVerificationResponse>> verifyKYC(KYCVerificationRequest request);

    /**
     * Check if transaction requires enhanced due diligence.
     * Based on amount, destination, customer risk profile.
     *
     * @param request due diligence check request
     * @return reactive publisher with due diligence result
     */
    Mono<ResponseEntity<DueDiligenceResponse>> checkDueDiligence(DueDiligenceRequest request);

    /**
     * Report suspicious activity (SAR - Suspicious Activity Report).
     * Required for regulatory compliance.
     *
     * @param request suspicious activity report
     * @return reactive publisher with report confirmation
     */
    Mono<ResponseEntity<SARResponse>> reportSuspiciousActivity(SARRequest request);

    /**
     * Get customer risk profile.
     * Returns risk level based on transaction history and profile.
     *
     * @param customerId customer identifier
     * @return reactive publisher with risk profile
     */
    Mono<ResponseEntity<RiskProfile>> getCustomerRiskProfile(String customerId);
}
