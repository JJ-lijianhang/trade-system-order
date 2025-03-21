package com.futurebank.order.pojo;

/**
 * @author ben
 * @date 2024/3/22 11:55
 **/

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class OrderDelayed implements Delayed {
    /* 触发时间*/
    private long time;

    /* 订单号*/
    String orderNo;

    public long getTime() {
        return time;
    }

    public OrderDelayed(long time, String orderNo, TimeUnit unit) {
        this.time = System.currentTimeMillis() + (time > 0? unit.toMillis(time): 0);
        this.orderNo = orderNo;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        OrderDelayed orderDelayed = (OrderDelayed) o;
        long diff = this.time - orderDelayed.time;
        if (diff <= 0) {
            return -1;
        }else {
            return 1;
        }
    }

    public String getOrderNo() {
        return orderNo;
    }


}