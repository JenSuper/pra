package com.jensuper.prc.design.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jichao
 * @version V1.0
 * @description: 树枝构件
 * @date 2019/08/21
 */
public class PersonBranch extends PersonNode {

    /**
     * 树叶节点
     */
    private List<PersonNode> childPersonNode = new ArrayList<>();


    public PersonBranch(String name, String sex, int age) {
        super(name, sex, age);
    }

    /**
     * 添加树叶节点
     */
    public void addPersonNode(PersonNode personNode) {
        this.childPersonNode.add(personNode);
    }

    /**
     * 获取当前树枝对应的树叶子节点
     */
    public List<PersonNode> getChildPersonNode() {
        return this.childPersonNode;
    }


}
