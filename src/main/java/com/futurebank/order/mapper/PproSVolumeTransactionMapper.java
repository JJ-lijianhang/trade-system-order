package com.futurebank.order.mapper;

import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.PproSVolumeTransactionEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 批量交易记录表(PproSVolumeTransaction)表数据库访问层
 *
 * @author ben
 * @since 2024-05-21 14:52:50
 */
public interface PproSVolumeTransactionMapper extends BaseMapper<PproSVolumeTransactionEntity> {
//    @Select("select * from tb_ppro_s_volume_transaction where is_processed = 0 and merchant_id = 'FUTUREBANKLITRICYONSLL9709972506' limit 1000 ")
    @Select("select * from tb_ppro_s_volume_transaction where is_processed = 0 and !(merchant_id = '' or merchant_id is null)")
    List<PproSVolumeTransactionEntity> selectByNonProcess();
}
