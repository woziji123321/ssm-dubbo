package com.bigdata.dao;

import com.bigdata.pojo.CheckItem;
import com.github.pagehelper.Page;

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
    public void add(CheckItem checkItem);

    /**
     * 分页
     * @param queryString
     * @return
     */
    public Page<CheckItem> selectByCondition(String queryString);

    /**
     * 检查索引
     * @param id
     * @return
     */
    public long findCountByCheckItemId(Integer id);

    /**
     * 删除
     * @param id
     */
    public void deleteById(Integer id);

    public CheckItem findById(Integer id);

    public void edit(CheckItem checkItem);
}
