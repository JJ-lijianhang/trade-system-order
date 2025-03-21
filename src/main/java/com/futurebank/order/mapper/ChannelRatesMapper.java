package com.futurebank.order.mapper;

import com.futurebank.order.config.DS;
import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.ChannelRatesEntity;
import org.apache.ibatis.annotations.Select;

/**
 * 渠道费率表(ChannelRates)表数据库访问层
 *
 * @author ben
 * @since 2024-05-28 18:39:48
 */
public interface ChannelRatesMapper extends BaseMapper<ChannelRatesEntity> {
    @DS("slave")
    @Select("select * from tb_channel_rates where id = #{channelId}")
    ChannelRatesEntity getChannelRates(Integer channelId);
}
