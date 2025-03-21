package com.futurebank.order.feign;

import com.futurebank.pojo.base.CommonResp;
import com.futurebank.pojo.vo.paymentQuery.PaymentQueryUpstreamRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @author ben
 * @date 2024/6/2 18:32
 **/
@FeignClient(name = "trade-system-core")
public interface ApiConfigService {

    @PostMapping("/payin/orderQueryUpstream")
    CommonResp orderQueryUpstream(@RequestBody PaymentQueryUpstreamRequest orderQueryRequest);

}
