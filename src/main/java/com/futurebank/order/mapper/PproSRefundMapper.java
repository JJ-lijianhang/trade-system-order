package com.futurebank.order.mapper;

import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.PproSRefundEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 费用退款记录表(PproSRefund)表数据库访问层
 *
 * @author ben
 * @since 2024-05-21 14:52:48
 */
public interface PproSRefundMapper extends BaseMapper<PproSRefundEntity> {
//    @Select("select * from tb_ppro_s_refund where is_processed = 0 and merchant_id = 'FUTUREBANKLITRICYONSLL9709972506' limit 1000")
    @Select("select * from tb_ppro_s_refund where is_processed = 0 and !(merchant_id = '' or merchant_id is null) limit 1000")
    List<PproSRefundEntity> selectByNonProcess();
}
