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
import io.fireflysoftware.rails.dtos.settlement.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

/**
 * Abstract REST controller for settlement operations.
 * 
 * Provides endpoints for:
 * - Settlement reports
 * - Settlement details
 * - Expected settlement dates
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractSettlementController {

    protected final RailAdapter railAdapter;

    /**
     * Get settlement report for a specific date.
     * GET /settlement/report?date=2024-01-15
     */
    @GetMapping("/settlement/report")
    public Mono<ResponseEntity<SettlementReport>> getSettlementReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.debug("Getting settlement report for date: {}", date);
        return railAdapter.settlement().getSettlementReport(date);
    }

    /**
     * Get settlement reports for a date range.
     * POST /settlement/reports
     */
    @PostMapping("/settlement/reports")
    public Mono<ResponseEntity<List<SettlementReport>>> getSettlementReports(
            @RequestBody SettlementReportRequest request) {
        log.debug("Getting settlement reports for date range");
        return railAdapter.settlement().getSettlementReports(request);
    }

    /**
     * Get detailed settlement transactions.
     * GET /settlement/{settlementId}
     */
    @GetMapping("/settlement/{settlementId}")
    public Mono<ResponseEntity<SettlementDetails>> getSettlementDetails(@PathVariable String settlementId) {
        log.debug("Getting settlement details: {}", settlementId);
        return railAdapter.settlement().getSettlementDetails(settlementId);
    }

    /**
     * Get expected settlement date for a payment.
     * GET /settlement/expected/{paymentId}
     */
    @GetMapping("/settlement/expected/{paymentId}")
    public Mono<ResponseEntity<SettlementDateResponse>> getExpectedSettlementDate(@PathVariable String paymentId) {
        log.debug("Getting expected settlement date for payment: {}", paymentId);
        return railAdapter.settlement().getExpectedSettlementDate(paymentId);
    }
}
