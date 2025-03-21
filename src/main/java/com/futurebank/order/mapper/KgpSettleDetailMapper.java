package com.futurebank.order.mapper;

import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.KgpSettleDetailEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (KgpSettleDetail)表数据库访问层
 *
 * @author ben
 * @since 2024-12-02 20:16:55
 */
public interface KgpSettleDetailMapper extends BaseMapper<KgpSettleDetailEntity> {
    @Select("select * from tb_kgp_settle_detail where is_processed=0")
    List<KgpSettleDetailEntity> getSettlementKgpList();
    @Select("select * from tb_kgp_settle_detail where partnerPaymentID=#{s}")
    KgpSettleDetailEntity getKgpSettleDetailByTransactionId(String s);
}
