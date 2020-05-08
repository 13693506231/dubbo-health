package com.kk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kk.common.MessageConst;
import com.kk.entity.PageResult;
import com.kk.entity.QueryPageBean;
import com.kk.entity.Result;
import com.kk.pojo.CheckItem;
import com.kk.service.CheckItemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.Op;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/checkItem")
@RestController
@Slf4j
public class CheckItemController {
    @Reference
    private CheckItemService checkItemService;
    @RequestMapping("/getItemsPage")
    public Result getCheckItemByString(@RequestBody QueryPageBean queryBean){
        System.out.println("CheckItemController.getCheckItemByString");
        System.out.println(queryBean);
        PageResult allItem = checkItemService.getAllItem(queryBean);
        if(allItem.getTotal()>0){
            return new Result(true, MessageConst.QUERY_CHECKITEM_SUCCESS,allItem);
        }else{
            return new Result(false, MessageConst.QUERY_CHECKITEM_FAIL);
        }
    }
    @RequestMapping("/addItem")
    public Result addCheckItem(@RequestBody CheckItem checkItem){
        log.debug("add111{}",checkItem);
/*        try {
            if(checkItemService != null){
                checkItemService.addCheckItem(checkItem);
                return new Result(true,MessageConst.ADD_CHECKITEM_SUCCESS);
            }else{
                log.debug("checkItemService is null222 ,未找到服务对象");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return new Result(false,MessageConst.ADD_CHECKITEM_FAIL);
    }

}
