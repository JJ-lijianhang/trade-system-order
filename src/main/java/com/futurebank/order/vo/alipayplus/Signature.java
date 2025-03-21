/**
 *   Legal Disclaimer
 *   Source Code or Sample Code not belongs to Alipay technological assets, partners agree to take full responsibility for any activities or actions under its use.
 *   Partners are authorised to use for Approved Purposes.
 *   "Approved Purpose" means, subject to any restrictions set out in the Participation Documents, (i) in relation to a Partner, as is necessary in order to facilitate that Partner’s participation in Alipay+ Core; and (ii) in relation to Alipay+, as is necessary in order to facilitate the provision of the Services (including the exercise of rights and performance of obligations under Participation Documents, managing AML/Fraud Risk, resolving any Disputes or facilitating the provision of Partner Products by other Partners).
 *   You should check our NDA for detail Legal T&C.
 */
package com.futurebank.order.vo.alipayplus;


import com.futurebank.order.utils.StringUtil;

/**
 * Signature
 * @author mulin.xl
 * @version $Id: Signature.java, v 0.1 2022‐06‐23 12:57 mulin.xl Exp $$
 */
public class Signature {

    String algorithm  = "RSA256";
    String keyVersion = "1";
    String signature;

    public Signature() {

    }

    /**
     * @param headString - something like 'algorithm=RSA256,keyVersion=2,signature=Lv06x'
     */
    public Signature(String headString) {
        this.algorithm = StringUtil.substringBetween(headString, "algorithm=", ",");
        this.keyVersion = StringUtil.substringBetween(headString, "keyVersion=", ",");
        this.signature = StringUtil.substringAfter(headString, "signature=");
    }

    public String toString() {
        return String.format("algorithm=%s,keyVersion=%s,signature=%s", algorithm, keyVersion,
            signature);
    }
    //
    //    public boolean validate() {
    //        return StringUtil.isNotBlank(signature);
    //    }

    /**
     * Getter method for property algorithm.
     *
     * @return property value of algorithm
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * Setter method for property algorithm.
     *
     * @param algorithm value to be assigned to property algorithm
     */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Getter method for property keyVersion.
     *
     * @return property value of keyVersion
     */
    public String getKeyVersion() {
        return keyVersion;
    }

    /**
     * Setter method for property keyVersion.
     *
     * @param keyVersion value to be assigned to property keyVersion
     */
    public void setKeyVersion(String keyVersion) {
        this.keyVersion = keyVersion;
    }

    /**
     * Getter method for property signature.
     *
     * @return property value of signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Setter method for property signature.
     *
     * @param signature value to be assigned to property signature
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }
}