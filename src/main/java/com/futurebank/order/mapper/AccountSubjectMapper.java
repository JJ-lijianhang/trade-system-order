package com.futurebank.order.mapper;

import com.futurebank.order.entity.AccountSubjectEntity;
import com.futurebank.order.mapper.base.BaseMapper;

import java.util.List;

public interface AccountSubjectMapper extends BaseMapper<AccountSubjectEntity> {

    /**
     * 查询所有的会计科目
     * @return
     */
    List<AccountSubjectEntity> queryAll();
}