package org.gb.sys.service;

import java.util.List;

import org.gb.sys.dao.SysResourceDao;
import org.gb.vo.SysResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysResourceServiceImpl implements SysResourceService {
	
	@Autowired
	private SysResourceDao resourceDao;



	@Override
	public List<SysResource> getResourceTree() {
		return resourceDao.getResourceTree();
	}

}
