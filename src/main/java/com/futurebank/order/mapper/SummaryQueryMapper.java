package com.futurebank.order.mapper;

import com.futurebank.order.entity.MerchantFilesEntity;
import com.futurebank.order.entity.MerchantReconciliationCalendarEntity;
import com.futurebank.order.entity.MerchantSettlementCalendarEntity;
import com.futurebank.order.req.ReconciliationCalendarReq;
import com.futurebank.order.req.SettementCalenderReq;
import com.futurebank.order.vo.QueryVo;
import com.futurebank.order.vo.recon.ReconSummaryVo;
import com.futurebank.order.vo.settlement.SettleSummaryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SummaryQueryMapper {

    /**
     * 对账日历查询
     * @param calendarReq
     * @return
     */
    List<MerchantReconciliationCalendarEntity> queryReconCalendar(ReconciliationCalendarReq calendarReq);

    /**
     * 查询对账文件
     * @param calendarReq
     * @return
     */
    List<MerchantFilesEntity> queryReconFile(ReconciliationCalendarReq calendarReq);


    /**
     * 结算日历查询
     * @param req
     * @return
     */
    List<MerchantSettlementCalendarEntity> querySettleCalendar(SettementCalenderReq req);


    /**
     * 查询结算文件
     * @param req
     * @return
     */
    List<MerchantFilesEntity> querySettleFile(SettementCalenderReq req);


    /**
     * 对账汇总查询
     * @param req
     * @return
     */
    List<ReconSummaryVo> queryReconSummary(ReconciliationCalendarReq req);

    /**
     * 结算汇总查询
     * @param req
     * @return
     */
    List<SettleSummaryVo> querySettleSummary(SettementCalenderReq req);


    /**
     * 查询对账币种
     * @param merchantId
     * @return
     */
    List<QueryVo> queryReconCurrency(@Param("merchantId") Long merchantId);

    /**
     * 查询对账支付方式
     * @param merchantId
     * @return
     */
    List<QueryVo> queryReconPaymentMth(@Param("merchantId") Long merchantId);

    /**
     * 查询结算币种
     * @param merchantId
     * @return
     */
    List<QueryVo> querySettleCurrency(@Param("merchantId") Long merchantId);

    /**
     * 查询结算支付方式
     * @param merchantId
     * @return
     */
    List<QueryVo> querySettlePaymentMth(@Param("merchantId") Long merchantId);

    /**
     * 查询支付方式
     * @param methodList
     * @return
     */
    List<QueryVo> queryPlatNameByMethod(@Param("methodList") List<String> methodList);


}
