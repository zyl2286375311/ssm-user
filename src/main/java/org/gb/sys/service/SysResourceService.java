package org.gb.sys.service;

import java.util.List;

import org.gb.vo.SysResource;
import org.gb.vo.SysResourceType;
import org.gb.vo.business.Tree;

public interface SysResourceService {


    List<SysResource> getResourceTree();

    //根据用户id
    List<Tree> selectMainMenu(String userId);

    //权限拦截
    List<SysResource> selectResourceByUserId(String userId);

    List<SysResourceType> selectResourceTypeList();

    void addResrouce(SysResource resource);

    List<SysResource> selectResourceList(String id);
}
