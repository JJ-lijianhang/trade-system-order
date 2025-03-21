/**
 * Legal Disclaimer
 * Source Code or Sample Code not belongs to Alipay technological assets, partners agree to take full responsibility for any activities or actions under its use.
 * Partners are authorised to use for Approved Purposes.
 * "Approved Purpose" means, subject to any restrictions set out in the Participation Documents, (i) in relation to a Partner, as is necessary in order to facilitate that Partner’s participation in Alipay+ Core; and (ii) in relation to Alipay+, as is necessary in order to facilitate the provision of the Services (including the exercise of rights and performance of obligations under Participation Documents, managing AML/Fraud Risk, resolving any Disputes or facilitating the provision of Partner Products by other Partners).
 * You should check our NDA for detail Legal T&C.
 */
package com.futurebank.order.enums;

/**
 * ResultStatus Type
 * @author wqy01239909
 * @version $Id: ResultStatusType.java, v 0.1 2022‐07-11 10:43 wqy01239909 Exp $
 */
public enum ResultStatusType {

    /**
     * success
     */
    S("S", "success"),
    /**
     * fail
     */
    F("F", "fail"),
    /**
     * unknown
     */
    U("U", "unknown");

    private String code;

    private String desc;

    /**
     * Gets authorization Notify Type by code.
     *
     * @param code the code
     * @return the authorization Notify Type
     */
    public static ResultStatusType getByCode(String code) {
        for (ResultStatusType value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

    ResultStatusType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * Getter method for property code.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter method for property code.
     *
     * @param code value to be assigned to property code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter method for property desc.
     *
     * @return property value of desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter method for property desc.
     *
     * @param desc value to be assigned to property desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

}
