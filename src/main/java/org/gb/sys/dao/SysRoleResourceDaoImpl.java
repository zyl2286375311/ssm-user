package org.gb.sys.dao;

import java.util.List;


import org.gb.util.BaseDao;
import org.gb.vo.SysRole;
import org.gb.vo.SysRoleResource;
import org.springframework.stereotype.Repository;

@Repository
public class SysRoleResourceDaoImpl extends BaseDao implements SysRoleResourceDao {

    @Override
    public List<SysRoleResource> getResourceByRoleId(SysRole role) {
        return this.getSqlMapClientTemplate().queryForList("roleResource.getResourceByRoleId", role);
    }

    @Override
    public void insertRoleResourceList(List<SysRoleResource> roleResourceList) {
        this.getSqlMapClientTemplate().insert("roleResource.insertRoleResourceList", roleResourceList);
    }

    @Override
    public int deleteRoleResourceByRoleId(String sysRoleId) {
        return this.getSqlMapClientTemplate().delete("roleResource.deleteRoleResourceByRoleId", sysRoleId);
    }



}
