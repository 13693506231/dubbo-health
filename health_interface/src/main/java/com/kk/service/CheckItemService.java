package com.kk.service;

import com.kk.entity.PageResult;
import com.kk.entity.QueryPageBean;
import com.kk.entity.Result;
import com.kk.pojo.CheckItem;

public interface CheckItemService {
    public PageResult getAllItem(QueryPageBean queryString);

    void addCheckItem(CheckItem checkItem);
}
