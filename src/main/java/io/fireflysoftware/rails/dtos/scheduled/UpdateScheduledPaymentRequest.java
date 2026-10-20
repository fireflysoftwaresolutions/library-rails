package io.fireflysoftware.rails.dtos.scheduled;

import io.fireflysoftware.rails.domain.Money;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class UpdateScheduledPaymentRequest {
    private String scheduledPaymentId;
    private Money newAmount;
    private LocalDate newExecutionDate;
    private String newRemittanceInfo;
}
