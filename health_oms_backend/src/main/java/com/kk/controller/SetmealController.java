package com.kk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kk.common.MessageConst;
import com.kk.common.RedisConst;
import com.kk.entity.PageResult;
import com.kk.entity.QueryPageBean;
import com.kk.entity.Result;
import com.kk.pojo.Setmeal;
import com.kk.service.SetMealService;
import com.kk.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetMealService setMealService;
    @Autowired
    private  JedisPool jedisPool;
    @RequestMapping("/findPage")
    public Result findPageSetmeal(@RequestBody QueryPageBean queryPageBean){
        PageResult pageSetMeal = setMealService.getPageSetMeal(queryPageBean);
        Result result = new Result(true, MessageConst.GET_SETMEAL_LIST_SUCCESS, pageSetMeal);
        return new Result(true, MessageConst.GET_SETMEAL_LIST_SUCCESS,pageSetMeal);
    }

    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile){
        System.out.println("SetmealController.upload1111");
        String originalFilename = imgFile.getOriginalFilename();
        String upName = UUID.randomUUID().toString().replace("-","")+"_"+originalFilename;
        try {
            System.out.println(upName);
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),upName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jedisPool.getResource().sadd(RedisConst.SETMEAL_PIC_RESOURCES,upName);
        String fileUrl = QiniuUtils.qiniu_img_url_pre +  upName;
        return new Result(true,MessageConst.UPLOAD_SUCCESS,fileUrl);
    }
    @RequestMapping("/add")
    public Result addSetmeal(@RequestBody Setmeal setmeal , @RequestParam("checkgroupIds") List<Integer> checkgroupIds){
        try{
        System.out.println("SetmealController.addSetmeal");
        System.out.println(setmeal+"===="+checkgroupIds);
        setmeal.setImg(setmeal.getImg().replace(QiniuUtils.qiniu_img_url_pre,""));
        setMealService.addSetmeal(setmeal,checkgroupIds);
        return new Result(true, MessageConst.ADD_SETMEAL_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConst.ADD_CHECKGROUP_FAIL);
        }
    }
    @RequestMapping("/getSetmeal")
    public Result getSetmeal(Integer id){
        Map<String,Object> setmealArr = setMealService.getSetmealByid(id);
        Setmeal item = (Setmeal) setmealArr.get("item");
        item.setImg(QiniuUtils.qiniu_img_url_pre+item.getImg());
        return new Result(true,MessageConst.ACTION_SUCCESS,setmealArr);
    }

    @RequestMapping("/editSetmeal")
    public Result editSetmeal(@RequestBody Setmeal setmeal , @RequestParam("checkgroupIds")List<Integer> checkgroupIds) {

        try {
            setmeal.setImg(setmeal.getImg().replace(QiniuUtils.qiniu_img_url_pre,""));
            setMealService.setSetmeal(setmeal,checkgroupIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConst.ACTION_FAIL);
        }
        return new Result(true,MessageConst.ACTION_SUCCESS);
    }
    @RequestMapping("/delSetmeal")
    public Result delSetmeal(@RequestParam("id") Integer id){
        setMealService.delSetmealByid(id);
        return new Result(true,MessageConst.ACTION_SUCCESS);
    }
}
