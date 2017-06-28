package org.gb.sys.service;


import org.gb.vo.SysUser;
import org.gb.vo.SysUserRole;

import java.util.List;

public interface SysUserRoleService {


    int deleteUserRoleByUserId(SysUser user);

    void insertUserRoleList(List<SysUserRole> userRoleList);
}
