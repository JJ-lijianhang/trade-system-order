package com.futurebank.order.mapper;

import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.PproSChargebackEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 退单记录表(PproSChargeback)表数据库访问层
 *
 * @author ben
 * @since 2024-05-21 14:52:48
 */
public interface PproSChargebackMapper extends BaseMapper<PproSChargebackEntity> {
//    @Select("select * from tb_ppro_s_chargeback where is_processed = 0 and merchant_id = 'FUTUREBANKLITRICYONSLL9709972506' limit 1000")
    @Select("select * from tb_ppro_s_chargeback where is_processed = 0 and !(merchant_id = '' or merchant_id is null) limit 1000")
    List<PproSChargebackEntity> selectByNonProcess();
}
