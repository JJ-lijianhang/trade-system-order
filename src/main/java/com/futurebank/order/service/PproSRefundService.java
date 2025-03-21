package com.futurebank.order.service;

import com.futurebank.order.service.base.BaseService;
import com.futurebank.order.mapper.PproSRefundMapper;
import com.futurebank.order.entity.PproSRefundEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 费用退款记录表(PproSRefund)表Service
 *
 * @author ben
 * @since 2024-05-21 14:52:48
 */
@Service
public class PproSRefundService extends BaseService<PproSRefundMapper, PproSRefundEntity> {

    public List<PproSRefundEntity> selectByNonProcess() {
        return baseMapper.selectByNonProcess();
    }
}
