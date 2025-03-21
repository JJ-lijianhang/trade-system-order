package com.futurebank.order.service;

import com.futurebank.order.service.base.BaseService;
import com.futurebank.order.mapper.PproSChargebackMapper;
import com.futurebank.order.entity.PproSChargebackEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 退单记录表(PproSChargeback)表Service
 *
 * @author ben
 * @since 2024-05-21 14:52:48
 */
@Service
public class PproSChargebackService extends BaseService<PproSChargebackMapper, PproSChargebackEntity> {

    public List<PproSChargebackEntity> selectByNonProcess() {
        return baseMapper.selectByNonProcess();
    }
}
