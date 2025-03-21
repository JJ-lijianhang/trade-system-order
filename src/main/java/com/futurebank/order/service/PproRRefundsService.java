package com.futurebank.order.service;

import com.futurebank.order.service.base.BaseService;
import com.futurebank.order.mapper.PproRRefundsMapper;
import com.futurebank.order.entity.PproRRefundsEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 退款记录表(PproRRefunds)表Service
 *
 * @author ben
 * @since 2024-05-26 13:51:17
 */
@Service
public class PproRRefundsService extends BaseService<PproRRefundsMapper, PproRRefundsEntity> {

    public List<PproRRefundsEntity> selectByNonProcess() {
        return this.baseMapper.selectByNonProcess();
    }
}
