package com.ylkj.mgt.utils;

import com.github.pagehelper.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionUtil {

    public static <R, E> List<R> convert(List<E> source, Function<E, R> function) {
        return source.stream().map(function::apply).collect(Collectors.toCollection(ArrayList::new));
    }

    public static <R, E> Page<R> convert(Page<E> source, Function<E, R> function) {
        return source.stream().map(function::apply).collect(Collectors.toCollection(() -> page(source)));
    }

    private static <R, E> Page<R> page(Page<E> source) {
        Page<R> page = new Page<>(source.getPageNum(), source.getPageSize());
        page.setTotal(source.getTotal());
        return page;
    }

    /**
     * 拆分集合
     * 用于批处理，在批量操作数据库时候数据太大的时候最好还是先拆分
     *
     * @param <T>
     * @param resList 要拆分的集合
     * @param count   每个集合的元素个数
     * @return 返回拆分后的各个集合
     */
    public static <T> List<List<T>> split(List<T> resList, int count) {
        if (resList == null || count < 1) {
            return null;
        }
        List<List<T>> ret = new ArrayList<List<T>>();
        int size = resList.size();
        if (size <= count) { //数据量不足count指定的大小
            ret.add(resList);
        } else {
            int pre = size / count;
            int last = size % count;
            //前面pre个集合，每个大小都是count个元素
            for (int i = 0; i < pre; i++) {
                List<T> itemList = new ArrayList<>();
                for (int j = 0; j < count; j++) {
                    itemList.add(resList.get(i * count + j));
                }
                ret.add(itemList);
            }
            //last的进行处理
            if (last > 0) {
                List<T> itemList = new ArrayList<>();
                for (int i = 0; i < last; i++) {
                    itemList.add(resList.get(pre * count + i));
                }
                ret.add(itemList);
            }
        }
        return ret;
    }

    /**
     * @return : java.util.List<java.util.List<T>>
     * @description: 把list根据length，进行分割成多份list
     * @date: 2019-04-16 19:20:39
     * @param: paramsList
     * @param: length
     */
    public static <T> List<List<T>> splitInParams(List<T> paramsList, int length) {
        if (length < 1 || paramsList == null || paramsList.size() == 0)
            return null;
        int size = paramsList.size();
        List<List<T>> list = new ArrayList<>();
        int d = (int) Math.ceil(size / (length + 0.0));
        for (int i = 0; i < d; i++) {
            int fromIndex = length * i;
            int toIndex = Math.min(fromIndex + length, size);
            list.add(paramsList.subList(fromIndex, toIndex));
        }
        return list;
    }

}
