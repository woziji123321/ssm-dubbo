package com.bigdata.dao;

import com.bigdata.pojo.CheckItem;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @ClassName: CheckItemDao
 * @Description 持久层Dao接口
 * @Author:我自己
 * @Date: 2022/5/24  16:09
 * @Version 1.0
 */
public interface CheckItemDao {
    /**
     * 增加
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<CheckItem> selectByCondition(String queryString);

    /**
     * 根据检查项id查询中间关系表
     * @param id
     * @return
     */
    long findCountByCheckItemId(Integer id);

    /**
     * 删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    CheckItem findById(Integer id);

    /**
     * 修改
     * @param checkItem
     */
    void edit(CheckItem checkItem);

    /**
     * 查询检查项的所有内容和检查组新增绑定
     * @return
     */
    List<CheckItem> findAll();
}
