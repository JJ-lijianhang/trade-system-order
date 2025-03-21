package com.futurebank.order.service;

import com.futurebank.order.entity.MerchantChargeEntity;
import com.futurebank.order.entity.MerchantWalletEntity;
import com.futurebank.order.mapper.MerchantChargeMapper;
import com.futurebank.order.mapper.MerchantWalletMapper;
import com.futurebank.order.service.base.BaseService;
import com.futurebank.pojo.base.CommonResp;
import com.futurebank.pojo.enums.MerchantChargeEventEnum;
import com.futurebank.pojo.enums.MerchantChargeTypeEnum;
import com.futurebank.pojo.enums.MerchantWalletEnum;
import com.futurebank.pojo.exception.ChangeAmountException;
import com.futurebank.pojo.exception.CustomException;
import com.futurebank.pojo.vo.MerchantBalanceQueryRequest;
import com.futurebank.pojo.vo.MerchantBalanceQueryResponse;
import com.futurebank.pojo.vo.TradeBeanReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 商户资金账户表(MerchantWallet)表Service
 *
 * @author ben
 * @since 2024-06-01 20:44:12
 */
@Service
@Slf4j
public class MerchantWalletService extends BaseService<MerchantWalletMapper, MerchantWalletEntity> {

    @Autowired
    MerchantWalletMapper merchantWalletMapper;


    @Transactional(rollbackFor = {Exception.class})
    @Retryable(value = ChangeAmountException.class, maxAttempts = 10, backoff = @Backoff(delay = 1000))
    public CommonResp addMerchantAcctMoneyCommon(TradeBeanReq tradeBeanReq) {
        log.info("addMerchantAcctMoneyCommon tradeBeanReq={}", tradeBeanReq);

        if (tradeBeanReq == null) {
            return CommonResp.fail("90001", "参数为空");
        }
        if (MerchantWalletEnum.冻结账户.getCode() == tradeBeanReq.getWalletType()) {
            return CommonResp.fail("90004", "该函数不支持welletType");
        }
        Long merchantId = tradeBeanReq.getMerchantId();
        String currency = tradeBeanReq.getCurrency();
        Integer chargetype = tradeBeanReq.getChargeType();
        Integer walletType = tradeBeanReq.getWalletType();
        Integer merchantChargeTEventype = tradeBeanReq.getChargeEventType();
        String remark = tradeBeanReq.getRemark();
        BigDecimal money = tradeBeanReq.getMoney() == null ? BigDecimal.ZERO : tradeBeanReq.getMoney();
        BigDecimal ioldmoney = BigDecimal.ZERO;
        if (money.doubleValue() <= 0.0d) {
            log.error("金额不能小于等于0  merchantid={}  money={} tradeBeanReq={}", merchantId, money, tradeBeanReq);
            return CommonResp.fail("90002", "金额小于0");
        }

        MerchantWalletEntity merchantWalletEntity = this.merchantWalletMapper.getMerchantWalletByMerchantIdAndCurrency(merchantId, currency);
        if (merchantWalletEntity == null) {
            int result = addMerchantWallet(tradeBeanReq);
            if (result != 1) {
                throw new ChangeAmountException("0", "添加商户钱包失败-回滚处理");
            }
            merchantWalletEntity = this.merchantWalletMapper.getMerchantWalletByMerchantIdAndCurrency(merchantId, currency);
        }

        Long version = merchantWalletEntity.getVersion();
        if (MerchantWalletEnum.收单账户.getCode() == walletType) {
            ioldmoney = merchantWalletEntity.getAcquiringBalance();
        } else if (MerchantWalletEnum.收款账户.getCode() == walletType) {
            ioldmoney = merchantWalletEntity.getCollectionBalance();
        } else if (MerchantWalletEnum.充值账户.getCode() == walletType) {
            ioldmoney = merchantWalletEntity.getRechargeBalance();
        } else if (MerchantWalletEnum.冻结账户.getCode() == walletType) {
            ioldmoney = merchantWalletEntity.getFreezeAmount();
        } else {
            return CommonResp.fail("90003", "未知账户类型");
        }
        if (chargetype == MerchantChargeTypeEnum.出账.getCode()) {
            tradeBeanReq.setMoney(money.multiply(new BigDecimal(-1)));
        }
        BigDecimal ioldmoneyTotal = merchantWalletEntity.getBalance();
        TradeBeanReq req = new TradeBeanReq();
        req.setMerchantId(merchantId);
        req.setCurrency(currency);
        req.setMoney(tradeBeanReq.getMoney());
        int result = -1;
        req.setVersion(version);
        if (MerchantWalletEnum.收单账户.getCode() == walletType) {
            result = this.merchantWalletMapper.updateMerchantWalletAddAcquiringBalance(req);
        } else if (MerchantWalletEnum.收款账户.getCode() == walletType) {
            result = this.merchantWalletMapper.updateMerchantWalletAddCollectionBalance(req);
        } else if (MerchantWalletEnum.充值账户.getCode() == walletType) {
            result = this.merchantWalletMapper.updateMerchantWalletAddRechargeBalance(req);
        }
        MerchantChargeEntity merchantChargeEntity = convertTradeBeanReqToUserChargeRecord(tradeBeanReq, ioldmoney, ioldmoneyTotal);

        int chargeResult = this.merchantChargeMapper.insert(merchantChargeEntity);

        if (result <= 0 || chargeResult <= 0) {
            log.error("变更金额失败 merchantid={} walletType={} ioldmoney={} money={} tradeBeanReq={}", merchantId, walletType, ioldmoney, money, tradeBeanReq);
            throw new ChangeAmountException("0", "变更金额失败-回滚处理");
        }

        return CommonResp.ok();
    }


