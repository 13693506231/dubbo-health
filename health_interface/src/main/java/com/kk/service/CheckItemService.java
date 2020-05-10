package com.kk.service;

import com.kk.entity.PageResult;
import com.kk.entity.QueryPageBean;
import com.kk.entity.Result;
import com.kk.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    public PageResult getAllItem(QueryPageBean queryString);

    void addCheckItem(CheckItem checkItem);

    CheckItem getOneItem(Integer itemid);

    void editCheckItem(CheckItem checkItem);

    void getDelItem(Integer itemid);

    List<CheckItem> getAll();

}
