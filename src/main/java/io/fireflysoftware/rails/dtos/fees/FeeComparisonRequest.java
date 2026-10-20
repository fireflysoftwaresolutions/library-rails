package io.fireflysoftware.rails.dtos.fees;

import io.fireflysoftware.rails.domain.*;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class FeeComparisonRequest {
    private Money amount;
    private List<SettlementSpeed> settlementSpeeds;
    private TransactionType transactionType;
}
