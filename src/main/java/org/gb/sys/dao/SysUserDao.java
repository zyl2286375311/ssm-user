package org.gb.sys.dao;

import java.util.List;

import org.gb.vo.SysUser;
import org.gb.vo.SysUserRole;
import org.gb.vo.business.UserPwd;
import org.gb.util.PageUtil;

public interface SysUserDao {

	//分页查询
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
    List<SysUserRole> selectUserRoleList(SysUser user);
}
