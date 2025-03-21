package com.futurebank.order.service;

import com.futurebank.order.service.base.BaseService;
import com.futurebank.order.mapper.PproSVolumeRefundMapper;
import com.futurebank.order.entity.PproSVolumeRefundEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 批量退款记录表(PproSVolumeRefund)表Service
 *
 * @author ben
 * @since 2024-05-21 14:52:49
 */
@Service
public class PproSVolumeRefundService extends BaseService<PproSVolumeRefundMapper, PproSVolumeRefundEntity> {
    @Autowired
    private PproSVolumeRefundMapper pproSVolumeRefundMapper;
    public List<PproSVolumeRefundEntity> selectByNonProcess() {
        return pproSVolumeRefundMapper.selectByNonProcess();
    }
}
