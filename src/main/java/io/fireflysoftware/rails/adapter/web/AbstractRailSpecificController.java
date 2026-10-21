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
import io.fireflysoftware.rails.dtos.specific.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractRailSpecificController {

    protected final RailAdapter railAdapter;

    @PostMapping("/specific/{operationName}")
    public Mono<ResponseEntity<RailOperationResponse>> executeOperation(
            @PathVariable String operationName,
            @RequestBody RailOperationRequest request) {
        log.info("Executing rail-specific operation: {}", operationName);
        return railAdapter.railSpecific().executeOperation(operationName, request);
    }

    @GetMapping("/specific/supports/{operationName}")
    public ResponseEntity<Boolean> supportsOperation(@PathVariable String operationName) {
        log.debug("Checking if operation is supported: {}", operationName);
        boolean supported = railAdapter.railSpecific().supportsOperation(operationName);
        return ResponseEntity.ok(supported);
    }
}
