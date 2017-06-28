package org.gb.sys.service;

import org.gb.vo.SysRole;
import org.gb.vo.SysUser;
import org.gb.vo.SysUserRole;
import org.gb.vo.business.UserPwd;
import org.gb.sys.dao.SysUserDao;
import org.gb.util.MD5Util;
import org.gb.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserDao userDao;
	
	
	//注入 roleService 角色Service层
	@Autowired
	private SysRoleService roleService;
	
	@Autowired
	private SysUserRoleService userRoleService;





	@Override
	public void updateRoleOfUser(SysUser user) {

		//根据用户ID删除之前的角色信息--- 删除  用户角色关联关系表

		int x =	userRoleService.deleteUserRoleByUserId(user);


		//将用户重新授予角色信息 添加 到  用户角色关联关系表

		String[] split = user.getRoleIds().split(",");

		List<SysUserRole>  userRoleList = new ArrayList<>();
		SysUserRole ur = null;
		for (int i = 0; i < split.length; i++) {
			ur = new SysUserRole();
			ur.setSysUserId(user.getId());
			ur.setSysRoleId(split[i]);
			userRoleList.add(ur);
		}

		userRoleService.insertUserRoleList(userRoleList);

	}

	@Override
	public List<SysUserRole> selectUserRoleList(SysUser user) {
		return userDao.selectUserRoleList(user);
	}


	@Override
	public List<SysRole> getRoleTree() {

		List<SysRole> roleTreeList = roleService.getRoleTree();



		return roleTreeList;
	}

	@Override
	public PageUtil<SysUser> selectUserList(PageUtil<SysUser> userPage) {
		return userDao.selectUserList(userPage);
	}
}
