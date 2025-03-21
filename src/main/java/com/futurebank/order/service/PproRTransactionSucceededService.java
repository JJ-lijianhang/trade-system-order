package com.futurebank.order.service;

import com.futurebank.order.service.base.BaseService;
import com.futurebank.order.mapper.PproRTransactionSucceededMapper;
import com.futurebank.order.entity.PproRTransactionSucceededEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 成功的交易记录表(PproRTransactionSucceeded)表Service
 *
 * @author ben
 * @since 2024-05-21 14:52:47
 */
@Service
public class PproRTransactionSucceededService extends BaseService<PproRTransactionSucceededMapper, PproRTransactionSucceededEntity> {

    public List<PproRTransactionSucceededEntity> selectByNonProcess() {
        return baseMapper.selectByNonProcess();

    }
}
