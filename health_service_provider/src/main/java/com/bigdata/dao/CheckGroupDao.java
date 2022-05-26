package com.bigdata.dao;

import com.bigdata.pojo.CheckGroup;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CheckGroupDao
 * @Description
 * @Author:我自己
 * @Date: 2022/5/25  16:14
 * @Version 1.0
 */
public interface CheckGroupDao {

    /**
     * 增加
     * @param checkGroup
     */
    void add(CheckGroup checkGroup);

    /**
     * 设置检查组和检查项的关联关系
     * @param map
     */
    void setCheckGroupAndCheckItem(Map map);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<CheckGroup> selectByCondition(String queryString);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    CheckGroup findById(Integer id);

    /**
     * 根据检查组id查询对应的所有检查项id
     * @param id
     * @return
     */
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    /**
     * 修改删除绑定
     * @param id
     */
    void deleteAssociation(Integer id);

    /**
     * 编辑
     * @param checkGroup
     */
    void edit(CheckGroup checkGroup);

    /**
     * 根据检查组id查询对应的所有检查项id
     * @param id
     * @return
     */
    long findCountByCheckGroupId(Integer id);

    /**
     * 删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 查询检查组的所有内容和套餐新增绑定
     * @return
     */
    List<CheckGroup> findAll();
}
