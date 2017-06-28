package org.gb.sys.controller;


import org.gb.sys.service.SysUserService;
import org.gb.util.PageUtil;
import org.gb.vo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("user")
public class SysUserController {
    @Autowired
    private SysUserService userService;

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