    public CommonResp merchantMoneyChange(TradeBeanReq tradeBeanReq) {
        log.info("addMerchantAcctMoneyCommon tradeBeanReq={}", tradeBeanReq);
        if (tradeBeanReq == null) {
            log.info("数据为空 tradeBeanReq ={} ", tradeBeanReq);
            throw new CustomException("400", "参数为空");
        }
        if (!tradeBeanReq.isValid()) {
            log.info("数据异常 tradeBeanReq ={} ", tradeBeanReq);
            throw new CustomException("401", "提交数据异常");
        }
        Long merchantId = tradeBeanReq.getMerchantId();
        String currency = tradeBeanReq.getCurrency();
        Integer chargetype = tradeBeanReq.getChargeType();
        Integer walletType = tradeBeanReq.getWalletType();
        Integer merchantChargeTEventype = tradeBeanReq.getChargeEventType();
        String remark = tradeBeanReq.getRemark();
        BigDecimal money = tradeBeanReq.getMoney() == null ? BigDecimal.ZERO : tradeBeanReq.getMoney();
        BigDecimal ioldmoney = BigDecimal.ZERO;

        MerchantWalletEntity merchantWalletEntity = this.merchantWalletMapper.getMerchantWalletByMerchantIdAndCurrency(merchantId, currency);
        if (merchantWalletEntity == null) {
            int result = addMerchantWallet(tradeBeanReq);
            if (result != 1) {
                throw new ChangeAmountException("0", "添加商户钱包失败-回滚处理");
            }
            merchantWalletEntity = this.merchantWalletMapper.getMerchantWalletByMerchantIdAndCurrency(merchantId, currency);
        }

        Long version = merchantWalletEntity.getVersion();
        if (version == null) {
            log.info("基础数据异常，请检查表tb_merchant_wallet merchantId={} currency={}", merchantId, currency);
            throw new CustomException("402", "基础数据异常，请检查表tb_merchant_wallet merchantId=" + merchantId + " currency=" + currency);
        }

        if (MerchantWalletEnum.收单账户.getCode() == walletType) {
            ioldmoney = merchantWalletEntity.getAcquiringBalance();
        } else if (MerchantWalletEnum.收款账户.getCode() == walletType) {
            ioldmoney = merchantWalletEntity.getCollectionBalance();
        } else if (MerchantWalletEnum.充值账户.getCode() == walletType) {
            ioldmoney = merchantWalletEntity.getRechargeBalance();
        } else if (MerchantWalletEnum.冻结账户.getCode() == walletType) {
            ioldmoney = merchantWalletEntity.getFreezeAmount();
        } else {
            throw new CustomException("90003", "未知账户类型");
        }
        if (chargetype == MerchantChargeTypeEnum.出账.getCode()) {
            tradeBeanReq.setMoney(money.multiply(new BigDecimal(-1)));
        }
        BigDecimal ioldmoneyTotal = merchantWalletEntity.getBalance();
        TradeBeanReq req = new TradeBeanReq();
        req.setMerchantId(merchantId);
        req.setCurrency(currency);
        req.setMoney(tradeBeanReq.getMoney());
        int result = -1;
        req.setVersion(version);
        if (MerchantWalletEnum.收单账户.getCode() == walletType) {
            result = this.merchantWalletMapper.updateMerchantWalletAddAcquiringBalance(req);
        } else if (MerchantWalletEnum.收款账户.getCode() == walletType) {
            result = this.merchantWalletMapper.updateMerchantWalletAddCollectionBalance(req);
        } else if (MerchantWalletEnum.充值账户.getCode() == walletType) {
            result = this.merchantWalletMapper.updateMerchantWalletAddRechargeBalance(req);
        } else if (MerchantWalletEnum.冻结账户.getCode() == walletType) {
            result = this.merchantWalletMapper.updateMerchantWalletAddFrozenBalance(req);
        }
        MerchantChargeEntity merchantChargeEntity = convertTradeBeanReqToUserChargeRecord(tradeBeanReq, ioldmoney, ioldmoneyTotal);

        int chargeResult = this.merchantChargeMapper.insert(merchantChargeEntity);

        if (result <= 0 || chargeResult <= 0) {
            log.error("变更金额失败1 merchantid={} walletType={} ioldmoney={} money={} tradeBeanReq={} result={} chargeResult={}", merchantId, walletType, ioldmoney, money, tradeBeanReq, result, chargeResult);
            throw new ChangeAmountException("0", "变更金额失败 result= " + result + " chargeResult=" + chargeResult);
        }

        return CommonResp.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    @Retryable(value = ChangeAmountException.class, maxAttempts = 8, backoff = @Backoff(delay = 300))
    public synchronized CommonResp merchantMoneyChangeTransaction(List<TradeBeanReq> tradeBeanReqs) {
        if (tradeBeanReqs == null)
            return CommonResp.fail("90001", "参数为空");
        if (tradeBeanReqs.size() == 0)
            return CommonResp.fail("90001", "参数为空");
        for (TradeBeanReq tradeBeanReq : tradeBeanReqs) {
            merchantMoneyChange(tradeBeanReq);
        }

        return CommonResp.ok();
    }


    public CommonResp merchantBalanceQuery(MerchantBalanceQueryRequest merchantBalanceQueryRequest) {
        List<MerchantWalletEntity> merchantWalletEntitys = merchantWalletMapper.getMerchantWalletByMerchantId(merchantBalanceQueryRequest.getMerchantId());

        if (merchantWalletEntitys == null || merchantWalletEntitys.isEmpty()) {
            throw new CustomException("90010", "商户不存在");
        }

        List<MerchantBalanceQueryResponse> merchantBalanceQueryResponses = merchantWalletEntitys.stream()
                .map(m -> {
                    MerchantBalanceQueryResponse merchantBalanceQueryResponse = new MerchantBalanceQueryResponse();
                    merchantBalanceQueryResponse.setCurrency(m.getCurrency());
                    merchantBalanceQueryResponse.setBalance(m.getBalance());
                    merchantBalanceQueryResponse.setAcquiringBalance(m.getAcquiringBalance());
                    merchantBalanceQueryResponse.setCollectionBalance(m.getCollectionBalance());
                    return merchantBalanceQueryResponse;
                }).collect(Collectors.toList());

        return CommonResp.ok(merchantBalanceQueryResponses);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Retryable(value = ChangeAmountException.class, maxAttempts = 10, backoff = @Backoff(delay = 1000))
    public CommonResp freezeAcctMoneyCommon(TradeBeanReq tradeBeanReq) {
        log.info("addFreezeAcctMoneyCommon tradeBeanReq={}", tradeBeanReq);
        if (tradeBeanReq == null) {
            return CommonResp.fail("90001", "参数为空");
        }

        Long merchantId = tradeBeanReq.getMerchantId();
        String currency = tradeBeanReq.getCurrency();
        Integer chargetype = tradeBeanReq.getChargeType();
        Integer walletType = tradeBeanReq.getWalletType();
        Integer merchantChargeTEventype = tradeBeanReq.getChargeEventType();
        String remark = tradeBeanReq.getRemark();

        if (walletType != MerchantWalletEnum.冻结账户.getCode()) {
            return CommonResp.fail("90004", "该函数只支持welletType");
        }
        if (chargetype != MerchantChargeTypeEnum.进账.getCode()) {
            return CommonResp.fail("90005", "该函数只支持进账");
        }


        BigDecimal money = tradeBeanReq.getMoney() == null ? BigDecimal.ZERO : tradeBeanReq.getMoney();
        BigDecimal ioldmoney = BigDecimal.ZERO;


        MerchantWalletEntity merchantWalletEntity = this.merchantWalletMapper.getMerchantWalletByMerchantIdAndCurrency(merchantId, currency);
        if (merchantWalletEntity == null) {
            int result = addMerchantWallet(tradeBeanReq);
            if (result != 1) {
                throw new ChangeAmountException("0", "添加商户钱包失败-回滚处理");
            }
            merchantWalletEntity = this.merchantWalletMapper.getMerchantWalletByMerchantIdAndCurrency(merchantId, currency);
        }
        ioldmoney = merchantWalletEntity.getFreezeAmount();
        Long version = merchantWalletEntity.getVersion();
        int result = -1;
        TradeBeanReq req = new TradeBeanReq();
        req.setMerchantId(merchantId);
        req.setCurrency(currency);
        req.setMoney(tradeBeanReq.getMoney());
        req.setVersion(version);
        result = this.merchantWalletMapper.updateMerchantWalletAddFrozenBalance(req);

        if (result <= 0) {
            log.error("变更金额失败 merchantid={} walletType={} ioldmoney={} money={} tradeBeanReq={} result={}", merchantId, walletType, ioldmoney, money, tradeBeanReq, result);
            throw new ChangeAmountException("0", "变更金额失败");
        }

        return CommonResp.ok();
    }

    /**
     * 通过冻结账户退款
     *
     * @param tradeBeanReq
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    @Retryable(value = ChangeAmountException.class, maxAttempts = 10, backoff = @Backoff(delay = 1000))
    public CommonResp FreezeAcctRefund(TradeBeanReq tradeBeanReq) {
        log.info("addMerchantAcctMoneyCommon tradeBeanReq={}", tradeBeanReq);
        if (tradeBeanReq == null) {
            return CommonResp.fail("90001", "参数为空");
//            return false;
        }
        if (MerchantWalletEnum.冻结账户.getCode() == tradeBeanReq.getWalletType()) {
            return CommonResp.fail("90004", "该函数不支持welletType");
        }
        Long merchantId = tradeBeanReq.getMerchantId();
        String currency = tradeBeanReq.getCurrency();
        Integer chargetype = tradeBeanReq.getChargeType();
        Integer walletType = tradeBeanReq.getWalletType();
        Integer merchantChargeTEventype = tradeBeanReq.getChargeEventType();
        String remark = tradeBeanReq.getRemark();
        BigDecimal money = tradeBeanReq.getMoney() == null ? BigDecimal.ZERO : tradeBeanReq.getMoney();
        BigDecimal ioldmoney = BigDecimal.ZERO;
        if (money.doubleValue() <= 0.0d) {
            log.error("金额不能小于等于0  merchantid={}  money={} tradeBeanReq={}", merchantId, money, tradeBeanReq);
            return CommonResp.fail("90002", "金额小于0");
        }

        MerchantWalletEntity merchantWalletEntity = this.merchantWalletMapper.getMerchantWalletByMerchantIdAndCurrency(merchantId, currency);
        if (merchantWalletEntity == null) {
            int result = addMerchantWallet(tradeBeanReq);
            if (result != 1) {
                throw new ChangeAmountException("0", "添加商户钱包失败-回滚处理");
            }
            merchantWalletEntity = this.merchantWalletMapper.getMerchantWalletByMerchantIdAndCurrency(merchantId, currency);
        }

        Long version = merchantWalletEntity.getVersion();
        if (MerchantWalletEnum.收单账户.getCode() == walletType) {
            ioldmoney = merchantWalletEntity.getAcquiringBalance();
        } else if (MerchantWalletEnum.收款账户.getCode() == walletType) {
            ioldmoney = merchantWalletEntity.getCollectionBalance();
        } else if (MerchantWalletEnum.充值账户.getCode() == walletType) {
            ioldmoney = merchantWalletEntity.getRechargeBalance();
        } else if (MerchantWalletEnum.冻结账户.getCode() == walletType) {
            ioldmoney = merchantWalletEntity.getFreezeAmount();
        } else {
            return CommonResp.fail("90003", "未知账户类型");
        }
        if (chargetype == MerchantChargeTypeEnum.出账.getCode()) {
            tradeBeanReq.setMoney(money.multiply(new BigDecimal(-1)));
        }
        BigDecimal ioldmoneyTotal = merchantWalletEntity.getBalance();
        TradeBeanReq req = new TradeBeanReq();
        req.setMerchantId(merchantId);
        req.setCurrency(currency);
        req.setMoney(tradeBeanReq.getMoney());
        int result = -1;
        req.setVersion(version);
        if (MerchantWalletEnum.收单账户.getCode() == walletType) {
            result = this.merchantWalletMapper.updateMerchantWalletAddAcquiringBalance(req);
        } else if (MerchantWalletEnum.收款账户.getCode() == walletType) {
            result = this.merchantWalletMapper.updateMerchantWalletAddCollectionBalance(req);
        } else if (MerchantWalletEnum.充值账户.getCode() == walletType) {
            result = this.merchantWalletMapper.updateMerchantWalletAddRechargeBalance(req);
        } else if (MerchantWalletEnum.冻结账户.getCode() == walletType) {
            result = this.merchantWalletMapper.updateMerchantWalletAddFrozenBalance(req);
        }
        MerchantChargeEntity merchantChargeEntity = null;
        if (chargetype == MerchantChargeTypeEnum.进账.getCode()) {
            merchantChargeEntity = freezeAcctMoneyCommon(tradeBeanReq, ioldmoney, ioldmoneyTotal);
        } else if (chargetype == MerchantChargeTypeEnum.出账.getCode()) {
            merchantChargeEntity = freezeAcctMoneyCommon(tradeBeanReq, ioldmoney, ioldmoneyTotal);
        }

        int chargeResult = this.merchantChargeMapper.insert(merchantChargeEntity);

        if (result <= 0 || chargeResult <= 0) {
            log.error("变更金额失败 merchantid={} walletType={} ioldmoney={} money={} tradeBeanReq={}", merchantId, walletType, ioldmoney, money, tradeBeanReq);
            throw new ChangeAmountException("0", "变更金额失败-回滚处理");
        }

        return CommonResp.ok();
    }

    @Autowired
    MerchantChargeMapper merchantChargeMapper;

    private MerchantChargeEntity freezeAcctMoneyCommon(TradeBeanReq tradeBeanReq, BigDecimal ioldmoney, BigDecimal ioldmoneyTotal) {

        MerchantChargeEntity record = new MerchantChargeEntity();

        record.setProviderId(tradeBeanReq.getProviderId());
        record.setPlatformId(tradeBeanReq.getPlatformId());
        record.setAgentId(tradeBeanReq.getAgentId());
        record.setMerchantId(tradeBeanReq.getMerchantId());
        record.setConsumerId(tradeBeanReq.getConsumerId());
        record.setTradeNo(tradeBeanReq.getTradeNo());
        record.setReferenceNo(tradeBeanReq.getReferenceNo());
        record.setChargeType(tradeBeanReq.getChargeType());
        record.setServiceType(tradeBeanReq.getServiceType());
        record.setChargeEventType(tradeBeanReq.getChargeEventType());
        record.setChargeEventName(MerchantChargeEventEnum.valueOfCode(tradeBeanReq.getChargeEventType()) == null ? null : MerchantChargeEventEnum.valueOfCode(tradeBeanReq.getChargeEventType()).getName());
        record.setWalletType(tradeBeanReq.getWalletType());

        record.setCurrency(tradeBeanReq.getCurrency());
        record.setIoldmoneytotal(ioldmoneyTotal);
        record.setIoldmoney(ioldmoney);
        record.setImoney(tradeBeanReq.getMoney());
        record.setIbalance(ioldmoney.add(tradeBeanReq.getMoney()).setScale(2, BigDecimal.ROUND_HALF_DOWN));

        record.setIbalancetotal(ioldmoneyTotal.subtract(tradeBeanReq.getMoney()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
        record.setCadddate(new Date());
        record.setCmemo(tradeBeanReq.getRemark());

        return record;
    }

    private MerchantChargeEntity convertTradeBeanReqToUserChargeRecord(TradeBeanReq tradeBeanReq, BigDecimal ioldmoney, BigDecimal ioldmoneyTotal) {

        MerchantChargeEntity record = new MerchantChargeEntity();

        record.setProviderId(tradeBeanReq.getProviderId());
        record.setPlatformId(tradeBeanReq.getPlatformId());
        record.setAgentId(tradeBeanReq.getAgentId());
        record.setMerchantId(tradeBeanReq.getMerchantId());
        record.setConsumerId(tradeBeanReq.getConsumerId());
        record.setPaymentMethod(tradeBeanReq.getPayment_method());
        record.setPeriod(tradeBeanReq.getPeriod());
        record.setTradeNo(tradeBeanReq.getTradeNo());
        record.setReferenceNo(tradeBeanReq.getReferenceNo());
        record.setChargeType(tradeBeanReq.getChargeType());
        record.setServiceType(tradeBeanReq.getServiceType());
        record.setChargeEventType(tradeBeanReq.getChargeEventType());
        record.setChargeEventName(MerchantChargeEventEnum.valueOfCode(tradeBeanReq.getChargeEventType()) == null ? null : MerchantChargeEventEnum.valueOfCode(tradeBeanReq.getChargeEventType()).getName());
        record.setWalletType(tradeBeanReq.getWalletType());

        record.setCurrency(tradeBeanReq.getCurrency());
        record.setIoldmoneytotal(ioldmoneyTotal);
        record.setIoldmoney(ioldmoney);
        record.setImoney(tradeBeanReq.getMoney());
        record.setIbalance(ioldmoney.add(tradeBeanReq.getMoney()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
        record.setIbalancetotal(ioldmoneyTotal.add(tradeBeanReq.getMoney()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
        record.setCadddate(new Date());
        record.setCmemo(tradeBeanReq.getRemark());



        return record;
    }

    public int addMerchantWallet(TradeBeanReq req) {
        MerchantWalletEntity merchantWalletEntity = new MerchantWalletEntity();
        merchantWalletEntity.setMerchantId(req.getMerchantId());
        merchantWalletEntity.setCurrency(req.getCurrency());
        merchantWalletEntity.setBalance(BigDecimal.ZERO);
        merchantWalletEntity.setRechargeBalance(BigDecimal.ZERO);
        merchantWalletEntity.setAcquiringBalance(BigDecimal.ZERO);
        merchantWalletEntity.setCollectionBalance(BigDecimal.ZERO);
        merchantWalletEntity.setPendingBalance(BigDecimal.ZERO);
        merchantWalletEntity.setFreezeAmount(BigDecimal.ZERO);
        merchantWalletEntity.setWithdrawalAmount(BigDecimal.ZERO);
        merchantWalletEntity.setVersion(0L);
        merchantWalletEntity.setCreatedTime(new Date());
        merchantWalletEntity.setUpdateTime(new Date());
        int result = this.merchantWalletMapper.insert(merchantWalletEntity);
        return result;
    }

}
