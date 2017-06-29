package org.gb.sys.service;



import org.gb.sys.dao.SysRoleDao;
import org.gb.util.PageUtil;
import org.gb.vo.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;


	@Override
	public PageUtil<SysRole> selectRoleList(PageUtil<SysRole> rolePage) {
		return null;
	}

}
