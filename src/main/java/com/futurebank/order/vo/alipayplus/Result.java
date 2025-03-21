/**
 * Legal Disclaimer
 * Source Code or Sample Code not belongs to Alipay technological assets, partners agree to take full responsibility for any activities or actions under its use.
 * Partners are authorised to use for Approved Purposes.
 * "Approved Purpose" means, subject to any restrictions set out in the Participation Documents, (i) in relation to a Partner, as is necessary in order to facilitate that Partner’s participation in Alipay+ Core; and (ii) in relation to Alipay+, as is necessary in order to facilitate the provision of the Services (including the exercise of rights and performance of obligations under Participation Documents, managing AML/Fraud Risk, resolving any Disputes or facilitating the provision of Partner Products by other Partners).
 * You should check our NDA for detail Legal T&C.
 */
package com.futurebank.order.vo.alipayplus;


import com.futurebank.order.enums.ResultStatusType;

/**
 * Result
 *
 * @author wangpeng
 * @version $Id: Result.java, v 0.1 2022‐07-05 20:47 wangpeng Exp $
 */
public class Result extends ToString {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4797962326642908477L;

    /**
     * If you received the notification successfully, set the value of result.resultStatus to S
     * and return an HTTP status code of 200 to indicate that your server received the call.
     */
    public final static Result S = new Result("SUCCESS", "success",
            ResultStatusType.S);

    /**
     * resultCode
     */
    private String resultCode;
    /**
     * resultStatus
     */
    private ResultStatusType resultStatus;
    /**
     * resultMessage
     */
    private String resultMessage;

    /**
     * construction method
     *
     * @param resultCode    resultCode
     * @param resultMessage resultMessage
     * @param resultStatus  resultStatus
     */
    public Result(String resultCode, String resultMessage, ResultStatusType resultStatus) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.resultStatus = resultStatus;
    }

    /**
     * construction method
     */
    public Result() {
    }

    /**
     * Getter method for property resultCode.
     *
     * @return property value of resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Setter method for property resultCode.
     *
     * @param resultCode value to be assigned to property resultCode
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * Getter method for property resultStatus.
     *
     * @return property value of resultStatus
     */
    public ResultStatusType getResultStatus() {
        return resultStatus;
    }

    /**
     * Setter method for property resultStatus.
     *
     * @param resultStatus value to be assigned to property resultStatus
     */
    public void setResultStatus(ResultStatusType resultStatus) {
        this.resultStatus = resultStatus;
    }

    /**
     * Getter method for property resultMessage.
     *
     * @return property value of resultMessage
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * Setter method for property resultMessage.
     *
     * @param resultMessage value to be assigned to property resultMessage
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

}
