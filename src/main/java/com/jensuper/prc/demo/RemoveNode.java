package com.jensuper.prc.demo;

import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/08/15
 */
public class RemoveNode {

    private List<Integer> listNode = new ArrayList<>();

    @Test
    public void test() {
        listNode.add(1);
        listNode.add(2);
        listNode.add(2);
        listNode.add(3);
        listNode.add(4);

        for (Integer node : listNode) {
            int count = Collections.frequency(listNode, node);
            if (count >= 2) {
                Iterator<Integer> iterator = listNode.iterator();
                while (iterator.hasNext()) {
                    Integer next = iterator.next();
                    if (node.equals(next)) {
                        iterator.remove();
                    }
                }
                System.out.println(listNode);
            }
        }
        List<Integer> listR = new ArrayList<>();
        listNode.stream().forEach(node -> {
            int count = Collections.frequency(listNode, node);
            if (count >= 2) {
                listR.add(node);
            }
        });
        Iterator<Integer> iterator = listNode.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (listR.contains(next)) {
                iterator.remove();
            }
        }
        System.out.println(listNode);
    }

    @Test
    public void test123(){
//        String sql = "SELECT * FROM data_set as  d123 JOIN data_source   as    ad     ON d.data_source_id = s.id";
        String sql = "SELECT * FROM data_set JOIN data_source ";
        List<String> list = new ArrayList<>();
        list.add("data_source");
        list.add("data_set");
        Matcher mac = Pattern.compile("data_set\\s+(as\\s+)?(.*?)\\s").matcher(sql);
        if (mac.find()) {
            System.out.println(mac.group(2));
        }
    }
}
