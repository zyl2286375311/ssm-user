package org.gb.sys.service;

import java.util.*;

import org.gb.sys.dao.SysResourceDao;
import org.gb.vo.SysResource;
import org.gb.vo.SysResourceType;
import org.gb.vo.SysUser;
import org.gb.vo.business.Tree;
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

	@Override
	public List<SysResource> selectResourceByUserId(String userId) {
		List<SysResource> resourceList =  resourceDao.selectResourceByUserId(userId);
		//去重
		resourceList = new ArrayList<>(new HashSet<>(resourceList));
		return resourceList;
	}



}
