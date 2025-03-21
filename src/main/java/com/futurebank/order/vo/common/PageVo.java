package com.futurebank.order.vo.common;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页列表 VO
 * @author wangxin
 * @date 2024/03/28
 */
@Data
@ApiModel(value = "PageVo", description = "分页列表 VO")
public class PageVo<T,V> implements Serializable {

    private static final long serialVersionUID = -1710273706052960025L;

    @ApiModelProperty(value = "列表")
    private List<T> list;

    @ApiModelProperty(value = "当前页")
    private int pageNum;

    @ApiModelProperty(value = "条数")
    private int pageSize;

    @ApiModelProperty(value = "总条数")
    private Long total;

    @ApiModelProperty(value = "列表汇总")
    private V totalPage;

    public PageVo() {}

    public PageVo(List<T> list, Integer pageNum, Integer pageSize, Long total) {
        this.list = list;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
    }

    public PageVo(List<T> list, V totalPage, Integer pageNum, Integer pageSize, Long total) {
        this.list = list;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.totalPage = totalPage;
    }

    public PageVo(Page<T> pageList) {
        this.list = pageList;
        this.pageNum = pageList.getPageNum();
        this.pageSize = pageList.getPageSize();
        this.total = pageList.getTotal();
    }

    public PageVo(PageInfo<T> pageList) {
        this.list = pageList.getList();
        this.pageNum = pageList.getPageNum();
        this.pageSize = pageList.getPageSize();
        this.total = pageList.getTotal();
    }


}
