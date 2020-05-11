package com.kk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kk.common.MessageConst;
import com.kk.entity.PageResult;
import com.kk.entity.QueryPageBean;
import com.kk.entity.Result;
import com.kk.service.SetMealService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetMealService setMealService;
    @RequestMapping("/findPage")
    public Result findPageSetmeal(@RequestBody QueryPageBean queryPageBean){
        PageResult pageSetMeal = setMealService.getPageSetMeal(queryPageBean);
        Result result = new Result(true, MessageConst.GET_SETMEAL_LIST_SUCCESS, pageSetMeal);
        return new Result(true, MessageConst.GET_SETMEAL_LIST_SUCCESS,pageSetMeal);
    }
}
