package io.fireflysoftware.rails.dtos.scheduled;

import io.fireflysoftware.rails.domain.*;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;

@Data
@Builder
public class PaymentExecutionHistory {
    private String executionId;
    private String paymentId;
    private Money amount;
    private Instant executedAt;
    private PaymentStatus status;
    private String failureReason;
}
