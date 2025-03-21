package com.futurebank.order.mapper;

import com.futurebank.order.entity.AccountWalletRulesEntity;
import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.vo.AccountWalletRulesVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountWalletRulesMapper extends BaseMapper<AccountWalletRulesEntity> {

    /**
     * 查询账户钱包规则列表
     * @param tradeType
     */
    List<AccountWalletRulesVo> queryAccountWalletRulesList(@Param("tradeType") String tradeType);


    /**
     * 查询全部交易类型
     * @return
     */
    List<AccountWalletRulesVo> queryAll();


    /**
     * 根据商户语言类型查询科目信息
     * @param language
     * @return
     */
    List<AccountWalletRulesVo> queryAccountSubject(@Param("language") String language);

}
