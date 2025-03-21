package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AccountSubjectEntity", description = "账户系统-会计科目")
public class AccountSubjectEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -7747574427917028266L;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id", example = "12")
    private Long parentId;

    /**
     * 科目级别
     */
    @ApiModelProperty(value = "科目级别", example = "1")
    private Integer level;

    /**
     * 科目编码
     */
    @ApiModelProperty(value = "科目编码", example = "1001")
    private String code;

    /**
     * 科目名称
     */
    @ApiModelProperty(value = "科目名称", example = "现金")
    private String name;

    /**
     * 科目类别 D借方，C贷方
     */
    @ApiModelProperty(value = "科目类别 D借方，C贷方", example = "D")
    private String type;

    /**
     * 科目描述
     */
    @ApiModelProperty(value = "科目描述", example = "用于记录现金的增减")
    private String desc;

}