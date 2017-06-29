package org.gb.sys.service;

import java.util.List;

import org.gb.vo.SysResource;

public interface SysResourceService {


    List<SysResource> getResourceTree();

    List<SysResource> selectResourceByUserId(String userId);
}
