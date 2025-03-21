package com.futurebank.order.mapper;

import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.PproRRefundsEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 退款记录表(PproRRefunds)表数据库访问层
 *
 * @author ben
 * @since 2024-05-26 13:51:17
 */
public interface PproRRefundsMapper extends BaseMapper<PproRRefundsEntity> {
    @Select("select * from tb_ppro_r_refunds  where is_processed = 0")
    List<PproRRefundsEntity> selectByNonProcess();
}
