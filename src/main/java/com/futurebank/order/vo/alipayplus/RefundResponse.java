package com.futurebank.order.vo.alipayplus;


import com.futurebank.pojo.vo.payment.Amount;

/**
 * Refund Response
 *
 * @author wqy01239909
 * @version $Id: RefundResponse.java, v 0.1 2022‐06-24 10:34 wqy01239909 Exp $
 */
public class RefundResponse extends APSResponse {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4791519309690978036L;
    /**
     * acquirerId
     */
    private String acquirerId;
    /**
     * pspId
     */
    private String pspId;
    /**
     * refundId
     */
    private String refundId;
    /**
     * refundTime
     */
    private String refundTime;
    /**
     * refundAmount
     */
    private Amount refundAmount;
    /**
     * settlementAmount
     */
    private Amount settlementAmount;
    /**
     * settlementQuote
     */
    private Quote settlementQuote;

    /**
     * Getter method for property acquirerId.
     *
     * @return property value of acquirerId
     */
    public String getAcquirerId() {
        return acquirerId;
    }

    /**
     * Setter method for property acquirerId.
     *
     * @param acquirerId value to be assigned to property acquirerId
     */
    public void setAcquirerId(String acquirerId) {
        this.acquirerId = acquirerId;
    }

    /**
     * Getter method for property pspId.
     *
     * @return property value of pspId
     */
    public String getPspId() {
        return pspId;
    }

    /**
     * Setter method for property pspId.
     *
     * @param pspId value to be assigned to property pspId
     */
    public void setPspId(String pspId) {
        this.pspId = pspId;
    }

    /**
     * Getter method for property refundId.
     *
     * @return property value of refundId
     */
    public String getRefundId() {
        return refundId;
    }

    /**
     * Setter method for property refundId.
     *
     * @param refundId value to be assigned to property refundId
     */
    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    /**
     * Getter method for property refundTime.
     *
     * @return property value of refundTime
     */
    public String getRefundTime() {
        return refundTime;
    }

    /**
     * Setter method for property refundTime.
     *
     * @param refundTime value to be assigned to property refundTime
     */
    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    /**
     * Getter method for property refundAmount.
     *
     * @return property value of refundAmount
     */
    public Amount getRefundAmount() {
        return refundAmount;
    }

    /**
     * Setter method for property refundAmount.
     *
     * @param refundAmount value to be assigned to property refundAmount
     */
    public void setRefundAmount(Amount refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * Getter method for property settlementAmount.
     *
     * @return property value of settlementAmount
     */
    public Amount getSettlementAmount() {
        return settlementAmount;
    }

    /**
     * Setter method for property settlementAmount.
     *
     * @param settlementAmount value to be assigned to property settlementAmount
     */
    public void setSettlementAmount(Amount settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    /**
     * Getter method for property settlementQuote.
     *
     * @return property value of settlementQuote
     */
    public Quote getSettlementQuote() {
        return settlementQuote;
    }

    /**
     * Setter method for property settlementQuote.
     *
     * @param settlementQuote value to be assigned to property settlementQuote
     */
    public void setSettlementQuote(Quote settlementQuote) {
        this.settlementQuote = settlementQuote;
    }
}