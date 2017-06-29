package org.gb.sys.service;




import org.gb.sys.dao.SysUserRoleDao;
import org.gb.vo.SysUser;
import org.gb.vo.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
	
	@Autowired
	private SysUserRoleDao userRoleDao;

	@Override
	public int deleteUserRoleByUserId(SysUser user) {

		int x = userRoleDao.deleteUserRoleByUserId(user);

		return x ;
	}

	@Override
	public void insertUserRoleList(List<SysUserRole> userRoleList) {
		userRoleDao.insertUserRoleList(userRoleList);
	}

}
