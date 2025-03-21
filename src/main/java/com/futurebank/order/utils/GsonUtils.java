/**
 * Legal Disclaimer
 * Source Code or Sample Code not belongs to Alipay technological assets, partners agree to take full responsibility for any activities or actions under its use.
 * Partners are authorised to use for Approved Purposes.
 * "Approved Purpose" means, subject to any restrictions set out in the Participation Documents, (i) in relation to a Partner, as is necessary in order to facilitate that Partner’s participation in Alipay+ Core; and (ii) in relation to Alipay+, as is necessary in order to facilitate the provision of the Services (including the exercise of rights and performance of obligations under Participation Documents, managing AML/Fraud Risk, resolving any Disputes or facilitating the provision of Partner Products by other Partners).
 * You should check our NDA for detail Legal T&C.
 */
package com.futurebank.order.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * a gson util that deals with gson object and string
 *
 * @author wangpeng
 * @version $Id: GsonUtils.java, v 0.1 2022‐06-28 19:37:35 GsonUtils Exp $
 */
public class GsonUtils {

    /** static field GSON **/
    private static final Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.disableHtmlEscaping().create();
    }

    /**
     * get JSON string from Object
     * @param value object
     * @return json string
     */
    public static String toJSONString(Object value) {
        return gson.toJson(value);
    }

    /**
     * get Object from JSon string
     * @param content json string
     * @param clazz Class
     * @param <T> type of Object
     * @return
     */
    public static <T> T toJavaObject(String content, Class<T> clazz) {
        return gson.fromJson(content, clazz);
    }
}
