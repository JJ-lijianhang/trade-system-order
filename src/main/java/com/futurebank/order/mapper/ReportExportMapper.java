package com.futurebank.order.mapper;

import com.futurebank.order.entity.MerchantReportRecordEntity;
import com.futurebank.order.vo.Invoice.MerchantInfoVo;
import com.futurebank.order.vo.Report.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashSet;
import java.util.List;

@Mapper
public interface ReportExportMapper {

    /**
     * 报表表头查询
     * @param reportQueryVo
     * @return
     */
    TableHeaderVo queryTableHeader(ReportQueryVo reportQueryVo);


    /**
     * 商户账变数据查询
     * @param reportQueryVo
     * @return
     */
    List<BalanceReportVo> queryBalanceChargeHistory(ReportQueryVo reportQueryVo);


    /**
     * 查询平台转账数据-- 收款表中数据
     * @param orderList
     * @return
     */
    List<MerchantPaymentOrderVo> queryPlatTransferOrder(@Param("orderList") HashSet<String> orderList);

    /**
     * 查询平台转账数据-- 付款表中数据
     * @param orderList
     * @return
     */
    List<MerchantPaymentOrderVo> queryPlatPaymentOrder(@Param("orderList") HashSet<String> orderList);


    /**
     * 收款数据查询
     * @param reportQueryVo
     * @return
     */
    List<CollectionReportVo> queryCollectionReport(ReportQueryVo reportQueryVo);


    /**
     * 付款数据查询
     * @param reportQueryVo
     * @return
     */
    List<PaymentReportVo> queryPaymentReport(ReportQueryVo reportQueryVo);


    /**
     * 转账数据查询
     * @param reportQueryVo
     * @return
     */
    List<ConvertReportVo> queryConvertReport(ReportQueryVo reportQueryVo);


    /**
     * 转账数据查询
     * @param reportQueryVo
     * @return
     */
    List<TransferReportVo> queryTransferReportSend(ReportQueryVo reportQueryVo);
    List<TransferReportVo> queryTransferReportReceived(ReportQueryVo reportQueryVo);

    /**
     * 收单交易数据查询
     * @param reportQueryVo
     * @return
     */
    List<AcquiringTransactionReportVo> queryAcquiringTransactionReport(ReportQueryVo reportQueryVo);

    /**
     * 收单退款数据查询
     * @param reportQueryVo
     * @return
     */
    List<AcquiringRefundReportVo> queryAcquiringRefundReport(ReportQueryVo reportQueryVo);

    /**
     * 收单拒付数据查询
     * @param reportQueryVo
     * @return
     */
    List<AcquiringChargebackReportVo> queryAcquiringChargebackReport(ReportQueryVo reportQueryVo);


    /**
     * 添加结算文件信息
     * @param record
     * @return
     */
    int insertReportRecord(MerchantReportRecordEntity record);


    /**
     * 查询业务类型
     * @return
     */
    List<BusinessTypeVo> queryBusinessType();

    List<PaymentMethodTypeVo> queryPaymentMethodType();

    /**
     * 查询下游订单号
     * @param orderList
     * @return
     */
    List<OrderNoChangeVo> queryDownstreamOrderNO(@Param("orderList") List<String> orderList);

    /**
     *  payment method change
     * @param methodList
     * @return
     */
    List<PaymentMethodChangeVo> queryPlatNameByMethod(@Param("methodList") List<String> methodList);

    /**
     * 科目信息
     * @return
     */
    List<AccountSubjectChangeVo> queryAccountSubject();

    /**
     * 收款方信息地址
     * @param ids
     * @return
     */
    List<PayeeInfoVo> queryPayeeAddressById(@Param("ids") List<Long> ids);
    List<PayeeInfoVo> queryPayeeRoutingCodeById(@Param("ids") List<Long> ids);
    List<PaymentPurposeVo> queryAllPaymentPurposeInfo(@Param("ids") List<String> ids);


    /**
     * 收款方信息
     * @param ids
     * @return
     */
    List<PayeeInfoVo> queryPayeeInfoById(@Param("ids") List<Long> ids);


    /**
     * 查询商户信息
     * @param merchantId
     * @return
     */
    List<MerchantInfoVo> queryMerchantInfoById(@Param("merchantId") Long merchantId);


    /**
     * 查询转账数据-查询总数
     * @param reportQueryVo
     * @return
     */
    int queryTransferReportSendCount(ReportQueryVo reportQueryVo);
    int queryTransferReportReceivedCount(ReportQueryVo reportQueryVo);
    int queryPaymentReportCount(ReportQueryVo reportQueryVo);
    int queryConvertReportCount(ReportQueryVo reportQueryVo);
    int queryCollectionReportCount(ReportQueryVo reportQueryVo);
    int queryBalanceChargeHistoryCount(ReportQueryVo reportQueryVo);
    int queryAcquiringTransactionReportCount(ReportQueryVo reportQueryVo);
    int queryAcquiringRefundReportCount(ReportQueryVo reportQueryVo);
    int queryAcquiringChargebackReportCount(ReportQueryVo reportQueryVo);

}
