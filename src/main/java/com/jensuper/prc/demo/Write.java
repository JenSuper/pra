package com.jensuper.prc.demo;

import java.util.*;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2021/08/20
 */
public class Write {

    /**
     * // 假设有如下数组：
     * // [[1, 3, 5, 7], [5, 3, 8, 10], [6, 3, 5, 12] ]
     * // 设计一个函数,返回N个数组的交集，传入以上参数，应返回结果: [3, 5]
     */
    public static void main(String[] args) {
        // 参数
        List<List<Integer>> paramList = new ArrayList<>();
        List<Integer> list1 = Arrays.asList(1, 3, 5, 7);
        List<Integer> list2 = Arrays.asList(5, 3, 8, 10);
        List<Integer> list3 = Arrays.asList(6, 3, 5, 12);
        paramList.add(list1);
        paramList.add(list2);
        paramList.add(list3);
        // 临时容器：用于存储交集元素
        Map<Integer, Integer> map = new HashMap<>();
        // 记录当前有多少数组
        int size = paramList.size();
        // 遍历每个数组中的元素，如果map中存在，个数+1，如果全部遍历完，元素个数=size，则为交集
        paramList.forEach(listTmp -> {
            listTmp.stream().distinct().forEach(tmp->{
                if (map.containsKey(tmp)) {
                    map.put(tmp, map.get(tmp) + 1);
                }else {
                    map.put(tmp, 1);
                }
            });
        });
        // 遍历map，找到value与数组个数一样的key
        List<Integer> result = new ArrayList<>();
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            if (next.getValue() == size) {
                result.add(next.getKey());
            }
        }
    }
}
