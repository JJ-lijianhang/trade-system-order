package com.futurebank.order.mapper;

import com.futurebank.order.entity.ChannelRatesEntity;
import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.ProductChannelEntity;
import org.apache.ibatis.annotations.Select;

/**
 * 产品通道表(ProductChannel)表数据库访问层
 *
 * @author ben
 * @since 2024-05-29 11:04:09
 */
public interface ProductChannelMapper extends BaseMapper<ProductChannelEntity> {
    @Select("SELECT * FROM tb_product_channel WHERE id = #{productChannelId}")
    ProductChannelEntity getProductChannel(Long productChannelId);
    @Select("SELECT * FROM tb_product_channel WHERE id = #{productChannelId}")
    ProductChannelEntity getProductChannelByProductId(Long productChannelId);
    @Select("SELECT * FROM tb_channel_rates WHERE id = #{channelId}")
    ChannelRatesEntity getChannelRatesByChannelId(Long channelId);
}
