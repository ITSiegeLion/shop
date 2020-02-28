package com.lsf.controller;

import com.lsf.pojo.Users;
import com.lsf.pojo.bo.UsersBO;
import com.lsf.service.UserService;
import com.lsf.utils.CookieUtils;
import com.lsf.utils.JsonResult;
import com.lsf.utils.JsonUtils;
import com.lsf.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "注册登录", tags = "用于注册登录相关的接口")
@RestController
@RequestMapping("passport")
public class PassportController {
    @Autowired
    private UserService userService;

    /**
     * 验证用户名是否可用
     * @param username
     * @return
     */
    @ApiOperation(value = "用户名是否可用", notes = "验证用户名是否可用", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public JsonResult usernameIsExist(@RequestParam String username){
        if (StringUtils.isBlank(username)){
            return JsonResult.getErrorResult(400, "用户名不能为空");
        }
        if (userService.queryUsernameIsExist(username)){
            return JsonResult.getErrorResult("用户名已存在");
        }
        return JsonResult.getSuccessResult();
    }

    /**
     * 用户注册
     * @param usersBO
     * @return
     */
    @PostMapping("/regist")
    public JsonResult usersRegister(@RequestBody UsersBO usersBO,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
        String username = usersBO.getUsername();
        String password = usersBO.getPassword();
        String confirmPassword = usersBO.getConfirmPassword();

        //用户名、密码不能为空
        if (StringUtils.isBlank(username)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(confirmPassword)){
            return JsonResult.getErrorResult("用户名或密码不能为空");
        }

        //验证用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist){
            return JsonResult.getErrorResult("用户名已存在");
        }

        //验证密码长度
        if (password.length() < 6){
            return JsonResult.getErrorResult("密码长度不能少于6");
        }

        //验证两次输入的密码是否一致
        if (!password.equals(confirmPassword)){
            return JsonResult.getErrorResult("两次输入的密码不一致");
        }

        //注册
        Users users = userService.createUser(usersBO);
        users = setNullProperty(users);

        //保存用户信息到Cookie
        CookieUtils.setCookie(request, response,"user",
                JsonUtils.objectToJson(users), true);
        return JsonResult.getSuccessResult(users);
    }

    /**
     * 用户登录
     * @param usersBO
     * @return
     */
    @PostMapping("/login")
    public JsonResult userLogin(@RequestBody UsersBO usersBO,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
        String username = usersBO.getUsername();
        String password = usersBO.getPassword();

        //用户名、密码不能为空
        if (StringUtils.isBlank(username)
                || StringUtils.isBlank(password)){
            return JsonResult.getErrorResult("用户名或密码不能为空");
        }

        //登录
        Users users = null;
        try {
            users = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (users == null){
            return JsonResult.getErrorResult("用户名或密码错误");
        }
        users = setNullProperty(users);

        //保存用户信息到Cookie
        CookieUtils.setCookie(request, response,"user",
                JsonUtils.objectToJson(users), true);

        return JsonResult.getSuccessResult(users);
    }

    public Users setNullProperty(Users users){
        users.setPassword(null);
        users.setMobile(null);
        users.setRealname(null);
        users.setEmail(null);
        users.setCreatedTime(null);
        users.setUpdatedTime(null);
        users.setBirthday(null);
        return users;
    }

    @ApiOperation(value = "注销登录", notes = "用户退出登录", httpMethod = "POST")
    @PostMapping("logout")
    public JsonResult logout(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response){
        //清除用户Cookie
        CookieUtils.deleteCookie(request, response, "user");

        // TODO 用户退出登录，需要清空购物车
        // TODO 分布式会话中需要清除用户数据

        return JsonResult.getSuccessResult();
    }

}
