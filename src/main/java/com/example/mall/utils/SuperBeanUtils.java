package com.example.mall.utils;

import com.example.mall.domain.extend.ExtendConsumer;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SuperBeanUtils extends BeanUtils {

    /**
     * 复制List<T>到新的List<T>
     * List<T> newList = SuperBeanUtils.copyListProperties(oldList, T::new);
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }

    /**
     * 复制List<T>到新的List<T> 有回调
     * List<T> newList = SuperBeanUtils.copyListProperties(oldList, T::new, (T1, T2) -> {业务逻辑});
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, ExtendConsumer<S, T> consumer) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            if (consumer != null) {
                consumer.accept(source, t);
            }
            list.add(t);
        }
        return list;
    }
}
