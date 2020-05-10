package com.kk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kk.common.MessageConst;
import com.kk.entity.PageResult;
import com.kk.entity.QueryPageBean;
import com.kk.entity.Result;
import com.kk.pojo.CheckGroup;
import com.kk.service.CheckGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/checkgroup")
@Slf4j
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;
    @RequestMapping("/getPage")
    public Result getPageGroup(@RequestBody QueryPageBean queryPageBean){
         log.debug("getpagegroup:{}",queryPageBean);
         PageResult  checkGroupData = checkGroupService.getPageGroupItem(queryPageBean);
        return new Result(true, MessageConst.ACTION_SUCCESS,checkGroupData);
    }
    @RequestMapping("/editGroup")
    public Result editGroup(Integer[] checkitemIds, @RequestBody CheckGroup checkGroup){
        log.debug("checkitemIds {} checkgroup {}",checkitemIds,checkGroup);
        try {
            checkGroupService.editGroupAndCheckItems(checkGroup,checkitemIds);
        } catch (Exception e) {
            return new Result(false,MessageConst.ACTION_FAIL,e.toString());
        }
        return new Result(true,MessageConst.ACTION_SUCCESS);
    }
    @RequestMapping("/addGroup")
    public Result addGroup(Integer[] checkitemIds, @RequestBody CheckGroup checkGroup){
        log.debug("checkitemIds {} checkgroup {}",checkitemIds,checkGroup);
        try {
            checkGroupService.addGroupAndCheckItems(checkGroup,checkitemIds);
        }catch (Exception e){
            return new Result(false,MessageConst.ACTION_FAIL,e.toString());
        }
        return new Result(true,MessageConst.ACTION_SUCCESS);
    }
    @RequestMapping("/getGroupAndCheckedItem")
    public Result getGroupAndCheckedItem(Integer groupid){
        HashMap groupItem = checkGroupService.getGroupOneById(groupid);
        return new Result(true,MessageConst.ACTION_SUCCESS,groupItem);
    }
    @RequestMapping("/delGroupItems")
    public Result delGroupItems(Integer groupid){
        try {
            checkGroupService.delGroupOneById(groupid);
        } catch (Exception e) {
            return new Result(true,MessageConst.DELETE_CHECKGROUP_FAIL,e.toString());
        }
        return new Result(true,MessageConst.DELETE_CHECKGROUP_SUCCESS);
    }
}
