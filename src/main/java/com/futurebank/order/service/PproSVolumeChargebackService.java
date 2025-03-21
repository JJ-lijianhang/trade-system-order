package com.futurebank.order.service;

import com.futurebank.order.entity.PproSVolumeTransactionEntity;
import com.futurebank.order.service.base.BaseService;
import com.futurebank.order.mapper.PproSVolumeChargebackMapper;
import com.futurebank.order.entity.PproSVolumeChargebackEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 批量退单记录表(PproSVolumeChargeback)表Service
 *
 * @author ben
 * @since 2024-05-21 14:52:49
 */
@Service
public class PproSVolumeChargebackService extends BaseService<PproSVolumeChargebackMapper, PproSVolumeChargebackEntity> {
    @Autowired
    private PproSVolumeChargebackMapper pproSVolumeChargebackMapper;
    public List<PproSVolumeChargebackEntity> selectByNonProcess() {
        return pproSVolumeChargebackMapper.selectByNonProcess();
    }
}
