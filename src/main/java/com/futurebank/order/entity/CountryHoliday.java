package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CountryHoliday", description = "国家节假日信息表")
public class CountryHoliday extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 年份
     */
    @ApiModelProperty(value = "年份")
    private Integer year;

    /**
     * 国家名称
     */
    @ApiModelProperty(value = "国家名称")
    private String country;

    /**
     * 国家代码
     */
    @ApiModelProperty(value = "国家代码")
    private String countryCode;

    /**
     * 语言
     */
    @ApiModelProperty(value = "语言")
    private String language;

    /**
     * 节假日名称
     */
    @ApiModelProperty(value = "节假日名称")
    private String name;

    /**
     * 节假日日期
     */
    @ApiModelProperty(value = "节假日日期 yyyy-MM-dd")
    private Date date;

    /**
     * 实际放假日期（可能与节假日日期不同）
     */
    @ApiModelProperty(value = "实际放假日期（可能与节假日日期不同）yyyy-MM-dd")
    private Date observed;

    /**
     * 是否为公共假期
     */
    @ApiModelProperty(value = "是否为公共假期")
    private Boolean isPublic;

    /**
     * 星期名称（例如：周一、周二等）
     */
    @ApiModelProperty(value = "星期名称（例如：Wednesday ）")
    private String weekdayName;

    /**
     * 星期数字表示（1-7，对应周一到周日）
     */
    @ApiModelProperty(value = "星期数字表示（1-7，对应周一到周日）")
    private Integer weekdayNumeric;
}
