package com.futurebank.order.mapper;

import com.futurebank.order.entity.MerchantFilesEntity;
import com.futurebank.order.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * 商户对账和结算文件表(MerchantFiles)表数据库访问层
 *
 * @author ben
 * @since 2024-06-08 14:49:33
 */
public interface MerchantFilesMapper extends BaseMapper<MerchantFilesEntity> {
    @Delete("delete from tb_merchant_files where merchant_id = #{id} and period = #{period} and file_type = #{type}")
    int deleteByMerchantIdAndPeriod(@Param("id") Long id, @Param("period") String period, @Param("type") String type);
//    int deleteByMerchantIdAndPeriod(Long id, String period);
}
