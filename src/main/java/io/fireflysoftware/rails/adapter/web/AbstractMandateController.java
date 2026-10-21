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
import io.fireflysoftware.rails.dtos.mandate.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Abstract REST controller for direct debit mandate operations.
 * 
 * Provides endpoints for:
 * - Mandate creation
 * - Mandate management (get, update, cancel)
 * - Mandate listing
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractMandateController {

    protected final RailAdapter railAdapter;

    /**
     * Create a new direct debit mandate.
     * POST /mandates
     */
    @PostMapping("/mandates")
    public Mono<ResponseEntity<MandateResponse>> createMandate(@RequestBody CreateMandateRequest request) {
        log.info("Creating mandate");
        return railAdapter.mandates().createMandate(request);
    }

    /**
     * Get mandate details.
     * GET /mandates/{mandateId}
     */
    @GetMapping("/mandates/{mandateId}")
    public Mono<ResponseEntity<MandateResponse>> getMandate(@PathVariable String mandateId) {
        log.debug("Getting mandate: {}", mandateId);
        return railAdapter.mandates().getMandate(mandateId);
    }

    /**
     * Update an existing mandate.
     * PUT /mandates
     */
    @PutMapping("/mandates")
    public Mono<ResponseEntity<MandateResponse>> updateMandate(@RequestBody UpdateMandateRequest request) {
        log.info("Updating mandate");
        return railAdapter.mandates().updateMandate(request);
    }

    /**
     * Cancel a mandate.
     * DELETE /mandates/{mandateId}
     */
    @DeleteMapping("/mandates/{mandateId}")
    public Mono<ResponseEntity<MandateResponse>> cancelMandate(@PathVariable String mandateId) {
        log.info("Cancelling mandate: {}", mandateId);
        return railAdapter.mandates().cancelMandate(mandateId);
    }

    /**
     * List mandates with filtering.
     * POST /mandates/list
     */
    @PostMapping("/mandates/list")
    public Mono<ResponseEntity<List<MandateResponse>>> listMandates(@RequestBody ListMandatesRequest request) {
        log.debug("Listing mandates");
        return railAdapter.mandates().listMandates(request);
    }
}
