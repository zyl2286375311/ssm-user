package org.gb.sys.dao;

import org.gb.vo.SysResource;

import java.util.List;



public interface SysResourceDao {


    List<SysResource> getResourceTree();

    List<SysResource> selectResourceByUserId(String userId);
}
