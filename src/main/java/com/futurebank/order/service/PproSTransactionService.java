package com.futurebank.order.service;

import com.futurebank.order.service.base.BaseService;
import com.futurebank.order.mapper.PproSTransactionMapper;
import com.futurebank.order.entity.PproSTransactionEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 商户交易表(PproSTransaction)表Service
 *
 * @author ben
 * @since 2024-05-21 14:52:49
 */
@Service
public class PproSTransactionService extends BaseService<PproSTransactionMapper, PproSTransactionEntity> {

    public List<PproSTransactionEntity> selectByNonProcess() {
        return baseMapper.selectByNonProcess();
    }
}
