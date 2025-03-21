/**
 * Legal Disclaimer
 * Source Code or Sample Code not belongs to Alipay technological assets, partners agree to take full responsibility for any activities or actions under its use.
 * Partners are authorised to use for Approved Purposes.
 * "Approved Purpose" means, subject to any restrictions set out in the Participation Documents, (i) in relation to a Partner, as is necessary in order to facilitate that Partner’s participation in Alipay+ Core; and (ii) in relation to Alipay+, as is necessary in order to facilitate the provision of the Services (including the exercise of rights and performance of obligations under Participation Documents, managing AML/Fraud Risk, resolving any Disputes or facilitating the provision of Partner Products by other Partners).
 * You should check our NDA for detail Legal T&C.
 */
package com.futurebank.order.vo.alipayplus;

import java.util.Map;

/**
 * Header
 *
 * @author Header
 * @version $Id: Buyer.java, v 0.1 2022‐07-05 20:47 wangpeng Exp $
 */
public abstract class Header {

    /**
     * contentType
     */
    private String contentType = "application/json; charset=UTF-8";

    /**
     * clientId
     */
    private String clientId;

    /**
     * signature
     */
    private Signature signature = new Signature();

    /**
     * Getter method for property contentType.
     *
     * @return property value of contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Setter method for property contentType.
     *
     * @param contentType value to be assigned to property contentType
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Getter method for property clientId.
     *
     * @return property value of clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Setter method for property clientId.
     *
     * @param clientId value to be assigned to property clientId
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Getter method for property signature.
     *
     * @return property value of signature
     */
    public Signature getSignature() {
        return signature;
    }

    /**
     * Setter method for property signature.
     *
     * @param signature value to be assigned to property signature
     */
    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    public abstract void fromMap(Map<String, String> headers);

    public abstract Map<String, String> toMap();
}