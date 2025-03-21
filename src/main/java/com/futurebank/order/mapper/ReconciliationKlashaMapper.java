package com.futurebank.order.mapper;

import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.ReconciliationKlashaEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (ReconciliationKlasha)表数据库访问层
 *
 * @author ben
 * @since 2024-11-05 16:55:09
 */
public interface ReconciliationKlashaMapper extends BaseMapper<ReconciliationKlashaEntity> {
    @Select("select * from tb_reconciliation_klasha where is_processed = 0 and settledStatus='SETTLED' limit 5000")
    List<ReconciliationKlashaEntity> getReconciliationKlashaList();
    @Select("select * from tb_reconciliation_klasha where transaction_tnxref = #{s}")
    ReconciliationKlashaEntity getReconciliationKlashaByTransactionTnxref(String s);
}
