/**
 *   Legal Disclaimer
 *   Source Code or Sample Code not belongs to Alipay technological assets, partners agree to take full responsibility for any activities or actions under its use.
 *   Partners are authorised to use for Approved Purposes.
 *   "Approved Purpose" means, subject to any restrictions set out in the Participation Documents, (i) in relation to a Partner, as is necessary in order to facilitate that Partner’s participation in Alipay+ Core; and (ii) in relation to Alipay+, as is necessary in order to facilitate the provision of the Services (including the exercise of rights and performance of obligations under Participation Documents, managing AML/Fraud Risk, resolving any Disputes or facilitating the provision of Partner Products by other Partners).
 *   You should check our NDA for detail Legal T&C.
 */
package com.futurebank.order.utils;

/**
 * an util that deal with String 
 * @author guangling.zgl
 * @version $Id: StringUtil.java, v 0.1 2019‐10-16 16:28:23 guangling.zgl Exp $
 */
public class StringUtil {

    /**
     * check if string is blank
     * 
     * @param str string
     * @return  if string is blank
     */
    public static boolean isBlank(String str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * sub string 
     * @param str string
     * @param open start position
     * @param close end position
     * @return string
     */
    public static String substringBetween(String str, String open, String close) {
        return substringBetween(str, open, close, 0);
    }

    public static final String EMPTY_STRING = "";

    /**
     * sub string 
     * @param str string
     * @param separator the char which begin to sub string at 
     * @return string
     */
    public static String substringAfter(String str, String separator) {
        if ((str == null) || (str.length() == 0)) {
            return str;
        }

        if (separator == null) {
            return EMPTY_STRING;
        }

        int pos = str.indexOf(separator);

        if (pos == -1) {
            return EMPTY_STRING;
        }

        return str.substring(pos + separator.length());
    }

    /**
     * sub string 
     * @param str string
     * @param open start position
     * @param close end position
     * @return string
     */
    public static String substringBetween(String str, String open, String close, int fromIndex) {
        if ((str == null) || (open == null) || (close == null)) {
            return null;
        }

        int start = str.indexOf(open, fromIndex);

        if (start != -1) {
            int end = str.indexOf(close, start + open.length());

            if (end != -1) {
                return str.substring(start + open.length(), end);
            }
        }

        return null;
    }

    /**
     * check if string is blank
     *
     * @param str string
     * @return  if string is blank
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * gen request id string
     * 
     * @param apiName like payment , cancel , refund 
     * @return request id string , like payment_20220712181112_15
     */
    public static String genRequestID(String apiName) {
        return apiName + "_" + DateUtil.getSimpleFormatTime() + "_"
               + (int) (Math.random() * 90 + 10);
    }
}
