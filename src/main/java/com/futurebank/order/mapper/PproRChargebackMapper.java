package com.futurebank.order.mapper;

import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.PproRChargebackEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 退单记录表(PproRChargeback)表数据库访问层
 *
 * @author ben
 * @since 2024-05-23 17:21:53
 */
public interface PproRChargebackMapper extends BaseMapper<PproRChargebackEntity> {
    @Select("select * from tb_ppro_r_chargeback where is_processed = 0")
    List<PproRChargebackEntity> selectByNonProcess();
}
