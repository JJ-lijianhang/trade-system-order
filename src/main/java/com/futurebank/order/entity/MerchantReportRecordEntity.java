package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


/**
 * 商户报告记录表(MerchantReportRecord)实体类
 *
 * @author makejava
 * @since 2024-06-07 20:49:33
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MerchantReportRecord", description = "商户报告记录表")
public class MerchantReportRecordEntity implements Serializable {
    private static final long serialVersionUID = 399064383248795704L;
    
    /**
     * 唯一标识
     */     
	@ApiModelProperty(value = "唯一标识")
    private Long id;
    
    /**
     * 商户编号
     */     
	@ApiModelProperty(value = "商户编号")
    private Long merchantId;
    
    /**
     * 文件名
     */     
	@ApiModelProperty(value = "文件名")
    private String fileName;
    
    /**
     * 文件类型
     */     
	@ApiModelProperty(value = "文件类型")
    private String fileType;
    
    /**
     * 文件路径
     */
	@ApiModelProperty(value = "文件路径")
    private String fileUrl;

    /**
     * 报告类型
     */     
	@ApiModelProperty(value = "报告类型")
    private String reportType;
    
    /**
     * 字段列表
     */     
	@ApiModelProperty(value = "字段列表")
    private String fields;
    
    /**
     * 开始时间
     */     
	@ApiModelProperty(value = "开始时间")
    private Date startTime;
    
    /**
     * 结束时间
     */     
	@ApiModelProperty(value = "结束时间")
    private Date endTime;
    
    /**
     * 创建时间
     */     
	@ApiModelProperty(value = "创建时间")
    private Date createdTime;
}
