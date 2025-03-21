package com.futurebank.order.mapper;

import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.PproRTransactionSucceededEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 成功的交易记录表(PproRTransactionSucceeded)表数据库访问层
 *
 * @author ben
 * @since 2024-05-21 14:52:47
 */
public interface PproRTransactionSucceededMapper extends BaseMapper<PproRTransactionSucceededEntity> {
    @Select("select * from tb_ppro_r_transaction_succeeded where is_processed = 0 limit 1000")
    List<PproRTransactionSucceededEntity> selectByNonProcess();
}
