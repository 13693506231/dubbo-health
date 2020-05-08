package com.kk.dao;

import com.github.pagehelper.Page;
import com.kk.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;

public interface CheckItemDao {
    Page<CheckItem> selectByCondition(@Param("queryString") String queryString);

    Boolean addItem(@Param("checkItem") CheckItem checkItem);
}
