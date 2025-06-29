package com.sz.wechat.pay.service.pojo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WechatCreateOrderParams {
    private String callbackUrl;
    private String openid;
    private String orderId;
    private String description;
    private Integer totalAmount;
}
