/*
 * Copyright 2025 Firefly Software Foundation
 */
package io.fireflysoftware.rails.dtos.reconciliation;

import lombok.Builder;
import lombok.Data;
import io.fireflysoftware.rails.domain.Money;
import java.time.Instant;

@Data
@Builder
public class DiscrepancyResponse {
    private String discrepancyId;
    private String transactionId;
    private String type;
    private Money amount;
    private Instant detectedAt;
    private String reason;
}
