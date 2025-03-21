package com.futurebank.order.service;

import com.futurebank.order.service.base.BaseService;
import com.futurebank.order.mapper.PproRRefundSucceededMapper;
import com.futurebank.order.entity.PproRRefundSucceededEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 基于提供文件内容生成的退款成功表(PproRRefundSucceeded)表Service
 *
 * @author ben
 * @since 2024-05-21 14:52:41
 */
@Service
public class PproRRefundSucceededService extends BaseService<PproRRefundSucceededMapper, PproRRefundSucceededEntity> {

    public List<PproRRefundSucceededEntity> selectByNonProcess() {
        return baseMapper.selectByNonProcess();
    }
}
