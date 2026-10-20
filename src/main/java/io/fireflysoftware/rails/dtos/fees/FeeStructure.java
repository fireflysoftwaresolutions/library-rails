package io.fireflysoftware.rails.dtos.fees;

import io.fireflysoftware.rails.domain.Money;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class FeeStructure {
    private String railType;
    private List<FeeTier> feeTiers;
    private Money minimumFee;
    private Money maximumFee;
    
    @Data
    @Builder
    public static class FeeTier {
        private Money fromAmount;
        private Money toAmount;
        private String feeType;
        private String feeValue;
    }
}
