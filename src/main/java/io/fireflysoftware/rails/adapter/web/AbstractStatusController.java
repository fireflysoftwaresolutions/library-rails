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
package io.fireflysoftware.rails.adapter.web;

import io.fireflysoftware.rails.adapter.RailAdapter;
import io.fireflysoftware.rails.dtos.status.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * Abstract REST controller for payment status operations.
 * 
 * Provides endpoints for:
 * - Transaction status queries
 * - Payment tracking
 * - Status by reference
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractStatusController {

    protected final RailAdapter railAdapter;

    /**
     * Query transaction status by ID.
     * GET /status/transaction/{transactionId}
     */
    @GetMapping("/status/transaction/{transactionId}")
    public Mono<ResponseEntity<TransactionStatusResponse>> queryTransactionStatus(@PathVariable String transactionId) {
        log.debug("Querying transaction status: {}", transactionId);
        return railAdapter.status().queryTransactionStatus(transactionId);
    }

    /**
     * Query status by end-to-end reference.
     * GET /status/reference/{reference}
     */
    @GetMapping("/status/reference/{reference}")
    public Mono<ResponseEntity<TransactionStatusResponse>> queryStatusByReference(@PathVariable String reference) {
        log.debug("Querying status by reference: {}", reference);
        return railAdapter.status().queryStatusByReference(reference);
    }

    /**
     * Get full payment tracking history.
     * GET /status/tracking/{paymentId}
     */
    @GetMapping("/status/tracking/{paymentId}")
    public Mono<ResponseEntity<PaymentTrackingResponse>> getPaymentTracking(@PathVariable String paymentId) {
        log.debug("Getting payment tracking: {}", paymentId);
        return railAdapter.status().getPaymentTracking(paymentId);
    }
}
