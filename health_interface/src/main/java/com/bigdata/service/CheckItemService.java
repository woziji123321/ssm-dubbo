package com.bigdata.service;

import com.bigdata.entity.PageResult;
import com.bigdata.pojo.CheckItem;

/**
 * @ClassName: CheckItemService
 * @Description 检查项服务接口
 * @Author:我自己
 * @Date: 2022/5/24  16:08
 * @Version 1.0
 */
public interface CheckItemService {
    /**
     * 增加
     * @param checkItem
     */
     void add(CheckItem checkItem);

    /**
     * 分页
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    /**
     * 删除
     * @param id
     */
    void delete(Integer id);

    CheckItem findById(Integer id);

    /**
     * 编辑
     * @param checkItem
     */
    void edit(CheckItem checkItem);
}
