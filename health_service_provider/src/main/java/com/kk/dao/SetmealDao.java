package com.kk.dao;

import com.github.pagehelper.Page;
import com.kk.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SetmealDao {

    Page<Setmeal> getPageSetMeal(@Param("queryString") String queryString);

    void addSetmeal(Setmeal setmeal);

    void delCheckGroup(@Param("id") Integer id);


    void addCheckGroup(@Param("id") Integer id,@Param("checkgroupId")  Integer checkgroupId);

    Setmeal getSetmealByid(@Param("id") Integer id);

    List<Integer> getSetmealCheckIds(@Param("id") Integer id);

    void editSetmeal(Setmeal setmeal);

    void delSetmealByid(@Param("id") Integer id);
}
