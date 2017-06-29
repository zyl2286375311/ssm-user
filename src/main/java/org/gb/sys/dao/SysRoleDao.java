package org.gb.sys.dao;

import org.gb.util.PageUtil;
import org.gb.vo.SysRole;

import java.util.List;



public interface SysRoleDao {

    PageUtil<SysRole> selectRoleList(PageUtil<SysRole> rolePage);


    List<SysRole> getRoleTree();
}
