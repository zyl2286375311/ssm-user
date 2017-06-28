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
	public PageUtil<SysUser> selectUserList(PageUtil<SysUser> userPage) {
		return userDao.selectUserList(userPage);
	}
}
