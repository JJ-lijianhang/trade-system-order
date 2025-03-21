package com.futurebank.order.mapper;

import com.futurebank.order.entity.MerchantChargeEntity;
import com.futurebank.order.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 商户资金流水表(MerchantCharge)表数据库访问层
 *
 * @author ben
 * @since 2024-06-01 20:44:11
 */
public interface MerchantChargeMapper extends BaseMapper<MerchantChargeEntity> {
    @Select("select * from tb_merchant_charge where reference_no = #{platformOrderNo} and charge_type = #{chargeType} and wallet_type = #{walletType} and charge_event_type = #{chargeEventType}")
    List<MerchantChargeEntity> getMerchantChargeByOrderNo1(@Param("platformOrderNo") String platformOrderNo,
                                                           @Param("chargeType") Integer chargeType,
                                                           @Param("walletType") Integer walletType,
                                                           @Param("chargeEventType") Integer chargeEventType);

    //    @Select("select * from tb_merchant_charge where reference_no = #{platformOrderNo} and charge_type = #{chargeType} and wallet_type = #{walletType} ")
    @Select("select * from tb_merchant_charge where reference_no = #{platformOrderNo} and imoney < 0 ")
    List<MerchantChargeEntity> getMerchantChargeByOrderNo(@Param("platformOrderNo") String platformOrderNo,
                                                          @Param("chargeType") Integer chargeType,
                                                          @Param("walletType") Integer walletType);
}