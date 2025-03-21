package com.futurebank.order.service;

import com.futurebank.order.entity.ProductChannelEntity;
import com.futurebank.order.mapper.ProductChannelMapper;
import com.futurebank.order.service.base.BaseService;
import org.springframework.stereotype.Service;


/**
 * 产品通道表(ProductChannel)表Service
 *
 * @author ben
 * @since 2024-05-29 11:04:09
 */
@Service
public class ProductChannelService extends BaseService<ProductChannelMapper, ProductChannelEntity> {

    public ProductChannelEntity getProductChannel(Long productChannelId) {
        return this.baseMapper.getProductChannel(productChannelId);
    }


}
