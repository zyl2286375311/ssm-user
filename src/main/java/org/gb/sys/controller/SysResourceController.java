package org.gb.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.gb.sys.service.SysResourceService;
import org.gb.sys.service.SysRoleResourceService;
import org.gb.vo.SysResource;
import org.gb.vo.SysRole;
import org.gb.vo.SysRoleResource;
import org.gb.vo.business.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("resource")
public class SysResourceController {
	
	@Autowired
	private SysResourceService resourceService;
	
	@Autowired
	private SysRoleResourceService roleResourceService;

	/**
	 * 根据角色id查询拥有的权限资源list
	 * @param role
	 * @return
	 */
	@RequestMapping(value="getResourceByRoleId",method=RequestMethod.POST)
	@ResponseBody
	public List<SysRoleResource> getResourceByRoleId(SysRole role){

		List<SysRoleResource> roleResourceList = roleResourceService.getResourceByRoleId(role);

		return roleResourceList;
	}



	/**
	 * 查询所有的权限资源tree
	 * @return
	 */
	@RequestMapping(value="getResourceTree",method=RequestMethod.POST)
	@ResponseBody
	public  ArrayList<Tree>  getResourceTree(){
		List<SysResource> resourceList =  resourceService.getResourceTree();
		ArrayList<Tree> treeList = new ArrayList<>();
		//一级节点
		Tree yiji = null;
		//子节点list
		ArrayList<Tree> childList = null;
		//子级节点
		Tree child = null;
		//节点的自定义属性 如 url等。。。
		HashMap<String, String> nodeAttr = null;
		for (int i = 0; i < resourceList.size(); i++) {
			//pid== null 说明一级节点
			if (resourceList.get(i).getPid() == null) {
				yiji = new Tree();
				yiji.setId(resourceList.get(i).getId());
				yiji.setText(resourceList.get(i).getName());
				yiji.setIconCls(resourceList.get(i).getIconCls());
				yiji.setState("open");
				childList = new ArrayList<>();
				//循环遍历子节点
				for (int j = 0; j < resourceList.size(); j++) {
					//当前循环的节点的父级id 等于  上层循环节点的id
					if (resourceList.get(j).getPid() != null &&
							resourceList.get(i).getId().equals(resourceList.get(j).getPid()) ) {
						//实例化子节点
						child = new Tree();
						//节点属性赋值
						child.setId(resourceList.get(j).getId());
						child.setText(resourceList.get(j).getName());
						child.setIconCls(resourceList.get(j).getIconCls());
						child.setPid(resourceList.get(j).getPid());
						child.setState("open");
						//实例化 自定义节点属性map
						nodeAttr = new HashMap<>();
						nodeAttr.put("url", resourceList.get(j).getUrl());
						child.setAttributes(nodeAttr);
						//子节点list 添加 child节点
						childList.add(child);
						selectChildList(resourceList, child);
					}
				}
				yiji.setChildren(childList);
				treeList.add(yiji);
			}
		}
		return treeList;
	}


	private void selectChildList(List<SysResource> resourceList,Tree prarentNode){
		//子节点list
		ArrayList<Tree> childList = new ArrayList<>();
		//子级节点
		Tree child = null;
		//节点的自定义属性 如 url等。。。
		HashMap<String, String> nodeAttr = null;
		//循环遍历子节点
		for (int j = 0; j < resourceList.size(); j++) {
			//当前循环的节点的父级id 等于  上层循环节点的id
			if (resourceList.get(j).getPid() != null &&
					prarentNode.getId().equals(resourceList.get(j).getPid()) ) {
				//实例化子节点
				child = new Tree();
				//节点属性赋值
				child.setId(resourceList.get(j).getId());
				child.setText(resourceList.get(j).getName());
				child.setIconCls(resourceList.get(j).getIconCls());
				child.setPid(resourceList.get(j).getPid());
				child.setState("open");
				//实例化 自定义节点属性map
				nodeAttr = new HashMap<>();
				nodeAttr.put("url", resourceList.get(j).getUrl());
				child.setAttributes(nodeAttr);
				//子节点list 添加 child节点
				childList.add(child);

				//递归调用查找子节点 n层
				selectChildList(resourceList, child);
			}
		}
		prarentNode.setChildren(childList);

	}

}
