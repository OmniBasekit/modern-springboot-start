package com.sz.wechat.mini.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WechatUserService {
    private final WxMaService wxMaService;
    private final WxMaConfig wxMaConfig;


    public WxMaJscode2SessionResult loginByMiniAppCode(String code) throws WxErrorException, IllegalArgumentException {

        try {
            if (StrUtil.isBlank(code)) {
                throw new IllegalArgumentException("No wx.login() return value: code");
            }

            if (!wxMaService.switchover(wxMaConfig.getAppid())) {
                throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", wxMaConfig.getAppid()));
            }
            return wxMaService.getUserService().getSessionInfo(code);
        } finally {
            WxMaConfigHolder.remove();//清理ThreadLocal
        }

    }

    public WxMaUserInfo getUserInfo(String sessionKey,
                                    String signature, String rawData, String encryptedData, String iv) throws  IllegalArgumentException {


        try {
            // 用户信息校验
            if (!wxMaService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
                WxMaConfigHolder.remove();//清理ThreadLocal
                throw new IllegalArgumentException("checkUserInfo failed");
            }
            // 解密用户信息
            return wxMaService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } finally {
            WxMaConfigHolder.remove();//清理ThreadLocal
        }


    }
}
