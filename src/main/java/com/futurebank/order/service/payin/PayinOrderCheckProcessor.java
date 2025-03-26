package com.futurebank.order.service.payin;//package com.futurebank.payment.service.payin;


import com.futurebank.order.service.PaymentOrderService;
import com.futurebank.order.service.PaymentProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class PayinOrderCheckProcessor {

    @Autowired
    private PaymentOrderService paymentService;

    @Autowired
    PaymentProviderService paymentProviderService;


    public void checkAllData() throws Exception {

        //paymentService.


    }

}
