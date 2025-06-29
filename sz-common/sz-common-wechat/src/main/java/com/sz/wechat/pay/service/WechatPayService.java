package com.sz.wechat.pay.service;

import cn.hutool.core.util.StrUtil;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.sz.wechat.pay.service.pojo.dto.WechatCreateOrderParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class WechatPayService {

    private final WxPayService wxPayService;


    public void createOrder(WechatCreateOrderParams wechatCreateOrderParams) throws WxPayException {
        WxPayUnifiedOrderV3Request wxPayUnifiedOrderRequest = new WxPayUnifiedOrderV3Request();
        WxPayUnifiedOrderV3Request.Amount amount = new WxPayUnifiedOrderV3Request.Amount();
        amount.setCurrency("CNY");
        amount.setTotal(wechatCreateOrderParams.getTotalAmount());
        wxPayUnifiedOrderRequest.setAmount(amount);
        wxPayUnifiedOrderRequest.setNotifyUrl(wechatCreateOrderParams.getCallbackUrl());
        wxPayUnifiedOrderRequest.setDescription(wechatCreateOrderParams.getDescription());
        WxPayUnifiedOrderV3Request.Payer payer = new WxPayUnifiedOrderV3Request.Payer();
        payer.setOpenid(wechatCreateOrderParams.getOpenid());
        wxPayUnifiedOrderRequest.setPayer(payer);
        wxPayUnifiedOrderRequest.setOutTradeNo(wechatCreateOrderParams.getOrderId());


        wxPayService.createOrderV3(TradeTypeEnum.JSAPI, wxPayUnifiedOrderRequest);
    }
}
