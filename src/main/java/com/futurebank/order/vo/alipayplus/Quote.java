/**
 * Legal Disclaimer
 * Source Code or Sample Code not belongs to Alipay technological assets, partners agree to take full responsibility for any activities or actions under its use.
 * Partners are authorised to use for Approved Purposes.
 * "Approved Purpose" means, subject to any restrictions set out in the Participation Documents, (i) in relation to a Partner, as is necessary in order to facilitate that Partner’s participation in Alipay+ Core; and (ii) in relation to Alipay+, as is necessary in order to facilitate the provision of the Services (including the exercise of rights and performance of obligations under Participation Documents, managing AML/Fraud Risk, resolving any Disputes or facilitating the provision of Partner Products by other Partners).
 * You should check our NDA for detail Legal T&C.
 */
package com.futurebank.order.vo.alipayplus;

import java.math.BigDecimal;

/**
 * Quote
 *
 * @author wangpeng
 * @version $Id: Quote.java, v 0.1 2022‐07-05 20:47 wangpeng Exp $
 */
public class Quote extends ToString {
    /** serialVersionUID */
    private static final long serialVersionUID = 7341192732456203831L;
    /**
     * quoteId
     */
    private String quoteId;
    /**
     * quoteCurrencyPair
     */
    private String quoteCurrencyPair;
    /**
     * quotePrice
     */
    private BigDecimal quotePrice;
    /**
     * quoteStartTime
     */
    private String quoteStartTime;
    /**
     * quoteExpiryTime
     */
    private String quoteExpiryTime;
    /**
     * baseCurrency
     */
    private String baseCurrency;
    /**
     * quoteUnit
     */
    private String quoteUnit;

    /**
     * Getter method for property quoteId.
     *
     * @return property value of quoteId
     */
    public String getQuoteId() {
        return quoteId;
    }

    /**
     * Setter method for property quoteId.
     *
     * @param quoteId value to be assigned to property quoteId
     */
    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    /**
     * Getter method for property quoteCurrencyPair.
     *
     * @return property value of quoteCurrencyPair
     */
    public String getQuoteCurrencyPair() {
        return quoteCurrencyPair;
    }

    /**
     * Setter method for property quoteCurrencyPair.
     *
     * @param quoteCurrencyPair value to be assigned to property quoteCurrencyPair
     */
    public void setQuoteCurrencyPair(String quoteCurrencyPair) {
        this.quoteCurrencyPair = quoteCurrencyPair;
    }

    /**
     * Getter method for property quotePrice.
     *
     * @return property value of quotePrice
     */
    public BigDecimal getQuotePrice() {
        return quotePrice;
    }

    /**
     * Setter method for property quotePrice.
     *
     * @param quotePrice value to be assigned to property quotePrice
     */
    public void setQuotePrice(BigDecimal quotePrice) {
        this.quotePrice = quotePrice;
    }

    /**
     * Getter method for property quoteStartTime.
     *
     * @return property value of quoteStartTime
     */
    public String getQuoteStartTime() {
        return quoteStartTime;
    }

    /**
     * Setter method for property quoteStartTime.
     *
     * @param quoteStartTime value to be assigned to property quoteStartTime
     */
    public void setQuoteStartTime(String quoteStartTime) {
        this.quoteStartTime = quoteStartTime;
    }

    /**
     * Getter method for property quoteExpiryTime.
     *
     * @return property value of quoteExpiryTime
     */
    public String getQuoteExpiryTime() {
        return quoteExpiryTime;
    }

    /**
     * Setter method for property quoteExpiryTime.
     *
     * @param quoteExpiryTime value to be assigned to property quoteExpiryTime
     */
    public void setQuoteExpiryTime(String quoteExpiryTime) {
        this.quoteExpiryTime = quoteExpiryTime;
    }

    /**
     * Getter method for property baseCurrency.
     *
     * @return property value of baseCurrency
     */
    public String getBaseCurrency() {
        return baseCurrency;
    }

    /**
     * Setter method for property baseCurrency.
     *
     * @param baseCurrency value to be assigned to property baseCurrency
     */
    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    /**
     * Getter method for property quoteUnit.
     *
     * @return property value of quoteUnit
     */
    public String getQuoteUnit() {
        return quoteUnit;
    }

    /**
     * Setter method for property quoteUnit.
     *
     * @param quoteUnit value to be assigned to property quoteUnit
     */
    public void setQuoteUnit(String quoteUnit) {
        this.quoteUnit = quoteUnit;
    }

}
