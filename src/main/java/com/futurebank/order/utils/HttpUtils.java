package com.futurebank.order.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ben
 * @date 2024/2/6 13:28
 **/


public class HttpUtils {



    /**
     * 判断请求类型是否为表单类型
     *
     * @param request HTTP请求
     * @return true表示为表单类型，false表示不是表单类型
     */
    public static boolean isFormRequest(HttpServletRequest request) {
        String contentType = request.getContentType();
        return StringUtils.hasText(contentType) && contentType.startsWith(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
    }

    /**
     * 判断请求类型是否为数据流类型
     *
     * @param request HTTP请求
     * @return true表示为数据流类型，false表示不是数据流类型
     */
    public static boolean isStreamRequest(HttpServletRequest request) {
        String contentType = request.getContentType();
        return StringUtils.hasText(contentType)
                && (contentType.startsWith(MediaType.APPLICATION_OCTET_STREAM_VALUE)
                || contentType.startsWith(MediaType.APPLICATION_JSON_VALUE));
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }


    /**
     * get map from header array
     * @param allHeaders  header array
     * @return  header map
     */
    public static Map<String, String> toHeaderMap(HttpHeaders allHeaders) {
        Map<String, String> headerMap = new HashMap<>();

        // 遍历所有 header
        for (Map.Entry<String, List<String>> entry : allHeaders.entrySet()) {
            String headerName = entry.getKey();
            List<String> headerValues = entry.getValue();

            // 如果只有一个值，使用 getFirst()
            if (headerValues.size() == 1) {
                headerMap.put(headerName, headerValues.get(0));
            } else {
                // 如果有多个值，将它们合并为一个字符串
                String joinedValues = String.join(", ", headerValues);
                headerMap.put(headerName, joinedValues);
            }
        }

        return headerMap;
    }

}

