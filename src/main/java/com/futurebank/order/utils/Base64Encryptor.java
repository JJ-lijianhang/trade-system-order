
package com.futurebank.order.utils;

/**
 * an interface that deals with encoding and decoding
 * @author wangpeng
 * @version $Id: Base64Encryptor.java, v 0.1 2022‐06-28 19:37:35 wangpeng Exp $
 */
public interface Base64Encryptor {

    /**
     * encode byte array to string
     * @param src byte array
     * @return byte array
     */
    String encodeToString(byte[] src);

    /**
     * decode string to byte array
     * @param src byte array
     * @return byte array
     */
    byte[] decode(String src);

}
