package io.fireflysoftware.rails.dtos.compliance;

import io.fireflysoftware.rails.domain.Money;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DueDiligenceRequest {
    private String customerId;
    private Money amount;
    private String destinationCountry;
    private String purpose;
}
