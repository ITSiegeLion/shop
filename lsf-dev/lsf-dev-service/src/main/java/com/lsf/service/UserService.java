package com.lsf.service;

import com.lsf.pojo.Users;
import com.lsf.pojo.bo.UsersBO;
import org.omg.CORBA.PUBLIC_MEMBER;

public interface UserService {

    /**
     * 判断用户名是否存在
     * @param username
     */
    public boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @param usersBO
     * @return
     */
    public Users createUser(UsersBO usersBO);

    /**
     * 检索用户名和密码是否匹配，用于登录
     */
    public Users queryUserForLogin(String username, String password);

}
