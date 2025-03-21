/**
 *   Legal Disclaimer
 *   Source Code or Sample Code not belongs to Alipay technological assets, partners agree to take full responsibility for any activities or actions under its use.
 *   Partners are authorised to use for Approved Purposes.
 *   "Approved Purpose" means, subject to any restrictions set out in the Participation Documents, (i) in relation to a Partner, as is necessary in order to facilitate that Partner’s participation in Alipay+ Core; and (ii) in relation to Alipay+, as is necessary in order to facilitate the provision of the Services (including the exercise of rights and performance of obligations under Participation Documents, managing AML/Fraud Risk, resolving any Disputes or facilitating the provision of Partner Products by other Partners).
 *   You should check our NDA for detail Legal T&C.
 */
package com.futurebank.order.vo.alipayplus;


import com.futurebank.order.utils.DateUtil;
import com.futurebank.order.utils.StringUtil;
import com.futurebank.order.vo.alipayplus.constants.CommonConstants;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * RequestHeader
 * @author mulin.xl
 * @version $Id: RequestHeader.java, v 0.1 2022‐06‐23 13:00 mulin.xl Exp $$
 */
public class RequestHeader extends Header implements Cloneable {

    /**
     * requestTime
     */
    private String requestTime = DateUtil.getISODateTimeStr(new Date());

    /**
     * agentToken
     */
    private String agentToken;

    /**
     * Getter method for property requestTime.
     *
     * @return property value of requestTime
     */
    public String getRequestTime() {
        return requestTime;
    }

    /**
     * Setter method for property requestTime.
     *
     * @param requestTime value to be assigned to property requestTime
     */
    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * Getter method for property agentToken.
     *
     * @return property value of agentToken
     */
    public String getAgentToken() {
        return agentToken;
    }

    /**
     * Setter method for property agentToken.
     *
     * @param agentToken value to be assigned to property agentToken
     */
    public void setAgentToken(String agentToken) {
        this.agentToken = agentToken;
    }

    @Override
    public void fromMap(Map<String, String> headers) {

        this.setClientId(headers.get(CommonConstants.CLIENT_ID_HEADER));
        this.setAgentToken(headers.get(CommonConstants.AGENT_TOKEN_HEADER));
        this.setRequestTime(headers.get(CommonConstants.REQ_TIME_HEADER));
        Signature signature = new Signature(headers.get(CommonConstants.REQ_SIGN_HEADER));
        this.setSignature(signature);

    }

    @Override
    public Map<String, String> toMap() {

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put(CommonConstants.CONTENT_TYPE_HEADER, this.getContentType());
        hashMap.put(CommonConstants.CLIENT_ID_HEADER, this.getClientId());
        hashMap.put(CommonConstants.REQ_TIME_HEADER, requestTime);

        if (StringUtil.isNotBlank(agentToken)) {
            hashMap.put(CommonConstants.AGENT_TOKEN_HEADER, agentToken);
        }

        if (this.getSignature() != null) {

            String signatureHeader = "algorithm=" + this.getSignature().algorithm + ",keyVersion="
                                     + this.getSignature().keyVersion + ",signature="
                                     + this.getSignature().getSignature();

            hashMap.put(CommonConstants.REQ_SIGN_HEADER, signatureHeader);

        }

        return hashMap;
    }

    @Override
    public RequestHeader clone() throws CloneNotSupportedException {
        RequestHeader requestHeader = (RequestHeader) super.clone();
        return requestHeader;
    }
}