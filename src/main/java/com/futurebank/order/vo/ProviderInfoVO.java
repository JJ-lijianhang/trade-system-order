package com.futurebank.order.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProviderInfoVO implements Serializable{
    private Long providerId;
    private String providerName;
    private String providerRename;
    private String productCapability;
    private Integer supportApis;
    private Integer supportGateway;
}
