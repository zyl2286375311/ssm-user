package org.gb.sys.service;


import org.gb.util.PageUtil;
import org.gb.vo.SysRole;
import org.gb.vo.SysRole;

import java.util.List;

public interface SysRoleService {

    PageUtil<SysRole> selectRoleList(PageUtil<SysRole> rolePage);
    //修改角色
    List<SysRole> getRoleTree();
}
