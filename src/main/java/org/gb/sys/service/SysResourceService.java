package org.gb.sys.service;

import java.util.List;

import org.gb.vo.SysResource;
import org.gb.vo.business.Tree;

public interface SysResourceService {


    List<SysResource> getResourceTree();

    //根据用户id
    List<Tree> selectMainMenu(String userId);
}
