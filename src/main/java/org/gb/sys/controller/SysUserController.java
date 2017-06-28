package org.gb.sys.controller;


import org.gb.sys.service.SysUserService;
import org.gb.util.ConfigUtil;
import org.gb.util.MD5Util;
import org.gb.util.PageUtil;
import org.gb.util.ReturnJson;
import org.gb.vo.SysUser;
import org.gb.vo.business.SessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    /**
     * 校验用户登录
     * @param user
     * @param request
     * @param flag
     * @return
     */
    @RequestMapping(value="checkSysUserLogin",method=RequestMethod.POST)
    @ResponseBody
    public ReturnJson checkSysUserLogin(SysUser user,HttpServletRequest request,Integer flag){
        ReturnJson rj = new ReturnJson();
        //获取session中的验证码
        String code = (String) request.getSession().getAttribute("imageCode");
        if (null !=flag && 1 !=flag) {
            //校验验证码是否正确
            if (null != user && !"".equals(user.getImgcode().trim()) && !"".equals(code)) {
                //验证码正确---不区分大小写
                if (user.getImgcode().trim().toUpperCase().equals(code.toUpperCase())) {
                    userFeng(user,rj,request);
                }
            }
        } else {
            userFeng(user,rj,request);
        }
        return rj;
    }

    /**
     * 用户登陆
     * @param user
     * @param rj
     * @param request
     */
    public void userFeng(SysUser user,ReturnJson rj,HttpServletRequest request){
        //u 查询数据库的信息
        //user 用户登录时表单
        SysUser u = userService.checkSysUser(user);
        if (null != u) {//用户名正确
            if (u.getPwd().equals(MD5Util.md5(user.getPwd()))) {//密码正确
                rj.setSuccess(true);
                rj.setMsg("登录成功");
                //登录成功之后将用户信息存放到session中
                SessionInfo sessionInfo = new SessionInfo();
                //用户密码置 空
                u.setPwd(null);
                sessionInfo.setUser(u);
                request.getSession().setAttribute(ConfigUtil.getSessionInfoName(),sessionInfo);
            } else {
                rj.setSuccess(false);
                rj.setMsg("密码错误");
            }
        } else {//用户名错误
            rj.setSuccess(false);
            rj.setMsg("用户名错误");
        }
    }

    /**
     * 校验用户---账户名称是否已存在
     * @param user
     * @return
     */
    @RequestMapping(value="checkSysUser",method=RequestMethod.POST)
    @ResponseBody
    public ReturnJson checkSysUser(SysUser user){
        ReturnJson rj = new ReturnJson();

        SysUser u = userService.checkSysUser(user);

        if (null != u) {
            rj.setSuccess(false);//已经被注册
        }else{
            rj.setSuccess(true);
        }
        return rj;
    }

    /**
     * 注册用户
     * @param sysUser
     * @param request
     * @return
     */
    @RequestMapping(value = "registerSysUser", method = RequestMethod.POST)
    @ResponseBody
    public ReturnJson registerSysUser(SysUser sysUser, HttpServletRequest request) {
        ReturnJson rj = new ReturnJson();
        //获取session中的验证码
        String code = (String) request.getSession().getAttribute("imageCode");
        //校验验证码是否正确
        if (null != sysUser && !"".equals(sysUser.getImgcode().trim()) && !"".equals(code)) {
            //验证码正确---不区分大小写
            if (sysUser.getImgcode().trim().toUpperCase().equals(code.toUpperCase())) {
                userService.saveSysUser(sysUser);
                // 主键id ,布尔类型 ,影响数据库的条数
                rj.setSuccess(true);
                rj.setMsg("注册成功");
            }else{
                rj.setSuccess(false);
                rj.setMsg("验证码错误");
            }
        }
        return rj;
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