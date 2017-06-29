package org.gb.sys.service;

import java.util.*;

import org.gb.sys.dao.SysResourceDao;
import org.gb.vo.SysResource;
import org.gb.vo.SysResourceType;
import org.gb.vo.SysUser;
import org.gb.vo.business.Tree;
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




	@Override
	public List<Tree> selectMainMenu(String userId) {

		List<SysResource> resourceList =  resourceDao.selectMainMenu(userId);




		//利用set的无序不重复的特性用来去重---重复数据---java基本数据类型可以，引用数据类型不可以。原因：JVM虚拟机的堆栈！！！

		//java基本数据类型---默认已经重写 hashCode() 与 equals()方法

		//java引用数据类型-- 利用set的无序不重复的特性用来去重--必须重写hashCode() 与 equals()方法
		//--重写之后两个对象是否相同通过比较属性值来决定


		// 去重方式一
		resourceList = new ArrayList<SysResource>(new HashSet<SysResource>(resourceList));

		//去重方式二
		//List<SysResource>   list = removeDuplicate(resourceList);

		//排序
		Collections.sort(resourceList, new Comparator<SysResource>() {
			@Override
			public int compare(SysResource o1, SysResource o2) {
				if (o1.getSeq() == null) {
					o1.setSeq(1000);
				}
				if (o2.getSeq() == null) {
					o2.setSeq(1000);
				}
				return o1.getSeq().compareTo(o2.getSeq());
			}
		});

		List<Tree> treeList = new ArrayList<Tree>();
		Tree fjtree = null;
		Tree zjtree = null;
		List<Tree> childTree = null;
		HashMap<String, String> url = null;
		for (SysResource r : resourceList) {
			fjtree = new Tree();
			if (null == r.getPid() ) {
				childTree = new ArrayList<>();
				fjtree.setId(r.getId());
				fjtree.setPid(r.getPid());
				fjtree.setText(r.getName());
				fjtree.setState("closed");
				fjtree.setIconCls(r.getIconCls());
				for (SysResource zir : resourceList) {
					if (null != zir.getPid() && zir.getPid().equals(r.getId())) {
						zjtree = new Tree();
						zjtree.setId(zir.getIconCls());
						zjtree.setPid(zir.getPid());
						zjtree.setText(zir.getName());
						zjtree.setIconCls(zir.getIconCls());
						url = new  HashMap<>();
						url.put("url", zir.getUrl());
						zjtree.setAttributes(url);
						//
						childTree.add(zjtree);
					}
				}
				fjtree.setChildren(childTree);
			}else{
				continue;
			}
			treeList.add(fjtree);
		}


		return treeList;
	}
}
