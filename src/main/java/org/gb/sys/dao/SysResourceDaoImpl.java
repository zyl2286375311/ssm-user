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






    @Override
    public List<SysResource> selectMainMenu(String userId) {
//		resourcetypeId 为 "0"

        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);
        map.put("resourceTypeId", "0");

        return getSqlMapClientTemplate().queryForList("resource.selectMainMenu",map);
    }
}
