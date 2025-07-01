package com.sz.ferry.user.service;

import com.mybatisflex.core.service.IService;
import com.sz.ferry.user.pojo.po.User;
import com.sz.core.common.entity.SelectIdsDTO;
import com.sz.core.common.entity.PageResult;
import java.util.List;
import com.sz.ferry.user.pojo.dto.UserCreateDTO;
import com.sz.ferry.user.pojo.dto.UserUpdateDTO;
import com.sz.ferry.user.pojo.dto.UserListDTO;
import com.sz.ferry.user.pojo.vo.UserVO;

/**
 * <p>
 * 用户表 Service
 * </p>
 *
 * @author Rain
 * @since 2025-06-30
 */
public interface UserService extends IService<User> {

    void create(UserCreateDTO dto);

    void update(UserUpdateDTO dto);

    PageResult<UserVO> page(UserListDTO dto);

    List<UserVO> list(UserListDTO dto);

    void remove(SelectIdsDTO dto);

    UserVO detail(Object id);
}