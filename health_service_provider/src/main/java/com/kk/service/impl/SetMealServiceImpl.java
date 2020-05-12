package com.kk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kk.common.RedisConst;
import com.kk.dao.SetmealDao;
import com.kk.entity.PageResult;
import com.kk.entity.QueryPageBean;
import com.kk.pojo.Setmeal;
import com.kk.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class SetMealServiceImpl implements SetMealService {
     @Autowired
    private SetmealDao setMealDao;
     @Autowired
     private JedisPool jedisPool;
    @Override
    public PageResult getPageSetMeal(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Setmeal> setmeals = setMealDao.getPageSetMeal(queryPageBean.getQueryString());
        PageResult pageResult = new PageResult();
        pageResult.setRows(setmeals.getResult());
        pageResult.setTotal(setmeals.getTotal());
        return pageResult;
    }

    @Override
    @Transactional
    /**
     * Description :  增加setmeal,和groupid
     * @param：
     * @return String：
     * @Author: ghc
     * @Create Date: 2008-07-02
     */
    public void addSetmeal(Setmeal setmeal, List<Integer> checkgroupIds) {
        setMealDao.addSetmeal(setmeal);
        for (int checkgroupId : checkgroupIds) {
            setMealDao.addCheckGroup(setmeal.getId(),checkgroupId);
        }
        jedisPool.getResource().sadd(RedisConst.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
    }

    @Override
    public Map<String,Object> getSetmealByid(Integer id) {
        Setmeal setmeal = setMealDao.getSetmealByid(id);
        List<Integer> checkids = setMealDao.getSetmealCheckIds(id);
        Map map = new  HashMap<String,Object>();
        map.put("item",setmeal);
        map.put("checkgroupIds",checkids);
        return map;
    }

    @Override
    /**
     * Description :    编辑
     * @param：
     * @return String：
     * @Author: ghc
     * @Create Date: 2008-07-02
     */
    public void setSetmeal(Setmeal setmeal, List<Integer> checkgroupIds) {
        try {
            Setmeal setmealArr = setMealDao.getSetmealByid(setmeal.getId());
            jedisPool.getResource().srem(RedisConst.SETMEAL_PIC_DB_RESOURCES,setmealArr.getImg());//删除以前的db redis
            setMealDao.editSetmeal(setmeal);
            jedisPool.getResource().sadd(RedisConst.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());

            setMealDao.delCheckGroup(setmeal.getId());
            for (int checkgroupId : checkgroupIds) {
                setMealDao.addCheckGroup(setmeal.getId(),checkgroupId);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void delSetmealByid(Integer id) {
        System.out.println(id+"delSetmealByid###");
        setMealDao.delSetmealByid(id);
        setMealDao.delCheckGroup(id);
    }
}
