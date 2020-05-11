package com.kk.service;

import com.kk.entity.PageResult;
import com.kk.entity.QueryPageBean;


public interface SetMealService {
    public PageResult getPageSetMeal(QueryPageBean queryPageBean);
}
