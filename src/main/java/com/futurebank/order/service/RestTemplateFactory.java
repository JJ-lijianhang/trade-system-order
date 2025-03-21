package com.futurebank.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author ben
 * @date 2024/10/19 15:52
 **/
@Component
public class RestTemplateFactory {

    @Autowired
    @Qualifier("restTemplateWithDefault")
    private RestTemplate restTemplateWithDefault;

    @Autowired
    @Qualifier("restTemplateWithFuturepay")
    private RestTemplate restTemplateWithFuturepay;

    /**
     * 根据条件选择适当的 RestTemplate 实例
     */
    public RestTemplate getRestTemplate(String useCert) {
        if (useCert == null)
            return restTemplateWithDefault;
        if (useCert.equals("kgp")) {
            return restTemplateWithFuturepay;
        } else {
            return restTemplateWithDefault;
        }
    }
}
