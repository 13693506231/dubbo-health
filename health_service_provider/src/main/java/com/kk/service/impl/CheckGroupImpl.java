package com.kk.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kk.dao.CheckGroupDao;
import com.kk.entity.PageResult;
import com.kk.entity.QueryPageBean;
import com.kk.pojo.CheckGroup;
import com.kk.service.CheckGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CheckGroupImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;
    @Override
    public PageResult getPageGroupItem(QueryPageBean queryPageBean) {
        log.debug("group querybean{}",queryPageBean);
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<CheckGroup> checkGroups = checkGroupDao.queryGroup(queryPageBean.getQueryString());
        PageResult pageResult = new PageResult();
        pageResult.setTotal(checkGroups.getTotal());
        pageResult.setRows(checkGroups.getResult());
        return pageResult;
    }

    @Override
    @Transactional
    public void addGroupAndCheckItems(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.addGroup(checkGroup);


        for (Integer checkitemId : checkitemIds) {
            checkGroupDao.addCheckItems(checkGroup.getId(),checkitemId);
        }
    }

    @Override
    @Transactional
    public void editGroupAndCheckItems(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.editGroup(checkGroup);
        checkGroupDao.delCheckedCheckItems(checkGroup.getId());
        for (Integer checkitemId : checkitemIds) {
            checkGroupDao.addCheckItems(checkGroup.getId(),checkitemId);
        }
    }

    @Override
    public HashMap getGroupOneById(Integer groupid) {
        CheckGroup checkGroup = checkGroupDao.getGroupOneById(groupid);
        List<Integer> checkItemids = checkGroupDao.getCheckedItem(groupid);
        HashMap dataMap =  new HashMap();
        dataMap.put("groupArr",checkGroup);
        dataMap.put("checkItemids",checkItemids);
        return dataMap;
    }

    @Override
    public void delGroupOneById(Integer groupid) {
        checkGroupDao.delGroupAll(groupid);
        checkGroupDao.delCheckedCheckItems(groupid);

    }

    @Override
    public List<CheckGroup> getAllGroup() {
        List<CheckGroup> checkGroups   =checkGroupDao.getAllCheckGroup();
        return checkGroups;
    }
}
