package org.gb.sys.controller;

import java.util.HashMap;
import java.util.Map;


import org.gb.sys.service.SysRoleService;
import org.gb.util.PageUtil;
import org.gb.vo.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("role")
public class SysRoleController {
	
	@Autowired
	private SysRoleService roleService;


	/**
	 * @param page  当前页
	 * @param rows  每页条数
	 * @return 分页Role信息
	 */
	@RequestMapping(value="selectRoleList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> selectRoleList(Integer page,Integer rows,PageUtil<SysRole> rolePage){

/*		PageUtil<Role> RolePage = new PageUtil<>();*/
		rolePage.setCpage(page);
		rolePage.setPageSize(rows);
		//查询条件---map
		System.out.println(rolePage.getWhereMap());
		//分页查询
		rolePage = roleService.selectRoleList(rolePage);

		//封装到map 设置easyui的datagrid组件识别的json字符串
		Map<String,Object> map = new HashMap<>();
		map.put("total", rolePage.getTotalCount());
		map.put("rows", rolePage.getList());

		return map;
	}

	@RequestMapping("toRoleList")
	public String toRoleList(){
		return "role/roleList";
	}
	
	
	

	

}
