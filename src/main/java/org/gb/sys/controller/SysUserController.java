package org.gb.sys.controller;


import org.gb.sys.service.SysUserService;
import org.gb.util.PageUtil;
import org.gb.util.ReturnJson;
import org.gb.vo.SysRole;
import org.gb.vo.SysUser;
import org.gb.vo.SysUserRole;
import org.gb.vo.business.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("user")
public class SysUserController {
    @Autowired
    private SysUserService userService;


    //用户授予角色，分配角色
    @RequestMapping(value="grantRoleOfUser",method= RequestMethod.POST)
    @ResponseBody
    public ReturnJson grantRoleOfUser(SysUser user){
        userService.updateRoleOfUser(user);
        return new ReturnJson(true, "修改角色成功",null);
    }

    /**
     * 根据用户id查询拥有的角色信息
     * @param user
     */
    @RequestMapping(value="getUserRoleInfo",method=RequestMethod.POST)
    @ResponseBody
    public List<SysUserRole> getUserRoleInfo(SysUser user){

        List<SysUserRole> userRoleList =  userService.selectUserRoleList(user);


        return userRoleList;
    }


    /**
     * 查询角色tree
     * @return
     */
    @RequestMapping(value="getRoleTree",method=RequestMethod.POST)
    @ResponseBody
    public List<Tree> getRoleTree(){
        List<SysRole> roleList =  userService.getRoleTree();

        List<Tree> treeList = new ArrayList<>();

        Tree t = null;

        for (SysRole role : roleList) {
            t = new Tree();
            t.setId(role.getId());
            t.setText(role.getName());
            treeList.add(t);
        }


        return treeList;
    }

    /**
     * @param page  当前页
     * @param rows  每页条数
     * @return 分页user信息
     */
    @RequestMapping("selectUserList")
    @ResponseBody
    public Map<String,Object> selectUserList(Integer page,Integer rows,PageUtil<SysUser> userPage){
        userPage.setCpage(page);
        userPage.setPageSize(rows);
        userPage = userService.selectUserList(userPage);
        Map<String,Object> map = new HashMap<>();
        map.put("total", userPage.getTotalCount());
        map.put("rows", userPage.getList());
        return map;
    }
    //跳页面
    @RequestMapping("toUserList")
    public String toUserList(){
        return "user/userList";
    }




}