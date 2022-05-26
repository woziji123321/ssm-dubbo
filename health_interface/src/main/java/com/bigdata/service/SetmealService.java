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

    void add(Setmeal setmeal, Integer[] checkgroupIds);

    void delete(Integer id);

    Setmeal findById(Integer id);

    List<Integer> findCheckGroupIdsBySetmeal(Integer id);

    void edit(Setmeal setmeal, Integer[] checkgroupIds);
}
