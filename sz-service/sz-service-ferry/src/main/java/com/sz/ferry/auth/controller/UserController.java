package com.sz.ferry.auth.controller;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.dev33.satoken.annotation.SaIgnore;
import com.sz.core.common.entity.ApiResult;
import com.sz.core.common.enums.CommonResponseEnum;
import com.sz.wechat.mini.service.WechatUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/wx/user")
public class UserController {
    private final WechatUserService userService;

    /**
     * 登陆接口
     */
    @SaIgnore
    @GetMapping("/login/{code}")
    public ApiResult<WxMaJscode2SessionResult> login(@PathVariable String code) {
//        return ApiResult.success("success");

        try {
            WxMaJscode2SessionResult sessionResult = userService.loginByMiniAppCode(code);
            return ApiResult.success(sessionResult);
        } catch (WxErrorException e) {
            return ApiResult.error(CommonResponseEnum.UNKNOWN);
        }
    }

    /**
     * 登陆接口
     */
    @SaIgnore
    @GetMapping("/userInfo")
    public ApiResult<WxMaUserInfo> userInfo(String sessionKey, String signature, String rawData, String encryptedData, String iv) {

        try {
            WxMaUserInfo userInfo = userService.getUserInfo(sessionKey, signature, rawData, encryptedData, iv);
            return ApiResult.success(userInfo);
        } catch (RuntimeException e) {
            return ApiResult.error(CommonResponseEnum.UNKNOWN);
        }
    }
}