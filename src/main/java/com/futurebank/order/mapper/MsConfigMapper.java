package com.futurebank.order.mapper;

import com.futurebank.order.entity.MsConfigEntity;
import com.futurebank.order.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 微服务鉴权(MsConfig)表数据库访问层
 *
 * @author ben
 * @since 2024-09-23 15:18:26
 */
public interface MsConfigMapper extends BaseMapper<MsConfigEntity> {
    @Select("select * from tb_ms_config where ms_name= 'order' limit 1 ")
    MsConfigEntity getAllowedIps();
}
