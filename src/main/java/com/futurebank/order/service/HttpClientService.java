package com.futurebank.order.service;

import com.alibaba.fastjson.JSON;
import com.futurebank.order.utils.HttpUtils;
import com.futurebank.order.utils.SignatureTool;
import com.futurebank.order.vo.alipayplus.ResponseHeader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author ben
 * @date 2024/2/12 17:02
 **/

@Service
@Slf4j
public class HttpClientService {

    @Autowired
    private RestTemplateFactory restTemplateFactory;


    public String postWithKgpToken(String url, Map<String, Object> paramMap, Map<String, Object> headerMap, String useCert) throws Exception {
        log.info("kgp url:{} paramMap:{} headerMap:{} ", url, paramMap, headerMap);
        HttpHeaders headers = new HttpHeaders();
        if ((headerMap != null && !headerMap.isEmpty())) {
            for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
                if (entry.getValue() != null) {
                    headers.set(entry.getKey(), entry.getValue().toString());
                }
            }
        }

        MultiValueMap<String, Object> formData = convertForm(paramMap);
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(formData, headers);

        try {

            ResponseEntity response = restTemplateFactory.getRestTemplate(useCert).postForEntity(url, files, String.class);
            log.info("ppro-success url: {} request:{} , response:{} headerMap:{}", url, paramMap, response, headerMap);
            return response == null ? null : response.getBody().toString();
        } catch (HttpStatusCodeException e) { // 客户端 服务器异常
            log.info("ppro-error url: {} request:{} , response:{} token:{}", url, paramMap, e.getResponseBodyAsString(), headerMap);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("ppro-error url: {} request:{} , response:{} token:{}", url, paramMap, e.getMessage(), headerMap);
            return null;
        }

    }

    /**
     * 带token的post方式请求接口
     *
     * @param url
     * @param paramMap
     * @param token
     * @return
     * @throws Exception
     */
    public Map<String, String> postWithPPRO(String url, Map<String, Object> paramMap, String token, String useCert) throws Exception {
        log.info("ppro request:{} , token:{}", paramMap, token);
        HttpHeaders headers = new HttpHeaders();
        if (!org.apache.commons.lang3.StringUtils.isBlank(token))
            headers.set("token", token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //将map转表单
        MultiValueMap<String, Object> formData = convertForm(paramMap);

        //用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(formData, headers);

        try {
            ResponseEntity response = restTemplateFactory.getRestTemplate(useCert).postForEntity(url, files, String.class);
            log.info("ppro-success url: {} request:{} , response:{} token:{}", url, paramMap, response, token);
            String contentType = response.getHeaders().getContentType().toString();
            if (contentType.contains("x-www-form-urlencoded") || contentType.contains("text/plain")) {
                return convertMap(response.getBody().toString());
            } else {
                return getTextBody(response.getBody().toString());
            }
        } catch (HttpStatusCodeException e) { // 客户端 服务器异常
            log.info("ppro-error url: {} request:{} , response:{} token:{}", url, paramMap, e.getResponseBodyAsString(), token);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("ppro-error url: {} request:{} , response:{} token:{}", url, paramMap, e.getMessage(), token);

            return null;

        }

    }

    /**
     * AlipayPlus post方式请求接口
     * - 参数形式：json
     */
    public String alipayPlusHttpPost(String url, String param, HttpHeaders headers,String useCert,String clientId,String httpMethod,String requestURI,String publicKey) {
        long l = System.currentTimeMillis();
        HttpEntity<String> httpEntity = new HttpEntity<>(param, headers);
        try {
            ResponseEntity<String> response = restTemplateFactory.getRestTemplate(useCert).postForEntity(url, httpEntity, String.class);
            int status = response.getStatusCodeValue();
            if ((status >= 200 && status < 300) || status == 302) {
                Map<String, String> toHeaderMap = HttpUtils.toHeaderMap(response.getHeaders());
                ResponseHeader responseHeader = new ResponseHeader();
                responseHeader.fromMap(toHeaderMap);
                String rspBody = new String(IOUtils.toByteArray(response.getBody()), StandardCharsets.UTF_8);
                boolean isVerify = SignatureTool.verify(httpMethod, requestURI,
                        clientId, responseHeader.getResponseTime(),
                        rspBody, responseHeader.getSignature().getSignature(),
                        publicKey);
                if (!isVerify){
                    log.error(String.format("responseHeader[%s]: [requestURI=[%s]], responseBody=[%s]", JSON.toJSON(responseHeader).toString(), requestURI, rspBody));
                    return null;
                }
                log.info("AlipayPlus 200 url:{} param:{} headers:{} response:{} times:{}", url, param, headers, rspBody, System.currentTimeMillis() - l);
                return rspBody;
            }
            return null;
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            Throwable cause = e.getCause();
            String causeMessage = (cause != null) ? cause.getMessage() : "Unknown cause";

            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("error", "ResourceAccessException");
            errorDetails.put("message", errorMessage);
            errorDetails.put("cause", causeMessage);
            errorDetails.put("httpCode", -2);
            String body = JSON.toJSONString(errorDetails);
            log.info("AlipayPlus -2 url:{} param:{} headers:{} response:{} times:{}", url, param, headers, body, System.currentTimeMillis() - l);
            e.printStackTrace();
            return null;
        }
    }

    public String postKlasha(String url, String param, Map<String, String> header, String useCert) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        for (EnumMap.Entry<String, String> entry : header.entrySet()) {
            headers.set(entry.getKey(), entry.getValue());
        }
        HttpEntity<String> entity = new HttpEntity<>(param, headers);

        try {
            ResponseEntity response = restTemplateFactory.getRestTemplate(useCert).exchange(url, HttpMethod.POST, entity, String.class);
            log.info("klasha url: {} request:{} , response:{} token:{} body:{}", url, param, response, header, response.getBody());
            return response.getBody().toString();
        } catch (HttpStatusCodeException e) { // 客户端 服务器异常
            return e.getResponseBodyAsString();
        } catch (Exception e) {    // 其他异常 [ io 网络异常（连接超时 主机不可到达）]
            log.info("klasha request:{} , response:{} token:{}", param, e.getMessage(), header);
            return e.getMessage();
        }


    }

    public String getKlasha(String url, String param, Map<String, String> header, String useCert) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        for (EnumMap.Entry<String, String> entry : header.entrySet()) {
            headers.set(entry.getKey(), entry.getValue());
        }
        HttpEntity<String> entity = new HttpEntity<>(param, headers);

        try {
            ResponseEntity response = restTemplateFactory.getRestTemplate(useCert).exchange(url, HttpMethod.GET, entity, String.class);
            log.info("klasha url: {} request:{} , response:{} token:{} body:{}", url, param, response, header, response.getBody());
            return response.getBody().toString();
        } catch (HttpStatusCodeException e) {
            return e.getResponseBodyAsString();
        } catch (Exception e) {
            log.info("klasha request:{} , response:{} token:{}", param, e.getMessage(), header);
            return e.getMessage();
        }


    }

    /**
     * 带token的post方式请求接口
     *
     * @param url
     * @param param
     * @param header
     * @return
     */
    public String postNotify(String url, String param, Map<String, String> header, String useCert) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        for (EnumMap.Entry<String, String> entry : header.entrySet()) {
            headers.set(entry.getKey(), entry.getValue());
        }
        HttpEntity<String> entity = new HttpEntity<>(param, headers);

        try {
            ResponseEntity response = restTemplateFactory.getRestTemplate(useCert).exchange(url, HttpMethod.POST, entity, String.class);
            log.info("url: {} request:{} , response:{} token:{} body:{}", url, param, response, header, response.getBody());
            return response.getBody().toString();
        } catch (HttpStatusCodeException e) {
            return e.getResponseBodyAsString();
        } catch (Exception e) {
            log.info("notify2 request:{} , response:{} token:{}", param, e.getMessage(), header);
            return e.getMessage();
        }


    }


    public String get(String uri, Map<String, String> param, String token, String useCert) {
        HttpHeaders headers = new HttpHeaders();
        if (org.apache.commons.lang3.StringUtils.isBlank(token))
            headers.set("token", token);
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        StringBuilder url = new StringBuilder();
        url.append(uri).append("?");
        param.forEach((k, v) -> url.append(k).append("=").append(v).append("&"));
        String getUrl = url.substring(0, url.length() - 1);

        ResponseEntity response = restTemplateFactory.getRestTemplate(useCert).exchange(getUrl, HttpMethod.GET, requestEntity, String.class);

        return response.getBody().toString();

    }



    public String klashaJson(String url, String param, Map<String, String> token, String useCert) {
        log.info("url:{} param:{} token:{}", url, param, JSON.toJSONString(token));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        for (EnumMap.Entry<String, String> entry : token.entrySet()) {
            headers.set(entry.getKey(), entry.getValue());
        }
        HttpEntity<String> entity = new HttpEntity<>(param, headers);
        try {
            ResponseEntity response = restTemplateFactory.getRestTemplate(useCert).postForEntity(url, entity, Map.class);
            log.info("klasha-{} : {}", response.getStatusCode(), response);
            return JSON.toJSONString(response.getBody());

        } catch (HttpClientErrorException e) {
            log.info("klasha-{} : {}", e.getStatusCode(), e.getResponseBodyAsString());
            return e.getResponseBodyAsString();
        } catch (HttpServerErrorException e) {
            log.info("klasha-{} : {}", e.getStatusCode(), e.getResponseBodyAsString());
            return e.getResponseBodyAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String klashaGet(String url, String param, Map<String, String> token, String useCert) {
        log.info("url:{} param:{} token:{}", url, param, JSON.toJSONString(token));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        for (EnumMap.Entry<String, String> entry : token.entrySet()) {
            headers.set(entry.getKey(), entry.getValue());
        }
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Map> response = restTemplateFactory.getRestTemplate(useCert).exchange(url, HttpMethod.GET, entity, Map.class);
            log.info("klasha-{} : {}", response.getStatusCode(), response);
            return JSON.toJSONString(response.getBody());
        } catch (HttpClientErrorException e) {
            log.info("klasha-{} : {}", e.getStatusCode(), e.getResponseBodyAsString());
            return e.getResponseBodyAsString();
        } catch (HttpServerErrorException e) {
            log.info("klasha-{} : {}", e.getStatusCode(), e.getResponseBodyAsString());
            return e.getResponseBodyAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static Map<String, String> convertMap(String queryString) throws Exception {

        queryString = URLDecoder.decode(queryString, StandardCharsets.UTF_8);

        // 使用UriComponentsBuilder解析URL参数
        Map<String, String> queryParams = UriComponentsBuilder.fromUriString("http://localhost?" + queryString)
                .build().getQueryParams().toSingleValueMap();
        return queryParams;
    }

    public static Map<String, String> getTextBody(String responseBody) {
        // 定义正则表达式匹配模式
        Pattern pattern = Pattern.compile("([A-Z]+):([^\\n]+)");

        // 创建 Map 对象来存储键值对
        Map<String, String> keyValueMap = new HashMap<>();

        // 创建 Matcher 对象，并应用正则表达式匹配
        Matcher matcher = pattern.matcher(responseBody);

        // 遍历匹配结果，提取键值对并存储到 Map 中
        while (matcher.find()) {
            String key = matcher.group(1).trim();
            String value = matcher.group(2).trim();
            // 存储键值对到 Map 中
            keyValueMap.put(key, value);
        }
        return keyValueMap;

    }



    public static MultiValueMap convertForm(Map<String, Object> map) {
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            formData.add(entry.getKey(), entry.getValue());
        }
        return formData;
    }

    public String getExchangeRate(String uri, Map<String, String> param, String token, String useCert) {
        try {
            HttpHeaders headers = new HttpHeaders();
            if (org.apache.commons.lang3.StringUtils.isBlank(token))
                headers.set("token", token);
            HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
            StringBuilder url = new StringBuilder();
            url.append(uri).append("?");
            param.forEach((k, v) -> url.append(k).append("=").append(v).append("&"));
            String getUrl = url.substring(0, url.length() - 1);
            ResponseEntity response = restTemplateFactory.getRestTemplate(useCert).exchange(getUrl, HttpMethod.GET, requestEntity, String.class);
            return response.getBody().toString();
        } catch (HttpStatusCodeException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public String exchangeRate(String source, String currencies) {
        Map<String, String> exMap = new HashMap<>();
        exMap.put("access_key", "9781ff5b49fb9283e4dbd2491f8f2db8");
        exMap.put("currencies", currencies);
        exMap.put("source", source);
        exMap.put("format", "1");

        String result = getExchangeRate("https://apilayer.net/api/live", exMap, null, null);
        log.info("exchangeRate result:{}", result);
        return result;
    }


    public String uploadFile(String url, InputStream inputStream, Map<String, String> headerMap, String fileName, String useCert) throws  IOException {
        log.info("url:{} headerMap:{} fileName:{} data:{}", url, headerMap, fileName, inputStream);
        // 创建MultiValueMap
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//        body.add("document", new InputStreamResource(inputStream));

        byte[] fileBytes;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            fileBytes = baos.toByteArray();
        }
        body.add("document", new ByteArrayResource(fileBytes) {
            @Override
            public String getFilename() {
                return fileName;
            }
        });
        // 添加文档分类参数
        body.add("documentCategory", "REFUND_DOCUMENT");
        body.add("documentSubCategory", "SLIP");

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        if ((headerMap != null && !headerMap.isEmpty())) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                if (entry.getValue() != null) {
                    headers.set(entry.getKey(), entry.getValue().toString());
                }
            }
        }
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // 创建HttpEntity
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);


        // 发送POST请求并接收响应

        try {
            ResponseEntity<Map> response = restTemplateFactory.getRestTemplate(useCert).exchange(url, HttpMethod.POST, requestEntity, Map.class);
            log.info("klasha-{} : {}", response.getStatusCode(), response);
            return JSON.toJSONString(response.getBody());
        } catch (HttpClientErrorException e) {
            log.info("klasha-{} : {}", e.getStatusCode(), e.getResponseBodyAsString());
            return e.getResponseBodyAsString();
        } catch (HttpServerErrorException e) {
            log.info("klasha-{} : {}", e.getStatusCode(), e.getResponseBodyAsString());
            return e.getResponseBodyAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
