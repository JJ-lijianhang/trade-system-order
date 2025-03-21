package com.futurebank.order.feign;

import com.futurebank.pojo.base.CommonResp;
import com.futurebank.order.vo.Invoice.SettlementNotifyVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ben
 * @date 2024/6/2 18:32
 **/
@FeignClient(name = "trade-system-cms")
public interface CMSApiService {

    @RequestMapping(value = "/mail/sendNotify", method = RequestMethod.POST)
    CommonResp sendNotify(@RequestBody SettlementNotifyVO settlementNotifyVO);

}
