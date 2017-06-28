package org.gb.sys.dao;

import org.gb.vo.SysUser;
import org.gb.vo.SysUserRole;

import java.util.List;



public interface SysUserRoleDao {


    int deleteUserRoleByUserId(SysUser user);

    void insertUserRoleList(List<SysUserRole> userRoleList);
}
