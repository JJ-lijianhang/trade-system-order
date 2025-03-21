package com.futurebank.order.service;

import com.futurebank.order.service.base.BaseService;
import com.futurebank.order.mapper.PproSVolumeTransactionMapper;
import com.futurebank.order.entity.PproSVolumeTransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 批量交易记录表(PproSVolumeTransaction)表Service
 *
 * @author ben
 * @since 2024-05-21 14:52:50
 */
@Service
public class PproSVolumeTransactionService extends BaseService<PproSVolumeTransactionMapper, PproSVolumeTransactionEntity> {
    @Autowired
    private PproSVolumeTransactionMapper pproSVolumeTransactionMapper;
    public List<PproSVolumeTransactionEntity> selectByNonProcess() {
        return pproSVolumeTransactionMapper.selectByNonProcess();
    }
}
