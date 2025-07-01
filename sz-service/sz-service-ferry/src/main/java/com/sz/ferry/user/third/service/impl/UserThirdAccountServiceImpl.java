package com.sz.ferry.user.third.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.sz.ferry.user.third.service.UserThirdAccountService;
import com.sz.ferry.user.third.pojo.po.UserThirdAccount;
import com.sz.ferry.user.third.mapper.UserThirdAccountMapper;

/**
 * <p>
 * 第三方用户表 服务实现类
 * </p>
 *
 * @author Rain
 * @since 2025-06-30
 */
@Service
@RequiredArgsConstructor
public class UserThirdAccountServiceImpl extends ServiceImpl<UserThirdAccountMapper, UserThirdAccount> implements UserThirdAccountService {
}