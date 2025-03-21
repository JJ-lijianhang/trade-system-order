/**
 * Legal Disclaimer
 * Source Code or Sample Code not belongs to Alipay technological assets, partners agree to take full responsibility for any activities or actions under its use.
 * Partners are authorised to use for Approved Purposes.
 * "Approved Purpose" means, subject to any restrictions set out in the Participation Documents, (i) in relation to a Partner, as is necessary in order to facilitate that Partner’s participation in Alipay+ Core; and (ii) in relation to Alipay+, as is necessary in order to facilitate the provision of the Services (including the exercise of rights and performance of obligations under Participation Documents, managing AML/Fraud Risk, resolving any Disputes or facilitating the provision of Partner Products by other Partners).
 * You should check our NDA for detail Legal T&C.
 */
package com.futurebank.order.vo.alipayplus.constants;

/**
 * code related constants
 *
 * @author wangpeng
 * @version $Id: CommonConstants.java, v 0.1 2022‐06-21 11:09 wangpeng Exp $
 */
public interface CommonConstants {

    /** scheme */
    String SCHEME = "https";

    /** the default charset */
    String DEFAULT_CHARSET = "UTF-8";

    /** client id header */
    String CLIENT_ID_HEADER = "Client-Id";

    /** accept header */
    String ACCEPT_HEADER = "Accept";

    /** content type header */
    String CONTENT_TYPE_HEADER = "Content-Type";

    /** user agent */
    String USER_AGENT_HEADER = "User-Agent";

    /** connection */
    String CONNECTION_HEADER = "Connection";

    /** req sign header */
    String REQ_SIGN_HEADER = "Signature";

    /** req time header */
    String REQ_TIME_HEADER = "Request-Time";

    /** rsp_sign_header */
    String RSP_SIGN_HEADER = "Signature";

    /** rsp time header */
    String RSP_TIME_HEADER = "Response-Time";

    /** agent token header */
    String AGENT_TOKEN_HEADER = "Agent-Token";

    /** http success code */
    int HTTP_SUCCESS_CODE = 200;

    /** ap config file */
    String APS_CONFIG_FILE = "config.properties";

    /** number zero  */
    int NUMBER_ZERO = 0;

}
