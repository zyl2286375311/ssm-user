package org.gb.sys.service;


import org.gb.util.PageUtil;
import org.gb.vo.SysRole;

import java.util.List;

public interface SysRoleService {

    //修改角色
    List<SysRole> getRoleTree();


    PageUtil<SysRole> selectRoleList(PageUtil<SysRole> rolePage);



}
