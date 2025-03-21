package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BaseEntity", description = "base")
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1879445038256169930L;

	/**
	 * 主键id
	 */
	@ApiModelProperty(value = "主键id", example = "1")
	private Integer id;

	/**
	 * 乐观锁
	 */
	@ApiModelProperty(value = "乐观锁", example = "1")
	private Long revision;

	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人", example = "123")
	private Long createdBy;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间", example = "2023-10-01 12:00:00")
	private Date createdTime;

	/**
	 * 更新人
	 */
	@ApiModelProperty(value = "更新人", example = "1234567890")
	private Long updatedBy;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间", example = "2023-10-01 12:00:00")
	private Date updatedTime;
}
