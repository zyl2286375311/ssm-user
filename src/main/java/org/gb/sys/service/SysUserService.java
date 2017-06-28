package org.gb.sys.service;

import org.gb.vo.SysRole;
import org.gb.vo.SysUser;
import org.gb.vo.SysUserRole;
import org.gb.vo.business.UserPwd;
import org.gb.util.PageUtil;

import java.util.List;

public interface SysUserService {


	PageUtil<SysUser> selectUserList(PageUtil<SysUser> userPage);

    /**
     * 用户注册
     * @param sysUser
     */
    void saveSysUser(SysUser sysUser);

    /**
     * 校验用户---账户名称是否已存在
     * @param user
     * @return
     */
    SysUser checkSysUser(SysUser user);
}
