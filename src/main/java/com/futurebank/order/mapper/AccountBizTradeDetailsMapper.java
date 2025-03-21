package com.futurebank.order.mapper;

import com.futurebank.order.entity.AccountBizTradeDetailsEntity;
import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.req.TradeDetailsReq;
import com.futurebank.order.vo.BalanceDetailsVo;

import java.util.List;

public interface AccountBizTradeDetailsMapper extends BaseMapper<AccountBizTradeDetailsEntity> {

    /**
     * 查询列表
     * @param record
     * @return
     */
    List<AccountBizTradeDetailsEntity> queryList(AccountBizTradeDetailsEntity record);


    List<BalanceDetailsVo> queryBalanceDetails(TradeDetailsReq req);

}
