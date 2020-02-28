package com.lsf.service.impl;

import com.lsf.enums.Sex;
import com.lsf.mapper.UsersMapper;
import com.lsf.pojo.Users;
import com.lsf.pojo.bo.UsersBO;
import com.lsf.service.UserService;
import com.lsf.utils.DateUtils;
import com.lsf.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    /**
     * 用户默认头像
     */
    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    /**
     * 判断用户是否存在
     * @param username
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Example usersExample = new Example(Users.class);
        Example.Criteria usersExampleCriteria = usersExample.createCriteria();
        usersExampleCriteria.andEqualTo("username", username);
        Users users = usersMapper.selectOneByExample(usersExample);
        return users != null;
    }

    /**
     * 用户注册
     * @param usersBO
     * @return
     */
    @Override
    public Users createUser(UsersBO usersBO) {
        //TODO 生成用户ID 后续要换成分布式ID生成方案
        String userId = UUID.randomUUID().toString();

        //创建用户
        Users users = new Users();
        users.setId(userId);
        users.setUsername(usersBO.getUsername());
        try {
            users.setPassword(MD5Utils.getMD5Str(usersBO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //默认昵称为用户名
        users.setNickname(usersBO.getUsername());
        //默认头像
        users.setFace(USER_FACE);
        //默认生日
        users.setBirthday(DateUtils.stringToDate("1900-01-01"));
        //默认性别保密
        users.setSex(Sex.secrecy.sex);

        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());

        usersMapper.insert(users);

        return users    ;
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public Users queryUserForLogin(String username, String password) {
        Example usersExample = new Example(Users.class);
        Example.Criteria usersExampleCriteria = usersExample.createCriteria();
        usersExampleCriteria.andEqualTo("username", username);
        usersExampleCriteria.andEqualTo("password", password);
        Users users = usersMapper.selectOneByExample(usersExample);
        return users;
    }
}
