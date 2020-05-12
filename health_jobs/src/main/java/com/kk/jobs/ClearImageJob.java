package com.kk.jobs;

import com.kk.common.RedisConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.Set;

@Service
public class ClearImageJob {
    @Autowired
    private JedisPool jedisPool;

    public void soutStr(){
        System.out.println("每隔十秒执行一次");
/*        System.out.println("clearImageJob......jedisPool: getResource:"+jedisPool.getResource());*/
    }
    public void clearImageJob(){
        try {
            Jedis resource = jedisPool.getResource();

            Set<String> setDiss = resource.sdiff(RedisConst.SETMEAL_PIC_RESOURCES,RedisConst.SETMEAL_PIC_DB_RESOURCES);
            System.out.println(setDiss);
            System.out.println(resource.smembers(RedisConst.SETMEAL_PIC_DB_RESOURCES));
            System.out.println(resource.smembers(RedisConst.SETMEAL_PIC_RESOURCES));
            if(setDiss.isEmpty()){
                System.out.println("no thing need clear");
                return;
            }
            Iterator<String> iterator = setDiss.iterator();
            while (iterator.hasNext()){
                String nextStr = iterator.next();
                resource.srem(RedisConst.SETMEAL_PIC_RESOURCES,nextStr);
                QiniuUtils.deleteFileFromQiniu(nextStr);
                System.out.println("删除str:"+nextStr+"成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ClearImageJob.clearImageJob");


    }
}
