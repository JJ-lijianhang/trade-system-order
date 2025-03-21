package com.futurebank.order.vo.Report;

import lombok.Data;

@Data
public class TableHeaderVo {
    /**
     * 法律实体名称
     */
    private String legalEntityName;

    /**
     * 国家代码
     */
    private String registerCountryCode;

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 时区
     */
    private String timeZone;

    /**
     * 日期范围
     */
    private String dateRange;

    /**
     * 开始日期 2024/12/1 0:30
     */
    private String fromDate;

    /**
     * 结束日期
     */
    private String toDate;
}
