package com.kk.service;

import com.kk.entity.PageResult;
import com.kk.entity.QueryPageBean;
import com.kk.pojo.Setmeal;

import java.util.List;
import java.util.Map;


public interface SetMealService {
    public PageResult getPageSetMeal(QueryPageBean queryPageBean);

    void addSetmeal(Setmeal setmeal, List<Integer> checkgroupIds);

    Map<String,Object> getSetmealByid(Integer id);

    void setSetmeal(Setmeal setmeal, List<Integer> checkgroupIds);

    void delSetmealByid(Integer id);
}
