package com.YongChang.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.YongChang.config.imlog.ImLog;
import com.YongChang.config.Result;
import com.YongChang.entity.RoleEntity;
import com.YongChang.entity.UserEntity;
import com.YongChang.mapper.UserDao;
import com.YongChang.service.RoleService;
import com.YongChang.service.UserService;
import com.YongChang.table.RoleTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;


    @RequestMapping("list.htm")
    public String list(Model model)throws Exception{
        List<UserEntity> list = userDao.list();
        model.addAttribute("list",list);
        return "user/list";
    }


    @RequestMapping("save.htm")
    public String save(Model model,String id)throws Exception{
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq(RoleTable.STATUS,true);
        List<RoleEntity> entities = roleService.selectList(entityWrapper);
        model.addAttribute("roles",entities);

        UserEntity userEntity = new  UserEntity();
        userEntity.setSex(true);
        userEntity.setStatus(true);
        if(!StringUtils.isEmpty(id)){
            userEntity = userService.selectById(id);
        }
        model.addAttribute("userEntity",userEntity);
        return "user/save";
    }


    @RequestMapping("saveData.htm")
    @ResponseBody
    @ImLog(type = "用户",mark = "保存用户 {name}")
    public Result save(Model model, UserEntity userEntity)throws Exception{
        if(StringUtils.isEmpty(userEntity.getId())){
            userEntity.setId(IdWorker.get32UUID());
            userEntity.setTime(new Date());
            userService.insert(userEntity);
        }else{
            userService.updateById(userEntity);
        }
        return Result.success("保存成功");
    }


    @RequestMapping("del.htm")
    @ResponseBody
    @ImLog(type = "用户",mark = "删除用户 {id}")
    public Result del(Model model, String id )throws Exception{
        userService.deleteById(id);
        return Result.success("保存成功");
    }



}
