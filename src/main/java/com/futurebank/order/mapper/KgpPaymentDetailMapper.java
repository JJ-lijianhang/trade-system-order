package com.futurebank.order.mapper;

import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.KgpPaymentDetailEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (KgpPaymentDetail)表数据库访问层
 *
 * @author ben
 * @since 2024-12-02 20:17:02
 */
public interface KgpPaymentDetailMapper extends BaseMapper<KgpPaymentDetailEntity> {
    @Select("select * from tb_kgp_payment_detail where is_processed=0 ")
    List<KgpPaymentDetailEntity> getReconciliationKgpList();
    @Select("select * from tb_kgp_payment_detail where partnerPaymentID=#{s}")
    KgpPaymentDetailEntity getkgpPaymentDetailByTransactionId(String s);
}
