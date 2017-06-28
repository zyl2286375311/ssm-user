package org.gb.sys.service;

import org.gb.vo.SysRole;
import org.gb.vo.SysUser;
import org.gb.vo.SysUserRole;
import org.gb.vo.business.UserPwd;
import org.gb.util.PageUtil;

import java.util.List;

public interface SysUserService {


	PageUtil<SysUser> selectUserList(PageUtil<SysUser> userPage);

	//获取角色tree
	List<SysRole> getRoleTree();

	//根据用户ID查询角色信息
	List<SysUserRole> selectUserRoleList(SysUser user);

	////用户授予角色，分配角色
	//根据用户ID删除之前的角色信息
	//将用户重新授予角色信息 添加 到  用户角色关联关系表
	void updateRoleOfUser(SysUser user);
}
