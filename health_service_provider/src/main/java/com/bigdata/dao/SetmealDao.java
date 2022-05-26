package com.bigdata.dao;

import com.bigdata.pojo.Setmeal;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SetMaleDao
 * @Description
 * @Author:我自己
 * @Date: 2022/5/26  9:47
 * @Version 1.0
 */
public interface SetmealDao {

    /**
     * 分页显示数据
     * @param queryString
     * @return
     */
    Page<Setmeal> selectByCondition(String queryString);

    /**
     * 新增数据
     * @param setmeal
     * @return
     */
    void add(Setmeal setmeal);

    /**
     * 设置套餐和检查组的关联关系
     * @param map
     */
    void setSetmealAndCheckGroup(Map<String, Integer> map);

    /**
     * 删除数据
     * @param id
     * @return
     */
    void deleteById(Integer id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Setmeal findById(Integer id);

    /**
     * 先删后增
     * @param id
     */
    void deleteAssociation(Integer id);

    /**
     * 根据套餐id查询对应的所有检查组id
     * @param id
     * @return
     */
    List<Integer> findCheckGroupIdsBySetmeal(Integer id);

    /**
     * 根据套餐id查询对应的所有检查组id
     * @param id
     * @return
     */
    long findCountBySetmealId(Integer id);

    /**
     * 编辑
     * @param setmeal
     * @return
     */
    void edit(Setmeal setmeal);
}
