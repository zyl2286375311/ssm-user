package org.gb.sys.dao;

import java.util.List;

import org.gb.vo.SysUser;
import org.gb.vo.SysUserRole;
import org.gb.vo.business.UserPwd;
import org.gb.util.PageUtil;

public interface SysUserDao {

	//分页查询
	PageUtil<SysUser> selectUserList(PageUtil<SysUser> userPage);


    List<SysUserRole> selectUserRoleList(SysUser user);
}
