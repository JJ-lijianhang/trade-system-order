package com.futurebank.order.mapper.base;


import com.futurebank.order.config.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Mapper
public interface BaseMapper<T> {

    @DS("slave")
    List<T> list(T record);

    int insert(T record);

    int updateById(T record);

    List<T> listByDate(@Param("record") T record, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<T> queryByPage(T record, PageRequest pageRequest);

    int update(T record);

    int deleteById(Long id);

    <T> T queryById(Long id);

    int count(T record);

    int insertBatch(List<T> record);

    int insertOrUpdateBatch(List<T> record);



}
