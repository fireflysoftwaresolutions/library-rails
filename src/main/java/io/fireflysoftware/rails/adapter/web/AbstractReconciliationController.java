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
import io.fireflysoftware.rails.dtos.reconciliation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractReconciliationController {

    protected final RailAdapter railAdapter;

    @GetMapping("/reconciliation/report")
    public Mono<ResponseEntity<ReconciliationReport>> getReconciliationReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.debug("Getting reconciliation report for date: {}", date);
        return railAdapter.reconciliation().getReconciliationReport(date);
    }

    @PostMapping("/reconciliation/discrepancies")
    public Mono<ResponseEntity<List<DiscrepancyResponse>>> findDiscrepancies(@RequestBody ReconciliationRequest request) {
        log.debug("Finding discrepancies");
        return railAdapter.reconciliation().findDiscrepancies(request);
    }

    @GetMapping("/reconciliation/summary")
    public Mono<ResponseEntity<ReconciliationSummary>> getReconciliationSummary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        log.debug("Getting reconciliation summary from {} to {}", startDate, endDate);
        return railAdapter.reconciliation().getReconciliationSummary(startDate, endDate);
    }
}
