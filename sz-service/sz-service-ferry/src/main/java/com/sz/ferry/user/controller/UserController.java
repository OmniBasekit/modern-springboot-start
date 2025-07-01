package com.sz.ferry.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import com.sz.core.common.entity.ApiPageResult;
import com.sz.core.common.entity.ApiResult;
import com.sz.core.common.constant.GlobalConstant;
import com.sz.core.common.entity.PageResult;
import com.sz.core.common.entity.SelectIdsDTO;
import com.sz.ferry.user.service.UserService;
import com.sz.ferry.user.pojo.dto.UserCreateDTO;
import com.sz.ferry.user.pojo.dto.UserUpdateDTO;
import com.sz.ferry.user.pojo.dto.UserListDTO;
import com.sz.ferry.user.pojo.vo.UserVO;

/**
 * <p>
 * 用户表 Controller
 * </p>
 *
 * @author Rain
 * @since 2025-06-30
 */
@Tag(name =  "用户表")
@RestController(value = "FerryUserController")
@RequestMapping("ferry-user")
@RequiredArgsConstructor
public class UserController  {

    private final UserService userService;

    @Operation(summary = "新增")
    @SaCheckPermission(value = "ferry.user.create")
    @PostMapping
    public ApiResult<Void> create(@RequestBody UserCreateDTO dto) {
        userService.create(dto);
        return ApiResult.success();
    }

    @Operation(summary = "修改")
    @SaCheckPermission(value = "ferry.user.update")
    @PutMapping
    public ApiResult<Void> update(@RequestBody UserUpdateDTO dto) {
        userService.update(dto);
        return ApiResult.success();
    }

    @Operation(summary = "删除")
    @SaCheckPermission(value = "ferry.user.remove")
    @DeleteMapping
    public ApiResult<Void> remove(@RequestBody SelectIdsDTO dto) {
        userService.remove(dto);
        return ApiResult.success();
    }

    @Operation(summary = "列表查询")
    @SaCheckPermission(value = "ferry.user.query_table")
    @GetMapping
    public ApiResult<PageResult<UserVO>> list(UserListDTO dto) {
        return ApiPageResult.success(userService.page(dto));
    }

    @Operation(summary = "详情")
    @SaCheckPermission(value = "ferry.user.query_table")
    @GetMapping("/{id}")
    public ApiResult<UserVO> detail(@PathVariable Object id) {
        return ApiResult.success(userService.detail(id));
    }
}