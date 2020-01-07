package com.jensuper.prc.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/08/05
 */
@Data
@Accessors(chain = true)
public class NodeDTO<T> {

    /**
     * 当前节点
     */
    private T value;
    /**
     * 下一个节点
     */
    private NodeDTO<T> next;

    public NodeDTO(T value) {
        this.value = value;
        this.next = null;
    }

    public static void printNode(NodeDTO nodeDTO) {
        while (nodeDTO != null) {
            System.out.println(nodeDTO.getValue());
            nodeDTO = nodeDTO.getNext();
        }
    }
}
