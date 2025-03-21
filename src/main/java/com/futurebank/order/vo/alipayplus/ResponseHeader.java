/**
 * Legal Disclaimer
 * Source Code or Sample Code not belongs to Alipay technological assets, partners agree to take full responsibility for any activities or actions under its use.
 * Partners are authorised to use for Approved Purposes.
 * "Approved Purpose" means, subject to any restrictions set out in the Participation Documents, (i) in relation to a Partner, as is necessary in order to facilitate that Partner’s participation in Alipay+ Core; and (ii) in relation to Alipay+, as is necessary in order to facilitate the provision of the Services (including the exercise of rights and performance of obligations under Participation Documents, managing AML/Fraud Risk, resolving any Disputes or facilitating the provision of Partner Products by other Partners).
 * You should check our NDA for detail Legal T&C.
 */
package com.futurebank.order.vo.alipayplus;


import com.futurebank.order.utils.DateUtil;
import com.futurebank.order.vo.alipayplus.constants.CommonConstants;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ResponseHeader
 * @author mulin.xl
 * @version $Id: ResponseHeader.java, v 0.1 2022‐06‐23 13:03 mulin.xl Exp $$
 */
public class ResponseHeader extends Header {

    /**
     * responseTime
     */
    private String responseTime = DateUtil.getISODateTimeStr(new Date());

    /**
     * Getter method for property responseTime.
     *
     * @return property value of responseTime
     */
    public String getResponseTime() {
        return responseTime;
    }

    /**
     * Setter method for property responseTime.
     *
     * @param responseTime value to be assigned to property responseTime
     */
    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public void fromMap(Map<String, String> headers) {

        this.setClientId(headers.get(CommonConstants.CLIENT_ID_HEADER.toLowerCase()));
        this.setContentType(headers.get(CommonConstants.CONTENT_TYPE_HEADER.toLowerCase()));
        this.setSignature(new Signature(headers.get(CommonConstants.RSP_SIGN_HEADER.toLowerCase())));
        this.setResponseTime(headers.get(CommonConstants.RSP_TIME_HEADER.toLowerCase()));

    }

    @Override
    public Map<String, String> toMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(CommonConstants.CLIENT_ID_HEADER, super.getClientId());
        hashMap.put(CommonConstants.CONTENT_TYPE_HEADER, super.getContentType());
        hashMap.put(CommonConstants.RSP_SIGN_HEADER, super.getSignature().toString());
        hashMap.put(CommonConstants.RSP_TIME_HEADER, this.responseTime);
        return hashMap;
    }
}