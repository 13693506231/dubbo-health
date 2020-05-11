package com.kk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
 import com.kk.dao.SetmealDao;
import com.kk.entity.PageResult;
import com.kk.entity.QueryPageBean;
import com.kk.pojo.Setmeal;
import com.kk.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    private SetmealDao setMealDao;
    @Override
    public PageResult getPageSetMeal(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Setmeal> setmeals = setMealDao.getPageSetMeal(queryPageBean.getQueryString());
        PageResult pageResult = new PageResult();
        pageResult.setRows(setmeals.getResult());
        pageResult.setTotal(setmeals.getTotal());
        return pageResult;
    }
}
