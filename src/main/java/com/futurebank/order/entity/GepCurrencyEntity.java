package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * (GepCurrency)实体类
 *
 * @author makejava
 * @since 2024-06-14 10:56:31
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "GepCurrency", description = "$tableInfo.comment")
public class GepCurrencyEntity implements Serializable {
    private static final long serialVersionUID = 309823327380527378L;
    
    /**
     * id
     */     
	@ApiModelProperty(value = "id")
    private Integer id;
    
    /**
     * 币种
     */     
	@ApiModelProperty(value = "币种")
    private String currency;
    
    /**
     * 国家三字码
     */     
	@ApiModelProperty(value = "国家三字码")
    private String iso3;
    
    /**
     * 国家（英文）
     */     
	@ApiModelProperty(value = "国家（英文）")
    private String countryEn;
    
    /**
     * 国家（中文）
     */     
	@ApiModelProperty(value = "国家（中文）")
    private String country;
    
    /**
     * 是否支持换汇(0:支持，1：不支持)
     */     
	@ApiModelProperty(value = "是否支持换汇(0:支持，1：不支持)")
    private Integer isExchange;
    
    /**
     * 是否支持收款(0:支持，1：不支持)
     */     
	@ApiModelProperty(value = "是否支持收款(0:支持，1：不支持)")
    private Integer isPayee;
    
    /**
     * 是否支持付款(0:支持，1：不支持)
     */     
	@ApiModelProperty(value = "是否支持付款(0:支持，1：不支持)")
    private Integer isPayment;
    
    /**
     * 渠道id
     */     
	@ApiModelProperty(value = "渠道id")
    private String channelId;
    
    /**
     * 是否支持SWIFT（0：支持，1：不支持）
     */     
	@ApiModelProperty(value = "是否支持SWIFT（0：支持，1：不支持）")
    private Integer isSwift;
    
    /**
     * 是否支持Local
     */     
	@ApiModelProperty(value = "是否支持Local")
    private Integer isLocal;
}
