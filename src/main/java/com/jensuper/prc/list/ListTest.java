package com.jensuper.prc.list;

import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2020/03/24
 */
public class ListTest {
    
    @Test
    public void test(){
        List<String> list = new ArrayList<String>(){
            {
                add("1");
                add("2");
                add("3");
                add("4");
                add("5");
                add("6");
            }
        };
        // 当前页数
        int currentPage = 2;
        // 每页大小
        int pageSize = 3;
        // 总数
        int total = list.size();
        List<String> limit = list.stream().skip((currentPage - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        System.out.println(limit);
    }

    @Test
    public void test2(){
        String a = "select ([编号]+[订单号])*max{[订单数量]+[订单号]} as 计算字段 , o.*  from order_detail o";
        String a1 = "select max{[订单数量]+[订单号]}*([编号]+[订单号]) as 计算字段 , o.*  from order_detail o";
        String a3 = "select max{[订单数量]}+max{[订单号]}*([编号]+[订单号])+max{[编号]+[订单号]} as 计算字段 , o.*  from order_detail o";
        // 替换字段为原名称
        String regex1 = "\\[.*?\\]";
        Matcher matcher1 = Pattern.compile(regex1).matcher(a1);
        while (matcher1.find()) {
            System.out.println(matcher1.group());
        }
        System.out.println("-------------分割线-------------");
        // 获取函数
        String regex = "max\\{.*?\\}";
        Matcher matcher = Pattern.compile(regex).matcher(a3);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void test3(){
        String content = "[签约额（元）]+[备注]";
        String regex = "\\[(.*?)\\]";
        Matcher mat = Pattern.compile(regex).matcher(content);
        while (mat.find()) {
            String column = mat.group(1);
            content = content.replace(column, "123");
        }
        System.out.println(content);
    }
}
