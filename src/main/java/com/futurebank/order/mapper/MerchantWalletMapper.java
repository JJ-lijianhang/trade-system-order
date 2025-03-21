package com.futurebank.order.mapper;

import com.futurebank.pojo.vo.TradeBeanReq;
import com.futurebank.order.entity.MerchantWalletEntity;
import com.futurebank.order.entity.MerchantWalletSubEntity;
import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.vo.AccountBalanceVo;
import com.futurebank.order.vo.BalanceChangeVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 商户资金账户表(MerchantWallet)表数据库访问层
 *
 * @author ben
 * @since 2024-06-01 20:44:12
 */
public interface MerchantWalletMapper extends BaseMapper<MerchantWalletEntity> {

    @Select("select * from tb_merchant_wallet where merchant_id = #{merchantId}")
    List<MerchantWalletEntity> getMerchantWalletByMerchantId(Long merchantId);

//    @Select("select * from tb_merchant_wallet where merchant_id = #{merchantId} and currency = #{currency}")
    MerchantWalletEntity getMerchantWalletByMerchantIdAndCurrency(@Param("merchantId") Long merchantId, @Param("currency") String currency);

    @Update("update tb_merchant_wallet set balance= balance + #{money}, recharge_balance = recharge_balance + #{money}, version = version + 1 where merchant_id = #{merchantId} and currency = #{currency} and version = #{version}")
    int updateMerchantWalletAddRechargeBalance(TradeBeanReq req);

    @Update("update tb_merchant_wallet set balance = balance + #{money}, acquiring_balance = acquiring_balance +  #{money} , version = version + 1 where merchant_id = #{merchantId} and currency = #{currency} and version = #{version} ")
    int updateMerchantWalletAddAcquiringBalance(TradeBeanReq req);

    @Update("update tb_merchant_wallet set balance = balance + #{money} , collection_balance = collection_balance + #{money} , version = version + 1 where merchant_id = #{merchantId} and currency = #{currency} and version = #{version} ")
    int updateMerchantWalletAddCollectionBalance(TradeBeanReq req);

    @Update("update tb_merchant_wallet set balance = balance + #{money} ,  freeze_amount = freeze_amount + #{money} , version = version + 1 where merchant_id = #{merchantId} and currency = #{currency} and version = #{version} ")
    int updateMerchantWalletAddFrozenBalance(TradeBeanReq req);

//    @Update("update tb_merchant_wallet set balance = balance - #{money} ,  freeze_amount = freeze_amount + #{money} , version = version + 1 where merchant_id = #{merchantId} and currency = #{currency} and version = #{version} ")
//    int updateMerchantWalletAddFrozenBalanceOld(TradeBeanReq req);

//    @Update("update tb_merchant_wallet set  pending_exchange = pending_exchange + #{money} , version = version + 1 where merchant_id = #{merchantId} and currency = #{currency} and version = #{version} ")
//    int updateMerchantWalletAddPendingExchange(TradeBeanReq req);


    @Update("update tb_merchant_wallet set balance = balance + #{total} , acquiring_balance = acquiring_balance +  #{moneySub}, collection_balance = collection_balance + #{money} , version = version + 1 where merchant_id = #{merchantId} and currency = #{currency} and version = #{version} ")
    int updateCollectionBalance(BalanceChangeVo req);


    /**
     * 更新在途金额
     * @param req
     * @return
     */
    int updateWalletPendingBalance(BalanceChangeVo req);

    /**
     * 查询在途钱包
     * @param merchantId
     * @param currency
     * @return
     */
    MerchantWalletSubEntity queryPendingWalletByMerchantIdAndCurrency(@Param("merchantId") Long merchantId, @Param("currency") String currency, @Param("walletCode") String walletCode);

    /**
     * 新增在途钱包
     * @param entity
     * @return
     */
    int addMerchantPendingWallet(MerchantWalletSubEntity entity);


    /**
     * 根据商户号和币种查询账户余额
     * @param merchantId
     * @param currency
     * @return
     */
    List<AccountBalanceVo> queryBalance(@Param("merchantId") Long merchantId, @Param("currency") String currency);
}
