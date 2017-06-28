package org.gb.sys.service;



import org.gb.sys.dao.SysRoleResourceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleResourceServiceImpl implements SysRoleResourceService{
	
	@Autowired
	private SysRoleResourceDao roleResourceDao;
	

}
