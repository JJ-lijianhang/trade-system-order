package com.futurebank.order.entity;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter;
import lombok.Setter;


/**
 * (KgpPaymentDetail)实体类
 *
 * @author ben
 * @since 2024-12-02 20:17:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "KgpPaymentDetail", description = "$tableInfo.comment")
public class KgpPaymentDetailEntity implements Serializable {
    private static final long serialVersionUID = -13819014515847247L;

    @ApiModelProperty(value = "${column.comment}")
    private String detail;

    @ApiModelProperty(value = "${column.comment}")
    private String projectid;

    @ApiModelProperty(value = "${column.comment}")
    private String projectname;

    @ApiModelProperty(value = "${column.comment}")
    private String partnerid;

    @ApiModelProperty(value = "${column.comment}")
    private String partnermerchantid;

    @ApiModelProperty(value = "${column.comment}")
    private String partnershopid;

    @ApiModelProperty(value = "${column.comment}")
    private String partnerorderid;

    @ApiModelProperty(value = "${column.comment}")
    private String partnerpaymentid;

    @ApiModelProperty(value = "${column.comment}")
    private String sourceoffundtype;

    @ApiModelProperty(value = "${column.comment}")
    private String sourceoffundmerchantid;

    @ApiModelProperty(value = "${column.comment}")
    private String sourceoffundshopid;

    @ApiModelProperty(value = "${column.comment}")
    private String amount;

    @ApiModelProperty(value = "${column.comment}")
    private String currencycode;

    @ApiModelProperty(value = "${column.comment}")
    private String paymentstatus;

    @ApiModelProperty(value = "${column.comment}")
    private String createdatetime;

    @ApiModelProperty(value = "${column.comment}")
    private String paymentdatetime;

    @ApiModelProperty(value = "${column.comment}")
    private String partnerpaymentdate;

    @ApiModelProperty(value = "${column.comment}")
    private String paymentprocessingdate;

    @ApiModelProperty(value = "${column.comment}")
    private String cashid;

    @ApiModelProperty(value = "${column.comment}")
    private String partnersof;

    @ApiModelProperty(value = "${column.comment}")
    private String cardbrand;

    @ApiModelProperty(value = "${column.comment}")
    private String issuerbank;

    @ApiModelProperty(value = "${column.comment}")
    private String cardmasking;

    @ApiModelProperty(value = "${column.comment}")
    private String transactionaccount;

    @ApiModelProperty(value = "${column.comment}")
    private String approvalcode;

    @ApiModelProperty(value = "${column.comment}")
    private String buyername;

    @ApiModelProperty(value = "${column.comment}")
    private String paymentfeeabsorber;

    @ApiModelProperty(value = "${column.comment}")
    private String paymentfeecollectiontype;

    @ApiModelProperty(value = "${column.comment}")
    private String paymentfeeamount;

    @ApiModelProperty(value = "${column.comment}")
    private String paymentvatamount;

    @ApiModelProperty(value = "${column.comment}")
    private String paymentfeenetamount;

    @ApiModelProperty(value = "${column.comment}")
    private String paymentwhtamount;

    @ApiModelProperty(value = "${column.comment}")
    private String feecurrencycode;

    @ApiModelProperty(value = "${column.comment}")
    private String reconcileflag;

    @ApiModelProperty(value = "${column.comment}")
    private String refundflag;

    @ApiModelProperty(value = "${column.comment}")
    private String payoutstatus;

    @ApiModelProperty(value = "${column.comment}")
    private String payersourceoffund;

    @ApiModelProperty(value = "${column.comment}")
    private Long id;

    /**
     * 0:未处理 1：已处理
     */
    @ApiModelProperty(value = "0:未处理 1：已处理")
    private Integer isProcessed;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateAt;
}
