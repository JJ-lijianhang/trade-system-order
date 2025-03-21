package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 商户表(Merchant)实体类
 *
 * @author ben
 * @since 2024-11-19 17:10:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Merchant", description = "商户表")
public class MerchantEntity implements Serializable {
    private static final long serialVersionUID = 995737296075071438L;

    /**
     * 商户id
     */
    @ApiModelProperty(value = "商户id")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 代理id
     */
    @ApiModelProperty(value = "代理id")
    private Long agentId;

    /**
     * 国家编码
     */
    @ApiModelProperty(value = "国家编码")
    private String countryCode;

    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 商户别名
     */
    @ApiModelProperty(value = "商户别名")
    private String merchantAlias;

    /**
     * 开户名
     */
    @ApiModelProperty(value = "开户名")
    private String accountName;

    /**
     * 开户银行
     */
    @ApiModelProperty(value = "开户银行")
    private String accountBank;

    /**
     * 银行帐号
     */
    @ApiModelProperty(value = "银行帐号")
    private String account;

    /**
     * 当前余额
     */
    @ApiModelProperty(value = "当前余额")
    private BigDecimal curBalance;

    /**
     * 冻结余额
     */
    @ApiModelProperty(value = "冻结余额")
    private BigDecimal freezeBalance;

    /**
     * 在途余额（暂时保留）
     */
    @ApiModelProperty(value = "在途余额（暂时保留）")
    private BigDecimal pendingBalance;

    /**
     * googleAuth
     */
    @ApiModelProperty(value = "googleAuth")
    private String verifyGoogle;

    /**
     * 鉴权ip-payin
     */
    @ApiModelProperty(value = "鉴权ip-payin")
    private String payinIp;

    /**
     * 鉴权ip-payout
     */
    @ApiModelProperty(value = "鉴权ip-payout")
    private String payoutIp;

    /**
     * 分配商户的安全key-payin
     */
    @ApiModelProperty(value = "分配商户的安全key-payin")
    private String payinSecurityKey;

    /**
     * 分配商户的安全key-payout
     */
    @ApiModelProperty(value = "分配商户的安全key-payout")
    private String payoutSecurityKey;

    /**
     * 商户webhook地址
     */
    @ApiModelProperty(value = "商户webhook地址")
    private String webhookUrl;

    /**
     * 状态 0=商用 1=禁用
     */
    @ApiModelProperty(value = "状态 0=商用 1=禁用")
    private Integer istate;

    /**
     * 是否未默认商户 0=不是 1=是 默认0 (废弃字段)
     */
    @ApiModelProperty(value = "是否未默认商户 0=不是 1=是 默认0 (废弃字段)")
    private Integer isDefault;

    /**
     * 商户介绍
     */
    @ApiModelProperty(value = "商户介绍")
    private String introduce;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 商户引用
     */
    @ApiModelProperty(value = "商户引用")
    private Long merchantReference;


    /**
     * 母商户状态
     */
    @ApiModelProperty(value = "母商户状态")
    private Integer parentState;

    /**
     * 子商户状态
     */
    @ApiModelProperty(value = "子商户状态")
    private Integer childState;

    /**
     * 审核次数
     */
    @ApiModelProperty(value = "审核次数")
    private Integer reviewCount;

    /**
     * 推荐人id
     */
    @ApiModelProperty(value = "推荐人id")
    private Long referenceId;

    /**
     * 推荐人名称
     */
    @ApiModelProperty(value = "推荐人名称")
    private String referenceName;

    /**
     * 销售人名称
     */
    @ApiModelProperty(value = "销售人名称")
    private String salesName;

    /**
     * 名单筛查状态 Pending=待验证 Verified=已验证 Returned=退回
     */
    @ApiModelProperty(value = "名单筛查状态 Pending=待验证 Verified=已验证 Returned=退回")
    private String screenState;

    /**
     * 操作状态：Filling=填写中 Pending=待验证 Verified=已验证 Activated=已激活 Returned=退回
     */
    @ApiModelProperty(value = "操作状态：Filling=填写中 Pending=待验证 Verified=已验证 Activated=已激活 Returned=退回")
    private String checkIn;

