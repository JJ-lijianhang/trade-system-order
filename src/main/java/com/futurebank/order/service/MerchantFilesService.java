package com.futurebank.order.service;

import com.futurebank.order.entity.MerchantFilesEntity;
import com.futurebank.order.mapper.MerchantFilesMapper;
import com.futurebank.order.service.base.BaseService;
import org.springframework.stereotype.Service;


/**
 * 商户对账和结算文件表(MerchantFiles)表Service
 *
 * @author ben
 * @since 2024-06-08 14:49:33
 */
@Service
public class MerchantFilesService extends BaseService<MerchantFilesMapper, MerchantFilesEntity> {

    int deleteByMerchantIdAndPeriod(Long id, String period, String type) {
        return this.baseMapper.deleteByMerchantIdAndPeriod(id, period, type);
    }
}
