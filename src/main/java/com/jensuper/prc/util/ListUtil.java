package com.jensuper.prc.util;

import com.bigone.ros.common.constants.Constants;
import com.bigone.ros.common.enums.ResponseEnum;
import com.bigone.ros.common.exception.BusinessException;
import com.bigone.ros.util.functionInterface.ListHandlerFunction;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 集合工具类
 *
 * @author lenny@bigonelab.com
 * @since 2021/8/3
 **/
public class ListUtil {

    /**
     * 字符串转集合 返回List<Integer>
     *
     * @author wangjie
     * @date 2022-08-15 13:45:29
     * @param str
     * @param symbol
     * @return
     */
    public static List<Integer> stringToListInteger(String str, String symbol) {
        if (StringUtils.isBlank(str)) {
            return new ArrayList<>();
        }
        return Arrays.stream(str.split(symbol)).map(s -> Integer.valueOf(s)).collect(Collectors.toList());
    }

    /**
     * 字符串转集合
     *
     * @param str    待转换字符串
     * @param symbol 分隔符
     * @author lenny@bigonelab.com
     * @retuen java.util.List<java.lang.String>
     * @since 2021/8/3
     */
    public static List<String> stringToList(String str, String symbol) {
        if (StringUtils.isBlank(str)) {
            return new ArrayList<>();
        }
        return Lists.newArrayList(Splitter.on(symbol)
                .trimResults()
                .omitEmptyStrings()
                .split(str));
    }

    /**
     * 集合转字符串
     *
     * @param data   带转换数据
     * @param symbol 分隔符
     * @author lenny@bigonelab.com
     * @retuen java.lang.String
     * @since 2021/8/3
     */
    public static String listToString(List data, String symbol) {
        if (CollectionUtils.isEmpty(data)) {
            return null;
        }
        return Joiner.on(symbol).join(data);
    }

    public static String listToString(List data) {
        return listToString(data, Constants.COMMA);
    }

    /**
     * 将Object类型的list转为具体类型的list
     *
     * @param obj
     * @param clazz
     * @return
     */
    public static <T> List<T> obj2List(Object obj, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        if (obj instanceof List<?>) {
            ((List<?>) obj).forEach(o -> list.add(clazz.cast(o)));
        }
        return list;
    }

    /**
     * 求两个集合的交集
     *
     * @param listA  被对比集合
     * @param listB  对比集合
     * @param mapper 对比的条件
     * @return
     */
    public static <T, U, R> List<T> getData(List<T> listA, List<U> listB, ListHandlerFunction<? super T, U, ? extends R> mapper) {
        if (com.baomidou.mybatisplus.core.toolkit.CollectionUtils.isEmpty(listA)) {
            return new ArrayList<>();
        }
        List<T> list = listA.stream().filter(one -> {
            List<? extends R> result = mapper.callBack(one, listB);
            if (result.indexOf(true) > -1)
                return true;
            return false;
        }).collect(Collectors.toList());
        return list;
    }

    /**
     * <p>
     * 合并多个list。该操作不会修改原先的list，而是直接返回一个所有list并集的新list。
     * </p>
     * 新list中元素的顺序是list传参顺序+list中的元素顺序。
     * @author lenny@bigonelab.com
     * @date 2022/8/28 10:21
     * @param lists 需要合并的lists
     * @return java.util.List<T>
     **/
    @SafeVarargs
    public static <T> List<T> mergeLists(List<T>... lists) {
        return Arrays.stream(lists).flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * <p>
     * 并行流合并多个list。该操作不会修改原先的list，而是直接返回一个所有list并集的新list。
     * </p>
     * 新list中元素的顺序不能被保证，适合对比较大的list进行合并操作。
     * @author lenny@bigonelab.com
     * @date 2022/8/28 10:21
     * @param lists 需要合并的lists
     * @return java.util.List<T>
     **/
    @SafeVarargs
    public static <T> List<T> mergeListsParallel(List<T>... lists) {
        return Arrays.stream(lists).parallel().flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * 数组复制
     *
     * @param fromList
     * @param toClass
     * @return
     */
    public static List copyTo(List fromList, Class toClass) {
        try {
            List toList = new ArrayList();
            Object tempObj;
            for (Object aFromList : fromList) {
                tempObj = toClass.newInstance();
                BeanUtils.copyProperties(aFromList, tempObj, toClass);
                toList.add(tempObj);
            }
            return toList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ResponseEnum.REQUEST_PARAMETER_ERROR);
        }
    }

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 4);
        System.out.println(listToString(integerList, ","));

        System.out.println(listToString(null, ","));

        System.out.println(listToString(Arrays.asList(3), ","));
    }

}
