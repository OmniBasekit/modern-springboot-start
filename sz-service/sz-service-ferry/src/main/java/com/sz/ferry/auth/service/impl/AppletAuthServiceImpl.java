package com.sz.ferry.auth.service.impl;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.sz.core.util.JsonUtils;

import com.sz.ferry.auth.service.AppletAuthService;
import com.sz.ferry.user.pojo.po.User;
import com.sz.ferry.user.service.UserService;
import com.sz.ferry.user.third.pojo.po.UserThirdAccount;
import com.sz.ferry.user.third.service.UserThirdAccountService;
import com.sz.security.core.util.LoginUtils;
import com.sz.security.pojo.LoginVO;
import com.sz.wechat.mini.service.WechatUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppletAuthServiceImpl implements AppletAuthService {
    private final WechatUserService wechatUserService;
    private final UserService userService;
    private final UserThirdAccountService userThirdAccountService;

    @Transactional
    public LoginVO login(String code) {


        try {
            WxMaJscode2SessionResult result = wechatUserService.loginByMiniAppCode(code);
            log.info(" 小程序登录返回信息：{}", JsonUtils.toJsonString(result));
            String openid = result.getOpenid();

            String sessionKey = result.getSessionKey(); // 小程序登录凭证
            QueryWrapper wrapper = QueryWrapper.create().eq(UserThirdAccount::getUniqueId, code);
            UserThirdAccount userThirdAccount = userThirdAccountService.getOneOpt(wrapper).orElse(new UserThirdAccount());

            User user;

            if (userThirdAccount.getUserId() == null) {
                //更新用户表
                var userRecord = new User();
                userRecord.setName("微信用户");
                userService.save(userRecord);
                user = userRecord;
                //更新第三方用户表
                userThirdAccount.setUserId(userRecord.getId());
                userThirdAccountService.saveOrUpdate(userThirdAccount);
            } else {
                user = userService.getById(userThirdAccount.getUserId());
            }

            Long userId = userThirdAccount.getUserId();
            SaLoginModel model = createLoginModel();


            // 设置jwt额外数据
            Map<String, Object> extraData = createExtraData(sessionKey, userId, openid);
            // 执行登录
            LoginUtils.performMiniLogin(userId, user, model, extraData);
            // 构造返回对象
            return createLoginVO(user);
        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }


    }

    private SaLoginModel createLoginModel() {
        SaLoginModel model = new SaLoginModel();
//        model.setDevice(client.getDeviceTypeCd());
//        model.setTimeout(client.getTimeout());
//        model.setActiveTimeout(client.getActiveTimeout());
        return model;
    }

    private Map<String, Object> createExtraData(String sessionKey, Long userId, String openid) {
        Map<String, Object> extraData = new HashMap<>();
        extraData.put("sessionKey", sessionKey);
        extraData.put("userId", userId);
        extraData.put("openId", openid);
        return extraData;
    }

    private LoginVO createLoginVO(User user) {
        LoginVO loginVo = new LoginVO();
        loginVo.setAccessToken(StpUtil.getTokenValue());
        loginVo.setExpireIn(StpUtil.getTokenTimeout());
        loginVo.setUserInfo(user);
        return loginVo;
    }
}
