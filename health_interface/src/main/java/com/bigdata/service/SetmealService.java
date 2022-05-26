package com.bigdata.service;

import com.bigdata.entity.PageResult;
import com.bigdata.pojo.Setmeal;

import java.util.List;

/**
 * @ClassName: SetMaleService
 * @Description
 * @Author:我自己
 * @Date: 2022/5/26  9:45
 * @Version 1.0
 */
public interface SetmealService {

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    /**
     * 新增
     * @param setmeal
     * @param checkgroupIds
     */
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Setmeal findById(Integer id);

    /**
     * 根据套餐id查询对应的所有检查组id
     * @param id
     * @return
     */
    List<Integer> findCheckGroupIdsBySetmeal(Integer id);

    /**
     * 编辑
     * @param setmeal
     * @param checkgroupIds
     * @return
     */
    void edit(Setmeal setmeal, Integer[] checkgroupIds);
}
