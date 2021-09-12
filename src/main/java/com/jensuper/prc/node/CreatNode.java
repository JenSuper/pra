package com.jensuper.prc.node;

import com.jensuper.prc.entity.NodeDTO;

import java.util.Arrays;
import java.util.List;

/**
 * @author jichao
 * @version V1.0
 * @description: 创建链表结构
 * @date 2019/08/05
 */
public class CreatNode {

    public static void main(String[] args) {
        List<Integer> listData = Arrays.asList(1, 2, 3, 4, 5);

        CreatNode creatNode = new CreatNode();
        NodeDTO nodeDTO = creatNode.create(listData);

        // 输出结果
        NodeDTO.printNode(nodeDTO);
    }

    /**
     * 创建链表结构
     * @param listData
     * @return
     */
    public NodeDTO create(List<Integer> listData) {
        if (listData.isEmpty()) {
            return null;
        }

        // 第一个节点
        NodeDTO<Integer> first = new NodeDTO<>(listData.get(0));

        // 下一个节点
        first.setNext(create(listData.subList(1, listData.size())));

        return first;
    }


}
