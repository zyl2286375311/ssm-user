package org.gb.sys.service;

import org.gb.vo.SysRole;
import org.gb.vo.SysRoleResource;

import java.util.List;



public interface SysRoleResourceService {


    List<SysRoleResource> getResourceByRoleId(SysRole role);
}
