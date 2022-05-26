package com.bigdata.service;

import com.bigdata.entity.PageResult;
import com.bigdata.pojo.CheckGroup;

import java.util.List;

/**
 * @ClassName: CheckGroupService
 * @Description
 * @Author:我自己
 * @Date: 2022/5/25  16:14
 * @Version 1.0
 */
public interface CheckGroupService {
    /**
     * 新增
     * @param checkGroup
     * @param checkitemIds
     */
    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 分页查询数据
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

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
     * 修改
     * @param checkGroup
     * @param checkitemIds
     */
    void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 查询检查组的所有内容和套餐新增绑定
     * @return
     */
    List<CheckGroup> findAll();
}
