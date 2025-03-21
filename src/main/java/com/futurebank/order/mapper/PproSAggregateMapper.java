package com.futurebank.order.mapper;

import com.futurebank.order.config.DS;
import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.PproSAggregateEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 商户交易汇总表(PproSAggregate)表数据库访问层
 *
 * @author ben
 * @since 2024-05-29 17:18:19
 */
public interface PproSAggregateMapper extends BaseMapper<PproSAggregateEntity> {
    @DS("slave")
    @Select("select * from tb_ppro_s_aggregate where settlement_date = #{settlementDate}")
    List<PproSAggregateEntity> selectBySettlementDate(String settlementDate);
}
