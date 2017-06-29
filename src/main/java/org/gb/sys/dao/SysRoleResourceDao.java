package org.gb.sys.dao;

import org.gb.vo.SysRole;
import org.gb.vo.SysRoleResource;

import java.util.List;



public interface SysRoleResourceDao {


    List<SysRoleResource> getResourceByRoleId(SysRole role);

    void insertRoleResourceList(List<SysRoleResource> roleResourceList);

    int deleteRoleResourceByRoleId(String sysRoleId);
}
