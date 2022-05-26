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

    Page<Setmeal> selectByCondition(String queryString);

    void add(Setmeal setmeal);

    void setSetmealAndCheckGroup(Map<String, Integer> map);

    

    void deleteById(Integer id);

    Setmeal findById(Integer id);

    

    void deleteAssociation(Integer id);

    void edit(Setmeal setmeal);

    List<Integer> findCheckGroupIdsBySetmeal(Integer id);

    long findCountBySetmealId(Integer id);
}
