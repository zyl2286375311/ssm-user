package org.gb.sys.dao;

import java.util.List;

import org.gb.sys.dao.SysRoleDao;

import org.gb.util.BaseDao;
import org.gb.vo.SysRole;
import org.springframework.stereotype.Repository;

@Repository
public class SysRoleDaoImpl extends BaseDao implements SysRoleDao {
    @Override
    public List<SysRole> getRoleTree() {
        return this.getSqlMapClientTemplate().queryForList("role.selectRoleList");
    }

}
