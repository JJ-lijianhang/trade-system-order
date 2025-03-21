package com.futurebank.order.mapper;

import com.futurebank.order.entity.MerchantEntity;
import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.vo.Invoice.MerchantBizInfoVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 商户表(Merchant)表数据库访问层
 *
 * @author ben
 * @since 2024-04-20 19:21:41
 */
public interface MerchantMapper extends BaseMapper<MerchantEntity> {
    @Select("select * from tb_merchant where id = #{merchantId}")
    MerchantEntity getMerchant(String merchantId);

    //    @Select("select * from tb_merchant where istate = 0")
    @Select("select * FROM tb_merchant WHERE datediff(CURRENT_DATE , settlement_date) > settlement_cycle")
    List<MerchantEntity> getMerchantEntityList();

    @Update("update tb_merchant set settlement_date = CURRENT_DATE where id = #{id}")
    void updateMerchantSettlementDate(Long id);

//    TODO 开发调试
    @Select("select * from tb_merchant where istate = 0 and datediff(now(),reconciliation_date) >= 1")
    List<MerchantEntity> getMerchantReciliationList();
    @Update("update tb_merchant set reconciliation_date = CURRENT_DATE where id = #{id}")
    void updateMerchantReconciliation(Long id);


    MerchantBizInfoVo queryMerchantBizInfo(@Param("merchantId") Long merchantId);

}
