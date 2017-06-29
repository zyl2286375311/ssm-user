package org.gb.sys.service;


import org.gb.util.PageUtil;
import org.gb.vo.SysRole;

public interface SysRoleService {

    PageUtil<SysRole> selectRoleList(PageUtil<SysRole> rolePage);
}
