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
 * 退单记录表(PproRChargeback)实体类
 *
 * @author ben
 * @since 2024-05-26 14:07:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PproRChargeback", description = "退单记录表")
public class PproRChargebackEntity implements Serializable {
    private static final long serialVersionUID = -10635291786975561L;

    @ApiModelProperty(value = "${column.comment}")
    private Long id;

    /**
     * 退单ID，唯一标识每笔退单
     */
    @ApiModelProperty(value = "退单ID，唯一标识每笔退单")
    private String cbid;

    /**
     * 金额，退单的金额
     */
    @ApiModelProperty(value = "金额，退单的金额")
    private String amount;

    /**
     * 货币类型，退单金额使用的货币代码
     */
    @ApiModelProperty(value = "货币类型，退单金额使用的货币代码")
    private String currency;

    /**
     * 账户持有人，账户被退单的个人或实体
     */
    @ApiModelProperty(value = "账户持有人，账户被退单的个人或实体")
    private String accountholder;

    /**
     * 支付目的，与退单相关的支付目的或说明
     */
    @ApiModelProperty(value = "支付目的，与退单相关的支付目的或说明")
    private String paymentpurpose;

    /**
     * 交易ID，与退单关联的交易的唯一标识符
     */
    @ApiModelProperty(value = "交易ID，与退单关联的交易的唯一标识符")
    private String txid;

    /**
     * 商户交易ID，商户系统中的交易ID
     */
    @ApiModelProperty(value = "商户交易ID，商户系统中的交易ID")
    private String merchanttxid;

    /**
     * 合同编号，与退单相关的合同或协议编号
     */
    @ApiModelProperty(value = "合同编号，与退单相关的合同或协议编号")
    private String mcontract;

    /**
     * 退单时间戳，退单操作发生的时间
     */
    @ApiModelProperty(value = "退单时间戳，退单操作发生的时间")
    private String chargebackts;

    /**
     * 导入时间戳，退单记录被导入系统的时间
     */
    @ApiModelProperty(value = "导入时间戳，退单记录被导入系统的时间")
    private String importedts;

    @ApiModelProperty(value = "${column.comment}")
    private String addJson;

    @ApiModelProperty(value = "${column.comment}")
    private String outJson;

    /**
     * 附加参考号码，与退单相关的附加参考号码或信息
     */
    @ApiModelProperty(value = "附加参考号码，与退单相关的附加参考号码或信息")
    private Date reconciliationDate;

    @ApiModelProperty(value = "${column.comment}")
    private Date createdTime;

    @ApiModelProperty(value = "${column.comment}")
    private Date updateTime;

    @ApiModelProperty(value = "${column.comment}")
    private String fileName;

    /**
     * 是否已处理
     */
    @ApiModelProperty(value = "是否已处理")
    private Integer isProcessed;
}
