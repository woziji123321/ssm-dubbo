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
     * 分页
     * @param queryString
     * @return
     */
    Page<CheckItem> selectByCondition(String queryString);

    /**
     * 检查索引
     * @param id
     * @return
     */
    long findCountByCheckItemId(Integer id);

    /**
     * 删除
     * @param id
     */
    void deleteById(Integer id);

    CheckItem findById(Integer id);

    /**
     * 修改
     * @param checkItem
     */
    void edit(CheckItem checkItem);

    /**
     * 查询
     * @return
     */
    List<CheckItem> findAll();
}
