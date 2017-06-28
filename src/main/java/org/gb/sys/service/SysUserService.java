package org.gb.sys.service;

import org.gb.vo.SysRole;
import org.gb.vo.SysUser;
import org.gb.vo.SysUserRole;
import org.gb.vo.business.UserPwd;
import org.gb.util.PageUtil;

import java.util.List;

public interface SysUserService {


	PageUtil<SysUser> selectUserList(PageUtil<SysUser> userPage);
}
