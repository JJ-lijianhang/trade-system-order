package com.futurebank.order.service;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

import com.futurebank.order.entity.ServerConfigEntity;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.List;

@Slf4j
@Component
public class IdGeneratorService {


    @Autowired
    private ServerConfigService serverConfigService;

    private long serverId = 0;

    private Snowflake snowflake;
    @PostConstruct
    public void init() throws Exception {
        serverId = Long.parseLong(getServerId());
        snowflake = IdUtil.createSnowflake(0, serverId);
    }

    public synchronized long snowFlake(final Long businessId) {
        return snowflake.nextId();
    }


    private String getServerId() throws Exception {

        InetAddress ipAddress = InetAddress.getLocalHost();

        ServerConfigEntity serverConfig = new ServerConfigEntity();
        serverConfig.setServerIp(ipAddress.getHostAddress());
        log.info("获取服务器ip地址:" + ipAddress.getHostAddress());
        List<ServerConfigEntity> serverConfigList = serverConfigService.list(serverConfig);
        if (serverConfigList == null || serverConfigList.size() == 0) {
            throw new RuntimeException();
        }

        if (serverConfigList.size() > 1) {
            throw new RuntimeException();
        }

        return serverConfigList.get(0).getServerId();
    }




}
