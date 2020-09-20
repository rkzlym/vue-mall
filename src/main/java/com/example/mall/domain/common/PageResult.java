package com.example.mall.domain.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * view object：视图对象，专门给页面所用
 * @param <T>
 */
@Data
public class PageResult<T> implements Serializable {
    private Long code;
    private String msg;
    private Long total;
    private Long totalPage;
    private List<T> items;

    public PageResult(){}

    public PageResult(Long code, String msg, Long total, Long totalPage, List<T> items){
        this.code = code;
        this.msg = msg;
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }

    public static <T> PageResult<T> success(String msg, Long total, Long totalPage, List<T> items){
        return new PageResult<>(ResultCode.SUCCESS.getCode(), msg, total, totalPage, items);
    }

    public static <T> PageResult<T> failed(String msg, Long total, Long totalPage, List<T> items){
        return new PageResult<>(ResultCode.FAILED.getCode(), msg, total, totalPage, items);
    }
}
