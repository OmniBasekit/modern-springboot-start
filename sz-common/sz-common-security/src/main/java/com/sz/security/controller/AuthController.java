package com.sz.security.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.sz.core.common.annotation.DebounceIgnore;
import com.sz.core.common.entity.ApiResult;
import com.sz.security.pojo.LoginInfo;
import com.sz.security.pojo.LoginVO;
import com.sz.security.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 认证Controller。
 *
 * @author sz
 * @since 2023/12/25
 */

@Tag(name = "认证")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @SaIgnore
    @DebounceIgnore
    @Operation(summary = "登录")
    @PostMapping("login")
    public ApiResult<LoginVO> login(@RequestBody LoginInfo loginInfo) {
        return ApiResult.success(authService.loginClient(loginInfo));
    }

    @SaIgnore
    @DebounceIgnore
    @Operation(summary = "登录")
    @PostMapping("test")
    public ApiResult<String> login() {
        return ApiResult.success("Test");
    }

    @Operation(summary = "登出")
    @PostMapping("logout")
    public ApiResult<Void> logout() {
        // 注意执行顺序，最后再执行logout
        StpUtil.getTokenSession().logout(); // 清除缓存session
        StpUtil.logout(); // 退出登录
        return ApiResult.success();
    }

}
