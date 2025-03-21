package com.futurebank.order.mapper;

import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.PproRRefundSucceededEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 基于提供文件内容生成的退款成功表(PproRRefundSucceeded)表数据库访问层
 *
 * @author ben
 * @since 2024-05-21 14:52:41
 */
public interface PproRRefundSucceededMapper extends BaseMapper<PproRRefundSucceededEntity> {
    @Select("select * from tb_ppro_r_refund_succeeded where is_processed = 0")
    List<PproRRefundSucceededEntity> selectByNonProcess();
}
