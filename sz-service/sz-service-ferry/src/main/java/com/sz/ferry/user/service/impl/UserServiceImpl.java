package com.sz.ferry.user.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.sz.ferry.user.service.UserService;
import com.sz.ferry.user.pojo.po.User;
import com.sz.ferry.user.mapper.UserMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.query.QueryChain;
import com.sz.core.common.enums.CommonResponseEnum;
import com.sz.core.util.PageUtils;
import com.sz.core.util.BeanCopyUtils;
import com.sz.core.util.Utils;
import com.sz.core.common.entity.PageResult;
import com.sz.core.common.entity.SelectIdsDTO;
import java.io.Serializable;
import java.util.List;
import com.sz.ferry.user.pojo.dto.UserCreateDTO;
import com.sz.ferry.user.pojo.dto.UserUpdateDTO;
import com.sz.ferry.user.pojo.dto.UserListDTO;
import com.sz.ferry.user.pojo.vo.UserVO;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Rain
 * @since 2025-06-30
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public void create(UserCreateDTO dto){
        User user = BeanCopyUtils.copy(dto, User.class);
        save(user);
    }

    @Override
    public void update(UserUpdateDTO dto){
        User user = BeanCopyUtils.copy(dto, User.class);
        QueryWrapper wrapper;
        // id有效性校验
        wrapper = QueryWrapper.create()
            .eq(User::getId, dto.getId());
        CommonResponseEnum.INVALID_ID.assertTrue(count(wrapper) <= 0);

        saveOrUpdate(user);
    }

    @Override
    public PageResult<UserVO> page(UserListDTO dto){
        Page<UserVO> page = pageAs(PageUtils.getPage(dto), buildQueryWrapper(dto), UserVO.class);
        return PageUtils.getPageResult(page);
    }

    @Override
    public List<UserVO> list(UserListDTO dto){
        return listAs(buildQueryWrapper(dto), UserVO.class);
    }

    @Override
    public void remove(SelectIdsDTO dto){
        CommonResponseEnum.INVALID_ID.assertTrue(dto.getIds().isEmpty());
        removeByIds(dto.getIds());
    }

    @Override
    public UserVO detail(Object id){
        User user = getById((Serializable) id);
        CommonResponseEnum.INVALID_ID.assertNull(user);
        return BeanCopyUtils.copy(user, UserVO.class);
    }

    private static QueryWrapper buildQueryWrapper(UserListDTO dto) {
        QueryWrapper wrapper = QueryWrapper.create().from(User.class);
        if (Utils.isNotNull(dto.getName())) {
            wrapper.like(User::getName, dto.getName());
        }
        if (Utils.isNotNull(dto.getMobile())) {
            wrapper.eq(User::getMobile, dto.getMobile());
        }
        if (Utils.isNotNull(dto.getAvatar())) {
            wrapper.eq(User::getAvatar, dto.getAvatar());
        }
        if (Utils.isNotNull(dto.getGender())) {
            wrapper.eq(User::getGender, dto.getGender());
        }
        return wrapper;
    }
}