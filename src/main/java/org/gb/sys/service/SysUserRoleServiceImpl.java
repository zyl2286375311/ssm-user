package org.gb.sys.service;




import org.gb.sys.dao.SysUserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
	
	@Autowired
	private SysUserRoleDao userRoleDao;


	
	
}
