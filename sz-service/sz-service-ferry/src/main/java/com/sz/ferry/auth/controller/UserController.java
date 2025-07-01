package com.sz.ferry.auth.controller;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.sz.core.common.entity.ApiResult;
import com.sz.core.common.enums.CommonResponseEnum;
import com.sz.ferry.auth.pojo.dto.AppletLoginDTO;
import com.sz.ferry.auth.service.AppletAuthService;
import com.sz.security.pojo.LoginVO;
import com.sz.wechat.mini.service.WechatUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/wx")
public class UserController {
    private final WechatUserService userService;
    private final AppletAuthService appletAuthService;

    /**
     * 登陆接口
     */
    @SaIgnore
    @PostMapping("/login")
    public ApiResult<LoginVO> login(@RequestBody AppletLoginDTO dto) {
        LoginVO login = appletAuthService.login(dto.getCode());
        return ApiResult.success(login);
    }



    /**
     * 登陆接口
     */
    @GetMapping("/userInfo")
    public ApiResult<WxMaUserInfo> userInfo(String sessionKey,String signature,String rawData,String encryptedData, String iv) {


        try {
            WxMaUserInfo userInfo = userService.getUserInfo(sessionKey, signature, rawData, encryptedData, iv);
            return ApiResult.success(userInfo);
        } catch (RuntimeException e) {
            log.error("获取用户信息失败：", e);
            Object extraUserInfo = StpUtil.getTerminalInfo().getExtraData();
            log.info("extraUserInfo:",extraUserInfo);
            return ApiResult.error(CommonResponseEnum.UNKNOWN);
        }
    }
}