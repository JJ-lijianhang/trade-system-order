package com.futurebank.order.mapper;

import com.futurebank.order.entity.*;
import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.vo.AutoSettmentVo.FileHandleLogVo;
import com.futurebank.order.vo.AutoSettmentVo.MerchantSettInfoVo;
import com.futurebank.order.vo.AutoSettmentVo.PPRORTransactionVo;
import com.futurebank.order.vo.Invoice.MerchantInfoVo;
import com.futurebank.order.vo.*;
import com.futurebank.order.vo.settlement.AggregateMerchantSettlementVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MerchantSettmMapper extends BaseMapper<MerchantSettmEntity> {


    /**
     * 查询当期满足的结算渠道
     * @param mId
     * @param period
     * @return
     */
    List<MerchantSettlementEntity> querySettlementProvider(@Param("merchantId") Long mId,@Param("period") String period);



    SummarySettledVo summarySettledPendingAmount(@Param("merchantId") Long merchantId,@Param("settlementDate") String settlementDate,@Param("providerIds") List<Long> providerIds);

    /**
     * 查询商户交易总金额
     * @param query
     * @return
     */
    List<MerchantSettmEntity> queryTotalVolume(MerchantSettmEntity query);

    /**
     * 查询merchant details 中的换汇数据
     * @param query
     * @return
     */
    List<MerchantSettmEntity> queryTotalFxFEEVolume(MerchantSettmEntity query);

    /**
     * 查询商户交易总金额 换汇
     * @param query
     * @return
     */
    List<MerchantSettmEntity> queryExchangeVolume(MerchantSettmEntity query);


    /**
     * 查询商户交易总金额
     * @param query
     * @return
     */
    List<MerchantSettmEntity> queryTotalVolumeByPayment(MerchantSettmEntity query);


    /**
     *  查询商户信息
     * @param mId
     * @return
     */
    MerchantInfoVo queryMerchantInfo(@Param("merchantId") Long mId);
    MerchantInfoVo queryMerchantInfoByProvider(@Param("merchantId") Long mId,@Param("providerId") Long providerId);


    /**
     * 查询商户发票信息
     * @param fileNameLikeStr
     * @param fileType
     * @return
     */
    List<MerchantFilesEntity> queryMerchantFileInfoFormInvoice(@Param("merchantId") Long merchantId ,@Param("fileType") String fileType,@Param("fileNameLikeStr") String fileNameLikeStr);


    /**
     * 添加结算文件信息
     * @param record
     * @return
     */
    int insertReportRecord(MerchantReportRecordEntity record);

    /**
     * 查询报告详情
     * @param record
     * @return
     */
    List<MerchantReportRecordEntity> queryReportRecordInfo(MerchantReportRecordEntity record);

    int deleteReportRecordInfo(MerchantReportRecordEntity record);


    /**
     * 查询商户结算信息
     * @return
     */
    List<MerchantInfoVo> queryMerchantSettleInfo(@Param("settlementDate") Date settlementDate);
    List<MerchantInfoVo> queryMerchantSettleInfoRec(@Param("reconDate") String reconDate);
    List<MerchantInfoVo> queryMerchantRecInfo();

    /**
     * 查询当期已经完成结算的商户信息 商户渠道信息
     * @param settlementDate 20241211
     * @return
     */
    List<MerchantSettlementEntity> querySettledMerchantInfo(@Param("settlementDate") String settlementDate);

    /**
     * 查询当期已经完成结算的商户信息,但是结算金额没有到最小结算金额的商户，
     *  待结算金额小于最小结算金额，MerchantSettlement 表中的状态会从 3 变成  2 所以这里查询状态 == 2 的数据，表示为转结算的数据
     * @param settlementDate 20241211
     * @return
     */
    List<MerchantSettlementEntity> queryPendingMerchantInfo(@Param("settlementDate") String settlementDate);

    /**
     * 查询已经发布结算的商户信息
     * @param queryVo
     * @return
     */
    List<MerchantSettlementEntity> queryPublishedMerchantInfoByProvider(PublishSettleQueryVo queryVo);
    List<MerchantSettlementEntity> queryPublishedMerchantInfo(PublishSettleQueryVo queryVo);

    /**
     * 查询已经发布结算的商户的结算金额
     * @param merchantId
     * @param settlementDate
     * @return
     */
    List<MerchantSettlementEntity> queryMerchantSettlementAmountInfo(@Param("merchantId") Long merchantId,@Param("settlementDate") String settlementDate);

    /**
     * 更新 settle_status = 2 ，不进行资金结算。等达到 最小结算金额，才进行资金结算。
     * @param merchantId
     * @param settlementDate
     * @return
     */
    int updatePublishedMerchantInfo(@Param("merchantId") Long merchantId,@Param("settlementDate") String settlementDate,@Param("status") int status);

    int updatePublishedMerchantInfoByProvider(@Param("merchantId") Long merchantId,@Param("settlementDate") String settlementDate,@Param("providerId") Long providerId,@Param("status") int status);

    AccountBizTradeDetailsEntity queryReleasePendingAmount(@Param("bizType") String bizType,@Param("merchantId") String merchantId,@Param("settlementDate") String settlementDate);

    List<SummarySettledVo> summarySettledAmount(@Param("merchantId") Long merchantId,@Param("settlementDate") String settlementDate,@Param("providerId") Long providerId);


    int summaryBatchInsert(@Param("list") List<MerchantSettlementCalendarEntity> list);

    /**
     * 查询汇总数据
     * @param merchantId
     * @param settlementDate
     * @return
     */
    List<MerchantSettlementCalendarEntity> querySummaryData(@Param("merchantId") Long merchantId,@Param("settlementDate") String settlementDate);

    int deleteSummaryData(@Param("merchantId") Long merchantId,@Param("settlementDate") String settlementDate);

    /**
     * 通过ID查询
     * @param merchantId
     * @return
     */
    MerchantInfoVo queryMerchantInfoById(@Param("merchantId") Long merchantId);

    /**
     * 根据结算周期查询结算 结算账单数据（上游已经结算，下游未结算账单）
     * @param query
     * @return
     */
    List<PaymentBillingEntity> querySettlementBillingsByCycle(MerchantSttemQuery query);

    /**
     * 补单数据查询
     * @param query
     * @return
     */
    List<PaymentBillingEntity> querySettlementDiffBillings(MerchantSttemQuery query);

    int updateDiffBills(PaymentBillingEntity entity);

    /**
     * 查询交易对账文件
     * @param query
     * @return
     */
    List<ReconciliationVo> getMerchantReconciliation(MerchantSttemQuery query);

    /**
     * 查询
     * @return
     */
    List<ProviderInfoVO> queryProviderInfoVO();

    /**
     * 查询结算汇总
     * @param merchantId
     * @param settlementDate
     * @return
     */
    List<SettlementAggergateVo> getPublishSettlementAggergate(@Param("merchantId") Long merchantId,@Param("settlementDate") String settlementDate);
    /**
     * 查询结算明细
     * @param merchantId
     * @param settlementDate
     * @return
     */
    List<SettlementVo> getPublishSettlement(@Param("merchantId") Long merchantId, @Param("settlementDate") String settlementDate);


    /**
     * 查询商户的渠道信息和支付方式
     * @param merchantId
     * @param providerId
     * @return
     */
    List<MerchantPaymentMethodVo> queryMerchantPaymentMethods(@Param("merchantId") Long merchantId, @Param("providerId") Long providerId);

    List<AggregateMerchantSettlementVo> aggregateMerchantSettlement(@Param("merchantId") Long merchantId,@Param("settlementDate") String settlementDate, @Param("providerId") Long providerId, @Param("start") String start,@Param("end") String end);



    List<MerchantInfoVo> queryMerchantReconInfo(@Param("merchantId") Long merchantId);

    /**
     * 更新结算日期
     * @param merchantId
     * @param providerId
     * @param settlementDate
     * @return
     */
    int updateSettlementDateByProvider(@Param("merchantId") Long merchantId,@Param("providerId") Long providerId,@Param("settlementDate") Date settlementDate);

    /**
     * 更新对账日期
     * @param merchantId
     * @param providerId
     * @param reconDate
     * @return
     */
    int updateReconDateByProvider(@Param("merchantId") Long merchantId,@Param("providerId") Long providerId,@Param("reconDate") Date reconDate);


    /**
     * 更新Billing表中的 对账日期
     * @param billId
     * @param reconDate
     * @return
     */
    int updateBillingReconDate(@Param("billId") Long billId,@Param("reconDate") Date reconDate);


    /**
     * 删除文件处理日志
     * @param fileHandleLogVo
     * @return
     */
    List<FileHandleEntity> selectFileHandleLog(FileHandleLogVo fileHandleLogVo);
    int deleteFileHandleLog(FileHandleLogVo fileHandleLogVo);
    int deleteMerchantFile(FileHandleLogVo fileHandleLogVo);
    int deleteMerchantSettlementFile(MerchantSettInfoVo merchantSettInfoVo);
    int updateMerchantInfo(MerchantSettInfoVo merchantSettInfoVo);
    int updateTbPPRORTransaction(PPRORTransactionVo pprorTransactionVo);
    int updateTbPPROVTransaction(PPRORTransactionVo pprorTransactionVo);
    int updatePaymentBilling(PPRORTransactionVo pprorTransactionVo);
    int updatePaymentBillingReconciliationStatus(PPRORTransactionVo pprorTransactionVo);
    int dateMerchantSettlementDetailsAggregate(MerchantSettInfoVo merchantSettInfoVo);
    int deleteMerchantSettlementDetails(MerchantSettInfoVo merchantSettInfoVo);
    List<PaymentOrderEntity> queryPaymentOrderByDate(@Param("merchantId") String merchantId,@Param("startDate") String settlementDate,@Param("endDate") String endDate);
}
