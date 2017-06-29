package org.gb.sys.service;



import org.gb.sys.dao.SysRoleDao;
import org.gb.util.PageUtil;
import org.gb.vo.SysRole;
import org.gb.vo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;

	@Override
    public PageUtil<SysRole> selectRoleList(PageUtil<SysRole> rolePage) {
    	return sysRoleDao.selectRoleList(rolePage);
    }

	@Override
	public List<SysRole> getRoleTree() {




		List<SysRole> roleList = sysRoleDao.getRoleTree();

		Collections.sort(roleList);

		List<SysUser> userList = new ArrayList<>();
		Collections.sort(userList, new Comparator<SysUser>() {
			@Override
			public int compare(SysUser o1, SysUser o2) {
				return o1.getAge() - o2.getAge();
			}
		});
		return roleList;
	}
}
