package com.bigdata.dao;

import com.bigdata.pojo.CheckGroup;
import com.bigdata.pojo.CheckItem;
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

    void setCheckGroupAndCheckItem(Map map);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<CheckGroup> selectByCondition(String queryString);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void deleteAssociation(Integer id);

    /**
     * 编辑
     * @param checkGroup
     */
    void edit(CheckGroup checkGroup);
}
