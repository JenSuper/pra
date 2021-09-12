package com.jensuper.prc.design.builder.two;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2021/07/07
 */
public class Item2ConcreteBuilder extends ItemBuilder<ItemParam2>{

    private StringBuffer sf = new StringBuffer();
    private ItemParam2 param;

    @Override
    public void buildCreate() {
        sf.append(this.param.getName()).append(" ");
    }

    @Override
    public void buildColumn() {
        sf.append(this.param.getColumn()).append(" ");
    }


    @Override
    public String build(ItemParam2 param) {
        this.param = param;
        buildCreate();
        buildColumn();
        return sf.toString();
    }
}
