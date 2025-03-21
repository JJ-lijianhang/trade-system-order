package com.futurebank.order.utils.FeeRate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class FeeJsonDelUtils {

    /**
     *
     * @param feeRateJson
     * @return
      */
    public static List<FeeRate> FeeJsonToList(String feeRateJson) {
        List<FeeRate> feeJson = new ArrayList<>();
        try {
            JSONObject fixFeeFsonObject = JSON.parseObject(feeRateJson);
            if (fixFeeFsonObject == null) {
                return feeJson;
            }
            for (Map.Entry<String, Object> entry : fixFeeFsonObject.entrySet()) {
                String feeType = entry.getKey();
                String feeRateV = String.valueOf(entry.getValue());
                String amount = feeRateV.replaceAll("[^0-9.]", "");
                String currency = feeRateV.replaceAll("[^A-Za-z]", "");
                FeeRate feeRate = new FeeRate();
                feeRate.setFeeType(feeType);
                feeRate.setFeeRate(amount);
                feeRate.setCurrency(currency);
                feeJson.add(feeRate);
            }
        } catch (Exception e) {
            log.error("feeJson deal error {}", feeRateJson);
        }
        return feeJson;
    }

    /**
     *
     * @param feeRateJson
     * @return
     */
    public static FeeRate FeeJsonToRate(String feeRateJson) {
        if (feeRateJson == null){
            return new FeeRate();
        }
        List<FeeRate> feeJson = new ArrayList<>();
        try {
            JSONObject fixFeeFsonObject = JSON.parseObject(feeRateJson);
            if (fixFeeFsonObject.isEmpty()) {
                return new FeeRate();
            }
            for (Map.Entry<String, Object> entry : fixFeeFsonObject.entrySet()) {
                String feeType = entry.getKey();
                String feeRateV = String.valueOf(entry.getValue());
                String amount = feeRateV.replaceAll("[^0-9.]", "");
                String currency = feeRateV.replaceAll("[^A-Za-z]", "");
                FeeRate feeRate = new FeeRate();
                feeRate.setFeeType(feeType);
                feeRate.setFeeRate(amount);
                feeRate.setCurrency(currency);
                feeJson.add(feeRate);
            }
            return feeJson.get(0);
        } catch (Exception e) {
            log.error("feeJson deal error {}", feeRateJson);
        }
        return new FeeRate();
    }

}
