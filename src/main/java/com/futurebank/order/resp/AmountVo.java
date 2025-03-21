package com.futurebank.order.resp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AmountVo implements Serializable {
    private String transactionAmount;
    private String transactionAmountCcy;
}

