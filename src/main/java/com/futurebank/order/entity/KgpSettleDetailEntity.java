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
 * (KgpSettleDetail)实体类
 *
 * @author ben
 * @since 2024-12-02 20:16:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "KgpSettleDetail", description = "$tableInfo.comment")
public class KgpSettleDetailEntity implements Serializable {
    private static final long serialVersionUID = 485254092420159963L;

    @ApiModelProperty(value = "${column.comment}")
    private String detail;

    @ApiModelProperty(value = "${column.comment}")
    private String receivertype;

    @ApiModelProperty(value = "${column.comment}")
    private String receiverid;

    @ApiModelProperty(value = "${column.comment}")
    private String projectid;

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
    private String paymentamount;

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
    private String currencycode;

    @ApiModelProperty(value = "${column.comment}")
    private String paymentdatetime;

    @ApiModelProperty(value = "${column.comment}")
    private String paymentprocessingdate;

    @ApiModelProperty(value = "${column.comment}")
    private String payouttype;

    @ApiModelProperty(value = "${column.comment}")
    private String payoutlevel;

    @ApiModelProperty(value = "${column.comment}")
    private String mainbatchidtype;

    @ApiModelProperty(value = "${column.comment}")
    private String mainbatchid;

    @ApiModelProperty(value = "${column.comment}")
    private String distributionamount;

    @ApiModelProperty(value = "${column.comment}")
    private String accountnumber;

    @ApiModelProperty(value = "${column.comment}")
    private String bankname;

    @ApiModelProperty(value = "${column.comment}")
    private String payoutstatus;

    @ApiModelProperty(value = "${column.comment}")
    private String errorname;

    @ApiModelProperty(value = "${column.comment}")
    private String createddatetime;

    @ApiModelProperty(value = "${column.comment}")
    private String transferdatetime;

    @ApiModelProperty(value = "${column.comment}")
    private String transfersuccessdatetime;

    @ApiModelProperty(value = "${column.comment}")
    private String completeddatetime;

    @ApiModelProperty(value = "${column.comment}")
    private String partnerbatchid;

    @ApiModelProperty(value = "${column.comment}")
    private String batchid;

    @ApiModelProperty(value = "${column.comment}")
    private String partnerpayoutmerchantid;

    @ApiModelProperty(value = "${column.comment}")
    private String payoutmerchantid;

    @ApiModelProperty(value = "${column.comment}")
    private String payoutpartnerbatchid;

    @ApiModelProperty(value = "${column.comment}")
    private String payersourceoffund;

    @ApiModelProperty(value = "${column.comment}")
    private Long id;

    @ApiModelProperty(value = "${column.comment}")
    private Integer isProcessed;

    @ApiModelProperty(value = "${column.comment}")
    private Date createdAt;

    @ApiModelProperty(value = "${column.comment}")
    private Date updateAt;
}
