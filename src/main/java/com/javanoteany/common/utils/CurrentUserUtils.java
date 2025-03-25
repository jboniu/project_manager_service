package com.javanoteany.common.utils;

import com.javanoteany.user.dto.LoginUserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;

public class CurrentUserUtils {

    /**
     * 获取当前用户
     * @return
     */
    public static LoginUserDto getCurrentUser(){
        LoginUserDto loginUserDto = (LoginUserDto) SecurityUtils.getSubject().getPrincipal();
        if(loginUserDto == null){
            throw new UnauthenticatedException("请先登录用户!");
        }
        return loginUserDto;
    }

    /**
     * 登出
     */
    public static void loginOut(){
        SecurityUtils.getSubject().logout();
    }
}
