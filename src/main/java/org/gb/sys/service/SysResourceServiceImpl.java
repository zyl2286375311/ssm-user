package org.gb.sys.service;


import org.gb.sys.dao.SysResourceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysResourceServiceImpl implements SysResourceService {
	
	@Autowired
	private SysResourceDao resourceDao;
	

}
