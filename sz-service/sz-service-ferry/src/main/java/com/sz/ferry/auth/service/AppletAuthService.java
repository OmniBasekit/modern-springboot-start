package com.sz.ferry.auth.service;

import com.sz.security.pojo.LoginVO;

public interface AppletAuthService {


    public LoginVO login(String code);

}
