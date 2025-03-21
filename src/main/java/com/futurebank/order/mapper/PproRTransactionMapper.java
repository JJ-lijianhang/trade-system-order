package com.futurebank.order.mapper;

import com.futurebank.order.entity.PproRTransactionEntity;
import com.futurebank.order.mapper.base.BaseMapper;

import java.util.List;

/**
 * 交易记录表(PproRTransaction)表数据库访问层
 *
 * @author ben
 * @since 2024-05-26 13:51:18
 */
public interface PproRTransactionMapper extends BaseMapper<PproRTransactionEntity> {
    /**
     * 查询未处理数据
     * @return
     */
    List<PproRTransactionEntity> selectByNonProcess();
}
