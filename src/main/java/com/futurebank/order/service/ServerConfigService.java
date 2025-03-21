package com.futurebank.order.service;

import com.futurebank.order.service.base.BaseService;
import com.futurebank.order.mapper.ServerConfigMapper;
import com.futurebank.order.entity.ServerConfigEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * 分布式部署配置表(ServerConfig)表Service
 *
 * @author ben
 * @since 2024-04-01 10:59:07
 */
@Service
public class ServerConfigService extends BaseService<ServerConfigMapper, ServerConfigEntity> {

}