    /**
     * 激活时间
     */
    @ApiModelProperty(value = "激活时间")
    private Date activatedTime;

    /**
     * 上次的操作状态
     */
    @ApiModelProperty(value = "上次的操作状态")
    private String lastCheckIn;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 鉴权ip-payin状态: 1=限制,0=不限制
     */
    @ApiModelProperty(value = "鉴权ip-payin状态: 1=限制,0=不限制")
    private Integer payinIpFlag;

    /**
     * 鉴权ip-payout状态: 1=限制,0=不限制
     */
    @ApiModelProperty(value = "鉴权ip-payout状态: 1=限制,0=不限制")
    private Integer payoutIpFlag;

    /**
     * key-payin名称
     */
    @ApiModelProperty(value = "key-payin名称")
    private String payinKeyName;

    /**
     * key_payout名称
     */
    @ApiModelProperty(value = "key_payout名称")
    private String payoutKeyName;

    /**
     * key-payin创建时间
     */
    @ApiModelProperty(value = "key-payin创建时间")
    private Date payinKeyCreatedTime;

    /**
     * key-payout创建时间
     */
    @ApiModelProperty(value = "key-payout创建时间")
    private Date payoutKeyCreatedTime;

    /**
     * key-payin密钥有效期 (为空时,不会过期)
     */
    @ApiModelProperty(value = "key-payin密钥有效期 (为空时,不会过期)")
    private Date payinValidityPeriod;

    /**
     * key-payout密钥有效期 (为空时,不会过期)
     */
    @ApiModelProperty(value = "key-payout密钥有效期 (为空时,不会过期)")
    private Date payoutValidityPeriod;

    /**
     * 对账日期
     */
    @ApiModelProperty(value = "对账日期")
    private Date reconciliationDate;

    /**
     * 结算日期
     */
    @ApiModelProperty(value = "结算日期")
    private Date settlementDate;

    /**
     * 结算周期
     */
    @ApiModelProperty(value = "结算周期")
    private Integer settlementCycle;

    /**
     * 最低结算金额
     */
    @ApiModelProperty(value = "最低结算金额")
    private BigDecimal minSettleAmount;

    /**
     * 最低结算金额币种
     */
    @ApiModelProperty(value = "最低结算金额币种")
    private String minSettleCcy;

    /**
     * 活跃状态 Active=活跃 Inactive=不活跃
     */
    @ApiModelProperty(value = "活跃状态 Active=活跃 Inactive=不活跃")
    private String activeStatus;

    /**
     * crr风险等级 HIGH=高 MEDIUM=中 LOW=低
     */
    @ApiModelProperty(value = "crr风险等级 HIGH=高 MEDIUM=中 LOW=低")
    private String rcrrRiskLevel;

    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    private String operator;

    /**
     * 登记状态 待更新=ToBeUpdated
     */
    @ApiModelProperty(value = "登记状态 待更新=ToBeUpdated")
    private String registerStatus;

    /**
     * 定期审核时间
     */
    @ApiModelProperty(value = "定期审核时间")
    private Date regularReviewDate;

    /**
     * 定期审核阶段
     */
    @ApiModelProperty(value = "定期审核阶段")
    private String regularReviewStage;

    /**
     * KGP合作伙伴商户id
     */
    @ApiModelProperty(value = "KGP合作伙伴商户id")
    private String kgpPartnerMerchantId;

    /**
     * KGP合作伙伴店铺id
     */
    @ApiModelProperty(value = "KGP合作伙伴店铺id")
    private String kgpPartnerShopId;

    /**
     * 指定筛选决策 ACCEPT=未命中 REJECT=命中筛查名单REVIEW=可能命中了筛查名单
     */
    @ApiModelProperty(value = "指定筛选决策 ACCEPT=未命中 REJECT=命中筛查名单REVIEW=可能命中了筛查名单")
    private String screenDecision;
}
