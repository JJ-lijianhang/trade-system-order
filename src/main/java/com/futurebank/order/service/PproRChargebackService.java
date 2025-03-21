package com.futurebank.order.service;

import com.futurebank.order.service.base.BaseService;
import com.futurebank.order.mapper.PproRChargebackMapper;
import com.futurebank.order.entity.PproRChargebackEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 退单记录表(PproRChargeback)表Service
 *
 * @author ben
 * @since 2024-05-23 17:21:53
 */
@Service
public class PproRChargebackService extends BaseService<PproRChargebackMapper, PproRChargebackEntity> {
    @Autowired
    PproRChargebackMapper mapper;

    public List<PproRChargebackEntity> selectByNonProcess() {
        return mapper.selectByNonProcess();
    }
}
