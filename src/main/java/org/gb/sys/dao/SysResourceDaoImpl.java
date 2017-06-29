package org.gb.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.gb.util.BaseDao;
import org.gb.vo.SysResource;
import org.springframework.stereotype.Repository;

@Repository
public class SysResourceDaoImpl extends BaseDao implements SysResourceDao {



    @Override
    public List<SysResource> getResourceTree() {
        SysResource resource = new SysResource();
        return getSqlMapClientTemplate().queryForList("resource.selectResourceTree",resource);
    }



    @Override
    public List<SysResource> selectResourceByUserId(String userId) {
        return this.getSqlMapClientTemplate().queryForList("resource.selectResourceByUserId", userId);
    }





}
