package com.bigdata.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bigdata.constant.MessageConstant;
import com.bigdata.entity.PageResult;
import com.bigdata.entity.QueryPageBean;
import com.bigdata.entity.Result;
import com.bigdata.pojo.Setmeal;
import com.bigdata.service.SetmealService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
