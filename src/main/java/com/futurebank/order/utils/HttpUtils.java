package com.futurebank.order.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    // 发送 POST 请求
    public static String sendPostRequest(String apiUrl, Map<String, Object> params) throws Exception {
        // 将参数转为 JSON 字符串
        String jsonInputString = JSONObject.toJSONString(params);

        // 创建 URL 连接
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);

        // 写入请求体
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        // 读取响应
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // 读取响应
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }
            // 返回响应内容
            return response.toString();
        }else {
            throw new RuntimeException("请求失败，响应码：" + responseCode);
        }
    }
    public static String generateSHA256(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 加密失败", e);
        }
    }

}

