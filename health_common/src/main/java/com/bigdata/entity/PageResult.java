package com.bigdata.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: PageResult
 * @Description 分页结果封装对象
 * @Author:我自己
 * @Date: 2022/5/24  12:08
 * @Version 1.0
 */
public class PageResult implements Serializable {
    private Long total;//总记录数
    private List rows;//当前页结果
    public PageResult(Long total, List rows) {
        super();
        this.total = total;
        this.rows = rows;
    }
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    public List getRows() {
        return rows;
    }
    public void setRows(List rows) {
        this.rows = rows;
    }
}
