package com.futurebank.order.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.futurebank.cache.RedisCache;
import com.futurebank.order.entity.PaymentProviderEntity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ben
 * @date 2024/6/24 14:08
 **/
@Slf4j
@Service
public class KlashaTokenService {
    @Autowired
    HttpClientService httpClientService;

    @Autowired
    private RedisCache redisCache;


    public String getToken(PaymentProviderEntity paymentProviderEntity) {

        String klashaTokenKey = "Klasha:Login:Token";
        if (paymentProviderEntity==null) {
            log.error("paymentProvider is null");
            return null;
        }
        if (StringUtils.isBlank(paymentProviderEntity.getPayinConfig())) {
            log.error("paymentProvider.payinConfig is null");
            return null;
        }

        String url = getConfigValue(paymentProviderEntity.getPayinConfig(), "login.url");
        String username = getConfigValue(paymentProviderEntity.getPayinConfig(), "login.username");
        String passwd = getConfigValue(paymentProviderEntity.getPayinConfig(), "login.password");
        if (StringUtils.isBlank(url)) {
            log.error("paymentProvider.payinConfig.url is null");
            return null;
        }
        if (StringUtils.isBlank(username)) {
            log.error("paymentProvider.payinConfig.username is null");
            return null;
        }
        if (StringUtils.isBlank(passwd)) {
            log.error("paymentProvider.payinConfig.passwd is null");
            return null;
        }

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", passwd);
        String response = httpClientService.klashaJson(url, JSON.toJSONString(params), new HashMap<>(),null);
        log.info("klashaTokenService:getToken:response:{}", response);
        if (response == null)
            return null;
        JSONObject jsonObject = JSON.parseObject(response);
        if (jsonObject.get("data") != null) {
            JSONObject data = jsonObject.getJSONObject("data");
            if (data.get("token") != null) {
                String token = data.getString("token");
                if (StringUtils.isNotBlank(token)) {
                    redisCache.setCacheObject(klashaTokenKey, token);
                    return token;
                }
            }
        }
        return null;
    }

    public static String getConfigValue(String configJson, String key) {
        Gson gson = new Gson();
        JsonObject configObject = gson.fromJson(configJson, JsonObject.class);

        String[] keys = key.split("\\.");
        JsonElement value = configObject;

        for (String k : keys) {
            if (value instanceof JsonObject) {
                value = ((JsonObject) value).get(k);
            } else {
                return null;
            }
        }

        if (value != null) {
            if (value instanceof JsonObject) {
                return gson.toJson(value);
            } else {
                return value.getAsString();
            }
        } else {
            return null;
        }
    }



}
