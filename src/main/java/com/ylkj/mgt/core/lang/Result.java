package com.ylkj.mgt.core.lang;

import com.github.pagehelper.Page;

/**
 * 统一的返回结果类型，结果必须包含code跟msg
 *
 * @param <T> 返回的数据
 * @author youjun
 */
public class Result<T> implements HttpCode {

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 返回的结果编码
     */
    private String code;

    /**
     * 返回的提示信息
     */
    private String msg;

    private Integer pageNum;//当前页数

    private Integer pageSize;

    private Long total;//分页总记录数

    public Result() {

    }

    /**
     * 失败结果
     *
     * @param code
     * @return
     */
    public static <T> Result<T> error(String code, String message) {
        return new Result<T>(code, message, null);
    }

    /**
     * 初始化
     *
     * @param code
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> init(String code, String message, T data) {
        return new Result<T>(code, message, data);
    }

    /**
     * 成功结果
     *
     * @param data
     * @return
     */
    public static <T> Result<T> ok(T data) {
        return new Result<T>(SUCCESS_CODE, "操作成功", data);
    }

    public Result(String code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
        if (data instanceof Page) {
            Page<?> p;
            this.pageNum = (p = (Page<?>) data).getPageNum();
            this.pageSize = p.getPageSize();
            this.total = p.getTotal();
        }
    }

    public Result(T data) {
        super();
        if (data instanceof Result) {
            Result<?> r;
            this.code = (r = (Result<?>) data).getCode();
            this.msg = r.getMsg();
            this.data = (T) r.getData();
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
