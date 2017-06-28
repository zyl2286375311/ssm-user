package org.gb.sys.dao;

import java.util.List;


import org.gb.util.BaseDao;
import org.gb.vo.SysUser;
import org.gb.vo.SysUserRole;
import org.springframework.stereotype.Repository;

@Repository
public class SysUserRoleDaoImpl extends BaseDao implements SysUserRoleDao {

    @Override
    public void insertUserRoleList(List<SysUserRole> userRoleList) {
        this.getSqlMapClientTemplate().insert("userRole.insertUserRoleList", userRoleList);

    }

    @Override
    public int deleteUserRoleByUserId(SysUser user) {
        int x = this.getSqlMapClientTemplate().delete("userRole.deleteUserRoleByUserId", user);

        return x;
    }
}
