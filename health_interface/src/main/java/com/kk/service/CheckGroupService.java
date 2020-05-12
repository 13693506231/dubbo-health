package com.kk.service;

import com.kk.entity.PageResult;
import com.kk.entity.QueryPageBean;
import com.kk.pojo.CheckGroup;

import java.util.HashMap;
import java.util.List;

public interface CheckGroupService {
    PageResult getPageGroupItem(QueryPageBean queryPageBean);

    void addGroupAndCheckItems(CheckGroup checkGroup, Integer[] checkitemIds);

    void editGroupAndCheckItems(CheckGroup checkGroup, Integer[] checkitemIds);

    HashMap getGroupOneById(Integer groupid);

    void delGroupOneById(Integer groupid);

    List<CheckGroup> getAllGroup();
}
