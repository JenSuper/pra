package com.jensuper.prc.design.composite;

import org.junit.Test;

import java.util.List;

/**
 * @author jichao
 * @version V1.0
 * @description: 组合模式
 * @date 2019/08/21
 */
public class InitTest {

    /**
     * 1. 构建成员树结构
     * 2. 遍历获取数结构
     */
    @Test
    public void test(){
        PersonBranch tree = createTree();
        showTree(tree);
    }

    /**
     * 展示树结构
     * @param tree
     */
    private void showTree(PersonBranch tree) {
        System.out.println(tree.getPersonNode());
        List<PersonNode> personNodeList = tree.getChildPersonNode();
        personNodeList.stream().forEach(node->{
            // 树叶节点
            if (node instanceof PersonLeaf) {
                System.out.println(node.getPersonNode());
            } else {
                // 树枝节点
                showTree((PersonBranch)node);
            }
        });
    }

    /**
     * 创建成员树结构
     */
    private PersonBranch createTree() {
        // 根节点
        PersonBranch root = new PersonBranch("one","女",12);

        PersonBranch rootChild1 = new PersonBranch("rootChild1","男",13);
        PersonBranch rootChild2 =  new PersonBranch("rootChild2","女",14);

        // rootChild1子节点
        PersonLeaf leaf1 = new PersonLeaf("leaf1","女",10);
        PersonLeaf leaf2 = new PersonLeaf("leaf2","男",11);
        PersonLeaf leaf3 = new PersonLeaf("leaf3","女",9);
        // rootChild2子节点
        PersonLeaf leaf4 = new PersonLeaf("leaf4","女",7);
        PersonLeaf leaf5 = new PersonLeaf("leaf5","男",8);
        PersonLeaf leaf6 = new PersonLeaf("leaf6","女",6);

        // 构建树结构
        root.addPersonNode(rootChild1);
        root.addPersonNode(rootChild2);

        rootChild1.addPersonNode(leaf1);
        rootChild1.addPersonNode(leaf2);
        rootChild1.addPersonNode(leaf3);

        rootChild2.addPersonNode(leaf4);
        rootChild2.addPersonNode(leaf5);
        rootChild2.addPersonNode(leaf6);

        return root;
    }
}
