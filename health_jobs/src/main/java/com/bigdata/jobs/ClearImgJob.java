package com.bigdata.jobs;

import com.bigdata.constant.RedisConstant;
import com.bigdata.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @ClassName: ClearImgJob
 * @Description:自定义Job，实现定时清理垃圾图片
 * @Author:我自己
 * @Date: 2022/5/28  8:48
 * @Version 1.0
 */
public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){
        //根据Redis中保存的两个set集合进行差值计算，获得垃圾图片名称集合
        Set<String> set =
                jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES,
                        RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if(set != null){
            for (String picName : set) {
                //删除七牛云服务器上的图片
                QiniuUtils.deleteFileFromQiniu(picName);
                //从Redis集合中删除图片名称
                jedisPool.getResource().
                        srem(RedisConstant.SETMEAL_PIC_RESOURCES,picName);
            }
        }
    }
}
