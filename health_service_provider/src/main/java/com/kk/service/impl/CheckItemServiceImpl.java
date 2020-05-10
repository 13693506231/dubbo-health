package com.kk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kk.common.MessageConst;
import com.kk.dao.CheckItemDao;
import com.kk.entity.PageResult;
import com.kk.entity.QueryPageBean;
import com.kk.entity.Result;
import com.kk.pojo.CheckItem;
import com.kk.service.CheckItemService;
import com.kk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;
    @Override
     public PageResult getAllItem(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<CheckItem> checkItemPage  = checkItemDao.selectByCondition(queryPageBean.getQueryString());
        PageResult pageResult = new PageResult();
        pageResult.setTotal(checkItemPage.getTotal());
        pageResult.setRows(checkItemPage.getResult());
        System.out.println("CheckItemServiceImpl.getAllItem");
        System.out.println(checkItemPage.getResult());
        return pageResult;
    }

    @Override
    public void addCheckItem(CheckItem checkItem) {
        System.out.println("addCheckItem");
        System.out.println(checkItem);
        checkItemDao.addItem(checkItem);
    }

    @Override
    public CheckItem getOneItem( Integer itemid) {
        System.out.println("CheckItemServiceImpl.getOneItem :"+itemid);
        CheckItem checkItem = checkItemDao.getOneItem(itemid);
        return checkItem;
    }

    @Override
    public void editCheckItem(CheckItem checkItem) {
        System.out.println("CheckItemServiceImpl.getOneItem :"+checkItem);
        checkItemDao.editItem(checkItem);
    }

    @Override
    public void getDelItem(Integer itemid) {
        System.out.println("CheckItemServiceImpl.getOneItem :"+itemid);
        checkItemDao.delItem(itemid);
    }

    @Override
    public List<CheckItem> getAll() {
        List<CheckItem> checkItems = checkItemDao.getAllItems();
        return checkItems;
    }
}
