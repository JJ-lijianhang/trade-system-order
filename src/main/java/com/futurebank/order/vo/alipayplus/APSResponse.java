/**
 * Legal Disclaimer
 * Source Code or Sample Code not belongs to Alipay technological assets, partners agree to take full responsibility for any activities or actions under its use.
 * Partners are authorised to use for Approved Purposes.
 * "Approved Purpose" means, subject to any restrictions set out in the Participation Documents, (i) in relation to a Partner, as is necessary in order to facilitate that Partner’s participation in Alipay+ Core; and (ii) in relation to Alipay+, as is necessary in order to facilitate the provision of the Services (including the exercise of rights and performance of obligations under Participation Documents, managing AML/Fraud Risk, resolving any Disputes or facilitating the provision of Partner Products by other Partners).
 * You should check our NDA for detail Legal T&C.
 */
package com.futurebank.order.vo.alipayplus;


/**
 * APS Base Response
 * @author mulin.xl
 * @version $Id: APSResponse.java, v 0.1 2022‐06‐23 12:52 mulin.xl Exp $$
 */
public abstract class APSResponse extends ToString {
    /** serialVersionUID */
    private static final long serialVersionUID = 5669984777395423776L;

    /**
     * responseHeader
     */
    private transient ResponseHeader responseHeader = new ResponseHeader();

    /**
     * result
     */
    private Result result = new Result();

    /**
     * Getter method for property responseHeader.
     *
     * @return property value of responseHeader
     */
    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    /**
     * Setter method for property responseHeader.
     *
     * @param responseHeader value to be assigned to property responseHeader
     */
    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    /**
     * Getter method for property result.
     *
     * @return property value of result
     */
    public Result getResult() {
        return result;
    }

    /**
     * Setter method for property result.
     *
     * @param result value to be assigned to property result
     */
    public void setResult(Result result) {
        this.result = result;
    }

    public APSResponse(ResponseHeader responseHeader, Result result) {
        this.responseHeader = responseHeader;
        this.result = result;
    }

    public APSResponse(Result result) {
        this.result = result;
    }

    public APSResponse() {
    }
}