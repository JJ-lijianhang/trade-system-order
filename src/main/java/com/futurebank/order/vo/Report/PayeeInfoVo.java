package com.futurebank.order.vo.Report;

import lombok.Data;

@Data
public class PayeeInfoVo {
    private Long payeeId;
    private String accType;
    private String accountName;
    private String cardNo;
    private String bankName;
    private String bankCode;
    private String swiftCode;
    private String countryCode;
    private String bankAddress;
    private String payeeAddress;
    private String routingCode;
}
