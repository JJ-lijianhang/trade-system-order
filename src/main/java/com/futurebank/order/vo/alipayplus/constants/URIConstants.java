/**
 * Legal Disclaimer
 * Source Code or Sample Code not belongs to Alipay technological assets, partners agree to take full responsibility for any activities or actions under its use.
 * Partners are authorised to use for Approved Purposes.
 * "Approved Purpose" means, subject to any restrictions set out in the Participation Documents, (i) in relation to a Partner, as is necessary in order to facilitate that Partner’s participation in Alipay+ Core; and (ii) in relation to Alipay+, as is necessary in order to facilitate the provision of the Services (including the exercise of rights and performance of obligations under Participation Documents, managing AML/Fraud Risk, resolving any Disputes or facilitating the provision of Partner Products by other Partners).
 * You should check our NDA for detail Legal T&C.
 */
package com.futurebank.order.vo.alipayplus.constants;

/**
 * API endpoint
 *
 * @author wqy01239909
 * @version $Id: URIConstants.java, v 0.1 2022‐08‐31 16:42 wqy01239909 Exp $$
 */
public interface URIConstants {

    /** cancel payment */
    String CANCEL_PAYMENT = "/aps/api/v1/payments/cancelPayment";

    /** refund */
    String REFUND = "/aps/api/v1/payments/refund";

}