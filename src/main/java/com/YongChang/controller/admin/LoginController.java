package com.YongChang.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.YongChang.config.imlog.ImLog;
import com.YongChang.config.Result;
import com.YongChang.entity.RoleEntity;
import com.YongChang.entity.UserEntity;
import com.YongChang.service.RoleService;
import com.YongChang.service.UserService;
import com.YongChang.table.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping("login.html")
    public String login(){
        return "login";
    }


    @GetMapping("logout.htm")
    public String logout(HttpServletResponse response){
        Cookie cookie = new Cookie("login_key_auth","");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/login.html";
    }


    @PostMapping("loginData.html")
    @ResponseBody
    @ImLog(type = "登录",mark = "用户 {loginName} 登录")
    public Result loginData(String loginName, String password, HttpServletResponse response)throws Exception{
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq(UserTable.LOGIN_NAME,loginName);
        UserEntity userEntity = userService.selectOne(entityWrapper);
        if(userEntity==null){
            return Result.error("用户名或密码错误");
        }
        if(!password.equals(userEntity.getPassword())){
            return Result.error("用户名或密码错误");
        }
        RoleEntity roleEntity = roleService.selectById(userEntity.getRoleId());
        if(roleEntity==null || roleEntity.getStatus()==false ){
            return Result.error("用户无角色可用");
        }
        Cookie cookie = new Cookie("login_key_auth",userEntity.getId());
        cookie.setPath("/");
        cookie.setMaxAge(3600000);
        response.addCookie(cookie);
        return Result.success("登录成功");
    }
}
