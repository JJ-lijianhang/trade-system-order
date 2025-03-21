package com.futurebank.order.service;

import com.futurebank.order.service.base.BaseService;
import com.futurebank.order.mapper.MsConfigMapper;
import com.futurebank.order.entity.MsConfigEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 微服务鉴权(MsConfig)表Service
 *
 * @author ben
 * @since 2024-09-23 15:18:26
 */
@Service
public class MsConfigService extends BaseService<MsConfigMapper, MsConfigEntity> {

    public MsConfigEntity getAllowedIps() {
        return this.baseMapper.getAllowedIps();
    }
}
