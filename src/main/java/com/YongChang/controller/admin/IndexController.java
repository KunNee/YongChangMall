package com.YongChang.controller.admin;

import com.YongChang.config.Contants;
import com.YongChang.entity.MenuEntity;
import com.YongChang.entity.RoleEntity;
import com.YongChang.entity.UserEntity;
import com.YongChang.mapper.MenuDao;
import com.YongChang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuDao menuDao;



    @RequestMapping("index.htm")
    public String index(Model model){
        UserEntity userEntity = Contants.get();
        model.addAttribute("userEntity",userEntity);

        RoleEntity roleEntity = roleService.selectById(userEntity.getRoleId());
        model.addAttribute("roleEntity",roleEntity);

        List<MenuEntity> menuEntities = menuDao.selectByRoleId(roleEntity.getId());
        List<MenuEntity> one = new ArrayList<>();
        for (MenuEntity menuEntity : menuEntities) {
            if(menuEntity.getType()==1){
                one.add(menuEntity);
            }
        }
        for (MenuEntity menuEntity : one) {
            List<MenuEntity> two = menuEntity.getChilds();
            if(two==null){
                two = new ArrayList<>();
            }
            for (MenuEntity entity : menuEntities) {
                if(entity.getType() == 2 && menuEntity.getId().equals(entity.getPId())){
                    two.add(entity);
                }
            }
            menuEntity.setChilds(two);
        }
        model.addAttribute("menus",one);
        return "index";
    }
}
