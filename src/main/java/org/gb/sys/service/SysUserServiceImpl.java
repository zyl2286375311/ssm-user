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

	/**
	 * 校验用户---账户名称是否已存在
	 * @param user
	 * @return
	 */
	@Override
	public SysUser checkSysUser(SysUser user) {
		return userDao.checkSysUser(user);
	}

	/**
	 * 注册用户
	 * @param sysUser
	 */
	@Override
	public void saveSysUser(SysUser sysUser) {
		//随机数id
		sysUser.setId(   UUID.randomUUID().toString() );
		//密码加密
		sysUser.setPwd(MD5Util.md5(sysUser.getPwd()));

		//创建时间
		sysUser.setCreatedatetime(new Date());
		//修改时间
		sysUser.setUpdatedatetime(new Date());

		userDao.saveSysUser(sysUser);
	}

	/**
	 * 查询用户列表
	 * @param userPage
	 * @return
	 */
	@Override
	public PageUtil<SysUser> selectUserList(PageUtil<SysUser> userPage) {
		return userDao.selectUserList(userPage);
	}
}
