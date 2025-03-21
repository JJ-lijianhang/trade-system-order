/**
 *   Legal Disclaimer
 *   Source Code or Sample Code not belongs to Alipay technological assets, partners agree to take full responsibility for any activities or actions under its use.
 *   Partners are authorised to use for Approved Purposes.
 *   "Approved Purpose" means, subject to any restrictions set out in the Participation Documents, (i) in relation to a Partner, as is necessary in order to facilitate that Partner’s participation in Alipay+ Core; and (ii) in relation to Alipay+, as is necessary in order to facilitate the provision of the Services (including the exercise of rights and performance of obligations under Participation Documents, managing AML/Fraud Risk, resolving any Disputes or facilitating the provision of Partner Products by other Partners).
 *   You should check our NDA for detail Legal T&C.
 */
package com.futurebank.order.utils;


import jakarta.xml.bind.DatatypeConverter;

/**
 * an interface that deal with encoding and decoding
 * @author mulin.xl
 * @version $Id: DefaultBase64Encryptor.java, v 0.1 2019‐10-16 16:25:49 mulin.xl Exp $
 */
public class DefaultBase64Encryptor implements Base64Encryptor {

    /**
     * encode byte array to string
     * @param src byte array
     * @return byte array
     */
    @Override
    public String encodeToString(byte[] src) {
        return DatatypeConverter.printBase64Binary(src);
    }

    /**
     * decode string to byte array
     * @param src byte array
     * @return byte array
     */
    @Override
    public byte[] decode(String src) {
        return DatatypeConverter.parseBase64Binary(src);
    }

}
