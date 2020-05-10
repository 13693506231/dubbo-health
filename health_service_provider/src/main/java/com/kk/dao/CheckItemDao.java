package com.kk.dao;

import com.github.pagehelper.Page;
import com.kk.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckItemDao {
    Page<CheckItem> selectByCondition( @Param("queryString") String queryString);

//    void addItem(@Param("checkItem") CheckItem checkItem);
    void addItem( CheckItem checkItem);

    CheckItem getOneItem(@Param("itemid") Integer itemid);

    void editItem( CheckItem checkItem);

    void delItem(@Param("itemid") Integer itemid);

    List<CheckItem> getAllItems();

}
