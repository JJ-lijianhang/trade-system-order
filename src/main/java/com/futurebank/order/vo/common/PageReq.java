package com.futurebank.order.vo.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class PageReq {
    /**
     * 当前页码
     */
    private int pageNum;

    private int pageSize;

    private String orderByColumn;

    private String isAsc;

    public int getPageNum() {
        if(pageNum <= 0 || pageNum > 100){
            return 1;
        }
        return pageNum;
    }


    public int getPageSize() {
        if(pageSize <= 0 || pageSize > 100){
            return 10;
        }
        return pageSize;
    }
}
