package com.futurebank.order.mapper;

import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.PproSTransactionEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 商户交易表(PproSTransaction)表数据库访问层
 *
 * @author ben
 * @since 2024-05-21 14:52:49
 */
public interface PproSTransactionMapper extends BaseMapper<PproSTransactionEntity> {
//    @Select("select * from tb_ppro_s_transaction where is_processed = 0 and merchant_id = 'FUTUREBANKLITRICYONSLL9709972506' limit 1000")
    @Select("select * from tb_ppro_s_transaction where is_processed = 0 and !(merchant_id = '' or merchant_id is null) limit 1000")
    List<PproSTransactionEntity> selectByNonProcess();
}
