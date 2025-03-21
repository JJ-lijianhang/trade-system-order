package com.futurebank.order.service;

import com.futurebank.order.service.base.BaseService;
import com.futurebank.order.mapper.PproSAggregateMapper;
import com.futurebank.order.entity.PproSAggregateEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 商户交易汇总表(PproSAggregate)表Service
 *
 * @author ben
 * @since 2024-05-29 17:18:19
 */
@Service
public class PproSAggregateService extends BaseService<PproSAggregateMapper, PproSAggregateEntity> {

    public List<PproSAggregateEntity> selectBySettlementDate(String settlementDate) {
        return this.baseMapper.selectBySettlementDate(settlementDate);
    }
}
