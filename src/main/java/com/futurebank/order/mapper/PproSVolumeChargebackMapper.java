package com.futurebank.order.mapper;

import com.futurebank.order.entity.PproSVolumeTransactionEntity;
import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.PproSVolumeChargebackEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 批量退单记录表(PproSVolumeChargeback)表数据库访问层
 *
 * @author ben
 * @since 2024-05-21 14:52:49
 */
public interface PproSVolumeChargebackMapper extends BaseMapper<PproSVolumeChargebackEntity> {
//    @Select("select * from tb_ppro_s_volume_chargeback where is_processed = 0 and merchant_id='FUTUREBANKLITRICYONSLL9709972506' limit 1000")
    @Select("select * from tb_ppro_s_volume_chargeback where is_processed = 0 and !(merchant_id = '' or merchant_id is null) limit 1000")
    List<PproSVolumeChargebackEntity> selectByNonProcess();
}
