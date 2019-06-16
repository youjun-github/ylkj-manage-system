package com.ylkj.mgt.web.args;

/**
 * 基础分页参数，默认第一页，每页20条记录
 * @author youjun
 */
public class PageArgs {

    private Integer pageNum = 1;

    private Integer pageSize = 20;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
