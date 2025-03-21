package com.futurebank.order.utils;

import com.futurebank.pojo.utils.FuturepayUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;

/**
 * MoneyUtils
 */
public class MoneyUtils {

    /**
     * 格式化金额
     * @param currency
     * @param money
     * @return
     */
    public static String formatMoney(String currency, BigDecimal money) {
        if (money != null && !money.equals(BigDecimal.ZERO)){
            return money.setScale(FuturepayUtils.getDecimalPlaces(currency), BigDecimal.ROUND_HALF_DOWN).toString();
        }
        return "";
    }

    /**
     * 格式化金额-添加千分位
     * @param currency
     * @param money
     * @return
     */
    public static String thousandsSeparator(String currency, BigDecimal money) {
        BigDecimal result = Optional.ofNullable(money).orElse(BigDecimal.ZERO);
        // 获取 NumberFormat 实例，使用 US 地区的格式（逗号作为千分位分隔符）
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        // 设置最小和最大小数位数
        numberFormat.setMinimumFractionDigits(FuturepayUtils.getDecimalPlaces(currency));
        numberFormat.setMaximumFractionDigits(FuturepayUtils.getDecimalPlaces(currency));
        // 格式化金额
       return numberFormat.format(result);
    }
}
