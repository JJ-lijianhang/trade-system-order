package com.futurebank.order.mapper;

import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.FileHandleEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * (FileHandle)表数据库访问层
 *
 * @author ben
 * @since 2024-05-21 11:07:14
 */
public interface FileHandleMapper extends BaseMapper<FileHandleEntity> {
    @Select("select * from tb_file_handle where provider_id = #{providerId} and file_name = #{fileName}")
    FileHandleEntity selectByFileName(@Param("providerId") Long providerId, @Param("fileName") String fileName);
}
