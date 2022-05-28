package com.bigdata.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bigdata.constant.MessageConstant;
import com.bigdata.constant.RedisConstant;
import com.bigdata.entity.PageResult;
import com.bigdata.entity.QueryPageBean;
import com.bigdata.entity.Result;
import com.bigdata.pojo.Setmeal;
import com.bigdata.service.SetmealService;
import com.bigdata.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.UUID;

/**
 * @ClassName: SetMaleController
 * @Description
 * @Author:我自己
 * @Date: 2022/5/26  9:44
 * @Version 1.0
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 新增数据
     * @param setmeal
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        try{
            setmealService.add(setmeal,checkgroupIds);
            //新增成功
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //新增失败
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try{
            setmealService.delete(id);
            return new Result(true,MessageConstant.DELETE_SETMEAL_SUCCESS);
        }catch (RuntimeException e){
            return new Result(false,e.getMessage());
        }catch (Exception e){
            return new Result(false, MessageConstant.DELETE_SETMEAL_FAIL);
        }
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public Result findById(Integer id){
        Setmeal setmeal = setmealService.findById(id);
        if(setmeal != null){
            Result result = new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS);
            result.setData(setmeal);
            return result;
        }
        return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
    }

    /**
     * 根据套餐id查询对应的所有检查组id
     * @param id
     * @return
     */
    @RequestMapping("/findCheckGroupIdsBySetmeal")
    public Result findCheckGroupIdsBySetmeal(Integer id) {
        try {
            List<Integer> checkgroupIds =
                    setmealService.findCheckGroupIdsBySetmeal(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkgroupIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    /**
     * 编辑
     * @param setmeal
     * @param checkgroupIds
     * @return
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        try{
            setmealService.edit(setmeal,checkgroupIds);
        }catch (Exception e){
            return new Result(false,MessageConstant.EDIT_SETMEAL_FAIL);
        }
        return new Result(true,MessageConstant.EDIT_SETMEAL_SUCCESS);
    }


    /**
     * 分页显示数据
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = setmealService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }

    /**
     * 图片上传
     * @param imgFile
     * @return
     */
    //图片上传
    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile){
        try{
            //获取原始文件名
            String originalFilename = imgFile.getOriginalFilename();
            int lastIndexOf = originalFilename.lastIndexOf(".");
            //获取文件后缀
            //String suffix = originalFilename.substring(lastIndexOf - 1);
            String suffix = originalFilename.substring(lastIndexOf);

            //使用UUID随机产生文件名称，防止同名文件覆盖
            String fileName = UUID.randomUUID().toString() + suffix;
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),fileName);
            //图片上传成功
            Result result = new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,fileName);

            //将上传图片名称存入Redis，基于Redis的Set集合存储
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);

            return result;
        }catch (Exception e){
            e.printStackTrace();
            //图片上传失败
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }
    }
}
