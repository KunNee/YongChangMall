package com.YongChang.controller.mobile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.YongChang.config.Contants;
import com.YongChang.config.IdWorkerUtil;
import com.YongChang.config.Result;
import com.YongChang.model.CustomerEntity;
import com.YongChang.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("mobileCustomer")
public class MobileCustomerController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping("jump.html")
    public String my(Model model,String url)throws Exception{
        model.addAttribute("url",url);
        return "mobile/jump";
    }


    @RequestMapping("infoData.do")
    @ResponseBody
    public Result infoData(Model model)throws Exception{
        CustomerEntity customerEntity = Contants.getCustomer();
        return Result.success(customerEntity);
    }



    @RequestMapping("info.do")
    public String info(Model model)throws Exception{
        CustomerEntity customerEntity = Contants.getCustomer();
        model.addAttribute("customer",customerEntity);
        return "mobile/my";
    }



    @RequestMapping("myUpdate.do")
    public String my_update(Model model)throws Exception{
        CustomerEntity customerEntity = Contants.getCustomer();
        model.addAttribute("entity",customerEntity);
        return "mobile/my_update";
    }


    @RequestMapping("myUpdateData.do")
    @ResponseBody
    public Result myUpdateData( CustomerEntity customerEntity)throws Exception{
        customerEntity.setId(Contants.getCustomer().getId());
        customerService.updateById(customerEntity);
        return Result.success("成功");
    }



    @RequestMapping("login.html")
    public String login(Model model) throws Exception{
        return "mobile/login";
    }



    @RequestMapping("logOut.do")
    @ResponseBody
    public Result logOut( HttpServletResponse response)throws Exception{
        Cookie cookie = new Cookie("login_key_auth_customer","-1");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return Result.success("退出成功");
    }

    @RequestMapping("loginData.html")
    @ResponseBody
    public Result loginData(String phone,String password, HttpServletResponse response)throws Exception{
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("phone",phone);
        CustomerEntity customerEntity = customerService.selectOne(entityWrapper);
        if(customerEntity==null){
            return Result.error("用户不存在");
        }
        if(!customerEntity.getPassword().equals(password)){
            return Result.error("密码错误");
        }
        Cookie cookie = new Cookie("login_key_auth_customer",customerEntity.getId());
        cookie.setPath("/");
        cookie.setMaxAge(3600000);
        response.addCookie(cookie);
        return Result.success(customerEntity.getId(),"登陆成功");
    }

    @RequestMapping("reg.html")
    public String reg(Model model) throws Exception{
        return "mobile/reg";
    }



    @RequestMapping("regData.html")
    @ResponseBody
    public Result regData( CustomerEntity customerEntity, HttpServletResponse response)throws Exception{
        customerEntity.setId(IdWorkerUtil.getId());
        customerEntity.setHeader("/img/a5.jpg");
        customerService.insert(customerEntity);
        Cookie cookie = new Cookie("login_key_auth_customer",customerEntity.getId());
        cookie.setPath("/");
        cookie.setMaxAge(3600000);
        response.addCookie(cookie);
        return Result.success(customerEntity.getId(),"登陆成功");
    }




}
