package org.gb.sys.service;

import java.util.ArrayList;
import java.util.List;


import org.gb.sys.dao.SysRoleResourceDao;
import org.gb.vo.SysRole;
import org.gb.vo.SysRoleResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleResourceServiceImpl implements SysRoleResourceService{
	
	@Autowired
	private SysRoleResourceDao roleResourceDao;



	@Override
	public List<SysRoleResource> getResourceByRoleId(SysRole role) {
		return roleResourceDao.getResourceByRoleId(role);
	}

}
