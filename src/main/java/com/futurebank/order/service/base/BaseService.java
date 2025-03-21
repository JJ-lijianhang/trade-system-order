package com.futurebank.order.service.base;


import com.futurebank.order.mapper.base.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseService<M extends BaseMapper<T>, T> {

    @Autowired
    protected M baseMapper;

    public List<T> list(T record) {
        return baseMapper.list(record);
    }

    public List<T> listByDate(T record, String startDate, String endDate) {
        return baseMapper.listByDate(record, startDate, endDate);
    }

    public boolean add(T record) {
        return baseMapper.insert(record) > 0;
    }

    public boolean insertOrUpdateBatch(List<T> record) {
        return baseMapper.insertOrUpdateBatch(record) > 0;
    }

    public boolean updateById(T record) {
        return baseMapper.updateById(record) > 0;
    }


    public List<T> queryByPage(T record, PageRequest pageRequest) {
        return baseMapper.queryByPage(record, pageRequest);
    }

    public int update(T record) {
        return baseMapper.update(record);
    }


    public T queryById(Long id) {
        return baseMapper.queryById(id);
    }

    public int deleteById(Long id) {
        return baseMapper.deleteById(id);
    }

    public int insertBatch(List<T> record) {
        return baseMapper.insertBatch(record);
    }

    public int count(T record) {
        return baseMapper.count(record);
    }





}
