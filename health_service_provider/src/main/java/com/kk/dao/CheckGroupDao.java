package com.kk.dao;

import com.github.pagehelper.Page;
import com.kk.entity.QueryPageBean;
import com.kk.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckGroupDao {


    Page<CheckGroup> queryGroup(@Param("queryString") String queryString);

    void addGroup(CheckGroup checkGroup);

    void addCheckItems(@Param("checkgroup_id") Integer id, @Param("checkitem_id") Integer checkitemId);

    void editGroup(CheckGroup checkGroup);

    void delCheckedCheckItems(@Param("checkgroup_id") Integer id);

    CheckGroup getGroupOneById(@Param("id") Integer groupid);

    List<Integer> getCheckedItem(@Param("groupid") Integer groupid);

    void delGroupAll(@Param("groupid") Integer groupid);

    List<CheckGroup> getAllCheckGroup();
}
