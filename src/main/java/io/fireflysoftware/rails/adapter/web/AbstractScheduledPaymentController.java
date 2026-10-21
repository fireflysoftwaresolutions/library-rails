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
import io.fireflysoftware.rails.dtos.scheduled.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractScheduledPaymentController {

    protected final RailAdapter railAdapter;

    @PostMapping("/scheduled")
    public Mono<ResponseEntity<ScheduledPaymentResponse>> createScheduledPayment(@RequestBody CreateScheduledPaymentRequest request) {
        log.info("Creating scheduled payment");
        return railAdapter.scheduledPayments().createScheduledPayment(request);
    }

    @GetMapping("/scheduled/{scheduledPaymentId}")
    public Mono<ResponseEntity<ScheduledPaymentResponse>> getScheduledPayment(@PathVariable String scheduledPaymentId) {
        log.debug("Getting scheduled payment: {}", scheduledPaymentId);
        return railAdapter.scheduledPayments().getScheduledPayment(scheduledPaymentId);
    }

    @PostMapping("/scheduled/list")
    public Mono<ResponseEntity<List<ScheduledPaymentResponse>>> listScheduledPayments(@RequestBody ListScheduledPaymentsRequest request) {
        log.debug("Listing scheduled payments");
        return railAdapter.scheduledPayments().listScheduledPayments(request);
    }

    @DeleteMapping("/scheduled/{scheduledPaymentId}")
    public Mono<ResponseEntity<CancellationResponse>> cancelScheduledPayment(@PathVariable String scheduledPaymentId) {
        log.info("Cancelling scheduled payment: {}", scheduledPaymentId);
        return railAdapter.scheduledPayments().cancelScheduledPayment(scheduledPaymentId);
    }

    @PutMapping("/scheduled")
    public Mono<ResponseEntity<ScheduledPaymentResponse>> updateScheduledPayment(@RequestBody UpdateScheduledPaymentRequest request) {
        log.info("Updating scheduled payment");
        return railAdapter.scheduledPayments().updateScheduledPayment(request);
    }

    @PostMapping("/recurring")
    public Mono<ResponseEntity<RecurringPaymentResponse>> createRecurringPayment(@RequestBody CreateRecurringPaymentRequest request) {
        log.info("Creating recurring payment");
        return railAdapter.scheduledPayments().createRecurringPayment(request);
    }

    @GetMapping("/recurring/{recurringPaymentId}")
    public Mono<ResponseEntity<RecurringPaymentResponse>> getRecurringPayment(@PathVariable String recurringPaymentId) {
        log.debug("Getting recurring payment: {}", recurringPaymentId);
        return railAdapter.scheduledPayments().getRecurringPayment(recurringPaymentId);
    }

    @DeleteMapping("/recurring/{recurringPaymentId}")
    public Mono<ResponseEntity<CancellationResponse>> cancelRecurringPayment(@PathVariable String recurringPaymentId) {
        log.info("Cancelling recurring payment: {}", recurringPaymentId);
        return railAdapter.scheduledPayments().cancelRecurringPayment(recurringPaymentId);
    }

    @GetMapping("/recurring/{recurringPaymentId}/history")
    public Mono<ResponseEntity<List<PaymentExecutionHistory>>> getRecurringPaymentHistory(@PathVariable String recurringPaymentId) {
        log.debug("Getting recurring payment history: {}", recurringPaymentId);
        return railAdapter.scheduledPayments().getRecurringPaymentHistory(recurringPaymentId);
    }
}
