package com.futurebank.order.vo.Invoice;

import lombok.Data;

@Data
public class SettlementNotifyVO {
    private Attr attr;
    private String email;
    private String sendType;


    @Data
    public static class Attr {
        private String date;
        private String sendType;
        private String reconciliationFileURL;
        private String reconciliationFileName;
        private String refundFileURL;
        private String refundFileURLName;
        private String settlementFileURL;
        private String settlementFileURLName;
        private String inoviceFileURL;
        private String inoviceFileURLName;
    }

}
