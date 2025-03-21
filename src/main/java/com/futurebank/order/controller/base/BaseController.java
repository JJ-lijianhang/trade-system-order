package com.futurebank.order.controller.base;

import com.futurebank.order.vo.common.PageReq;
import com.github.pagehelper.PageHelper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public abstract class BaseController {


    protected void startPage(PageReq req) {

        int pageNum = req.getPageNum();
        int pageSize = req.getPageSize();

        if (StringUtils.isBlank(req.getOrderByColumn()) || StringUtils.isBlank(req.getIsAsc())) {
            PageHelper.startPage(pageNum, pageSize);
        } else {
            PageHelper.startPage(pageNum, pageSize, req.getOrderByColumn() + " " + req.getIsAsc());
        }
    }



}