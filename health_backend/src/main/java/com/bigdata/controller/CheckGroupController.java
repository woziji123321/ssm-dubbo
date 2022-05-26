package com.bigdata.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.bigdata.constant.MessageConstant;
import com.bigdata.entity.PageResult;
import com.bigdata.entity.QueryPageBean;
import com.bigdata.entity.Result;
import com.bigdata.pojo.CheckGroup;
import com.bigdata.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @ClassName: CheckGroupController
 * @Description：检查组管理
 * @Author:我自己
 * @Date: 2022/5/25  16:13
 * @Version 1.0
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    /**
     * 新增
     * @param checkGroup
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        try{
            checkGroupService.add(checkGroup,checkitemIds);
            //新增成功
            return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //新增失败
            return new Result(false,MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkGroupService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public Result findById(Integer id){
        CheckGroup checkGroup = checkGroupService.findById(id);
        if(checkGroup != null){
            Result result = new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS);
            result.setData(checkGroup);
            return result;
        }
        return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
    }

    /**
     * 根据检查组合id查询对应的所有检查项id
     * @param id
     * @return
     */
    @RequestMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(Integer id){
        try{
            List<Integer> checkitemIds =
                    checkGroupService.findCheckItemIdsByCheckGroupId(id);
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkitemIds);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    /**
     * 编辑
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        try {
            checkGroupService.edit(checkGroup,checkitemIds);
        }catch (Exception e){
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            checkGroupService.delete(id);
            return new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        }catch (RuntimeException e){
            return new Result(false,e.getMessage());
        }catch (Exception e){
            return new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }


    @RequestMapping("/findAll")
    public Result findAll(){
        List<CheckGroup> checkGroupList = checkGroupService.findAll();
        if (checkGroupList != null&&checkGroupList.size()>0){
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroupList);
        }
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
     }

}
