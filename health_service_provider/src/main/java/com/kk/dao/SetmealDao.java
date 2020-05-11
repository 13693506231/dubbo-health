package com.kk.dao;

import com.github.pagehelper.Page;
import com.kk.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SetmealDao {

    Page<Setmeal> getPageSetMeal(@Param("queryString") String queryString);
}
