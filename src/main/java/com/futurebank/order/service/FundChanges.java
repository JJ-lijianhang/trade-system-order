package com.futurebank.order.service;

import com.futurebank.order.entity.MerchantChargeEntity;
import com.futurebank.order.vo.BalanceChangeVo;
import com.futurebank.pojo.base.CommonResp;

import java.math.BigDecimal;

public interface FundChanges {

    /**
     * 商户钱包资金变动
     * @param vo
     * @return
     */
    CommonResp merchantWalletChanges(BalanceChangeVo vo);

    /**
     * 商户钱包新增
     * @param req
     * @return
     */
    int addMerchantWallet(BalanceChangeVo req);

     /**
     * 商户钱包新增-在途钱包
     * @param req
     * @return
     */
    int addMerchantPendingWallet(BalanceChangeVo req);

    /**
     * 转换交易请求为商户钱包变动记录
     * @param tradeBeanReq
     * @param ioldmoney
     * @param ioldmoneyTotal
     * @return
     */
    MerchantChargeEntity convertTradeBeanReqToUserChargeRecord(BalanceChangeVo tradeBeanReq, BigDecimal ioldmoney, BigDecimal ioldmoneyTotal);
}
