package com.YongChang.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.YongChang.config.imlog.ImLog;
import com.YongChang.config.IdWorkerUtil;
import com.YongChang.config.Result;
import com.YongChang.entity.MenuEntity;
import com.YongChang.entity.RoleEntity;
import com.YongChang.entity.RoleMenuEntity;
import com.YongChang.service.MenuService;
import com.YongChang.service.RoleMenuService;
import com.YongChang.service.RoleService;
import com.YongChang.table.RoleMenuTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuService roleMenuService;


    @GetMapping("list.htm")
    public String list(Model model)throws Exception{
        List<RoleEntity> list = roleService.selectList(new EntityWrapper<>());
        model.addAttribute("list",list);
        return "role/list";
    }

    @PostMapping("save.htm")
    @ResponseBody
    @ImLog(type = "角色",mark = "保存角色 {id} {name} {status}")
    public Result save(String id,String name,Boolean status)throws Exception{
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(name);
        roleEntity.setStatus(status);
        if(StringUtils.isEmpty(id)){
            roleEntity.setId(IdWorker.get32UUID());
            roleEntity.setTime(new Date());
            roleService.insert(roleEntity);
        }else{
            roleEntity.setId(id);
            roleService.updateById(roleEntity);
        }
        return Result.success("保存成功");
    }



    @PostMapping("del.htm")
    @ResponseBody
    @ImLog(type = "角色",mark = "删除角色 {id}")
    public Result del(String id)throws Exception{
        roleService.deleteById(id);
        return Result.success("保存成功");
    }


    @RequestMapping("auth.htm")
    public String auth(String id,Model model)throws Exception{
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq(RoleMenuTable.ROLE_ID,id);
        List<RoleMenuEntity> entity = roleMenuService.selectList(entityWrapper);

        List<MenuEntity> menuEntities = menuService.selectList(null);
        if(entity!=null && !entity.isEmpty()){
            Set<String> menuids = new HashSet<>();
            for (RoleMenuEntity roleMenuEntity : entity) {
                menuids.add(roleMenuEntity.getMenuId());
            }
            for (MenuEntity menuEntity : menuEntities) {
                if(menuids.contains(menuEntity.getId())){
                    menuEntity.setAuth(true);
                }
            }
        }
        model.addAttribute("menuEntities",menuEntities);
        model.addAttribute("roleId",id);
        return "role/auth";
    }


    @RequestMapping("authData.htm")
    @ResponseBody
    @ImLog(type = "角色",mark = "角色分配权限 {id} {ids}")
    public Result data(String[] ids,String roleId,Model model)throws Exception{
        List<String> list = new ArrayList<>();
        for (String id : ids) {
            list.add(id);
        }
        List<MenuEntity> menuEntities = menuService.selectBatchIds(list);
        //删除原有权限
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq(RoleMenuTable.ROLE_ID,roleId);
        roleMenuService.delete(entityWrapper);
        Set<String> set = new HashSet<>();
        //循环添加新的权限
        for (MenuEntity menuEntity : menuEntities) {
            set.add(menuEntity.getPId());
        }
        for (String s : set) {
            RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
            roleMenuEntity.setId(IdWorkerUtil.getId());
            roleMenuEntity.setMenuId(s);
            roleMenuEntity.setRoleId(roleId);
            roleMenuService.insert(roleMenuEntity);
        }
        for (String s : list) {
            RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
            roleMenuEntity.setId(IdWorkerUtil.getId());
            roleMenuEntity.setMenuId(s);
            roleMenuEntity.setRoleId(roleId);
            roleMenuService.insert(roleMenuEntity);
        }
        return Result.success("权限保存成功");
    }

}
