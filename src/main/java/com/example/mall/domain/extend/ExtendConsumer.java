package com.example.mall.domain.extend;

/**
 * 拓展Consumer接口: 提供两个参数 无返回值
 * @param <T1>
 * @param <T2>
 */
@FunctionalInterface
public interface ExtendConsumer<T1, T2> {
    void accept(T1 t1, T2 t2);
}
