package com.jensuper.prc.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jensuper.prc.date.DateStyle;
import com.jensuper.prc.entity.Person;
import com.jensuper.prc.entity.SankeyChartData;
import com.jensuper.prc.secret.DesUtils;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.junit.Test;
import org.python.antlr.ast.Str;
import org.python.bouncycastle.cms.PasswordRecipientId;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;
import sun.util.calendar.BaseCalendar;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/08/22
 */
public class Demo {

    /**
     * 计时器
     */
    @Test
    public void test() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("开始任务");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    @Test
    public void test2() {
        List<Map<String, Object>> resultList = new ArrayList<>();

        Map<String, Object> mapT = new HashMap<>();
        mapT.put("城市", "北京市");
        mapT.put("区域", "朝阳区");
        mapT.put("乡镇", "高碑店");
        mapT.put("人数", "10");
        mapT.put("人员率", "10%");

        Map<String, Object> mapL = new HashMap<>();
        mapL.put("城市", "北京市");
        mapL.put("区域", "海淀区");
        mapL.put("乡镇", "中关村");
        mapL.put("人数", "20");
        mapL.put("人员率", "20%");

        Map<String, Object> mapQ = new HashMap<>();
        mapQ.put("城市", "北京市");
        mapQ.put("区域", "朝阳区");
        mapQ.put("乡镇", "常营");
        mapQ.put("人数", "15");
        mapQ.put("人员率", "15%");


        Map<String, Object> mapH = new HashMap<>();
        mapH.put("城市", "北京市");
        mapH.put("区域", "丰台区");
        mapH.put("乡镇", "方庄");
        mapH.put("人数", "20");
        mapH.put("人员率", "20%");

        resultList.add(mapT);
        resultList.add(mapL);
        resultList.add(mapQ);
        resultList.add(mapH);

        List<String> group = Arrays.asList("城市", "区域", "乡镇");
        String agger = "人数";

        List<SankeyChartData> listData = new ArrayList<>();

        // 设置根节点
        this.makeDataRoot(listData, resultList, group.get(0));

        // 设置子节点
        for (SankeyChartData chartData : listData) {
            List<SankeyChartData> children = this.makeData(resultList, group, 1, agger);
            chartData.setChildren(children);
        }
        System.out.println(JSON.toJSONString(listData, false));
    }

    private List<SankeyChartData> makeData(List<Map<String, Object>> resultList, List<String> groupList, int index, String agger) {
        // 按照当前维度进行分组
        Map<String, List<Map<String, Object>>> mapGroup = resultList.stream().collect(Collectors.groupingBy(map -> map.get(groupList.get(index)).toString()));
        // 当前数据集合
        List<SankeyChartData> chartDataList = new ArrayList<>();
        // 遍历分组后的数据
        mapGroup.forEach((mapK, mapV) -> {
            // 父节点
            SankeyChartData dataP = new SankeyChartData();
            dataP.setName(mapK);

            int indexTmp = index + 1;

            // 如果索引打印维度集合数量则结束
            if (indexTmp > groupList.size() - 1) {
                return;
            }

            // 子节点
            List<SankeyChartData> listChild = new ArrayList<>();
            mapV.stream().forEach(a -> {

                this.makeData(resultList, groupList, indexTmp, agger);

                // 如果是最后一个维度，则获取指标结果
                if (indexTmp == groupList.size() - 1) {
                    SankeyChartData child = new SankeyChartData();
                    String value = a.get(groupList.get(indexTmp)).toString();
                    child.setName(value);
                    child.setValue(a.get(agger).toString());
                    listChild.add(child);
                    return;
                }

                SankeyChartData child = new SankeyChartData();
                String value = a.get(groupList.get(indexTmp)).toString();
                child.setName(value);
                listChild.add(child);

            });
            dataP.setChildren(listChild);

            // 将当前节点放入集合中
            chartDataList.add(dataP);
        });
        return chartDataList;
    }

    private void makeDataRoot(List<SankeyChartData> listData, List<Map<String, Object>> resultList, String group) {
        // 按照当前维度进行分组
        Map<String, List<Map<String, Object>>> mapGroup = resultList.stream().collect(Collectors.groupingBy(map -> map.get(group).toString()));

        // 遍历分组后的数据
        mapGroup.forEach((mapK, mapV) -> {
            SankeyChartData dataP = new SankeyChartData();
            // 设置父节点
            dataP.setName(mapK);
            listData.add(dataP);
        });
    }


    @Test
    public void test123() {
        List<Map<String, Object>> listMap = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张飞");
        map.put("age", "11");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "刘备");
        map2.put("age", "11");

        Map<String, Object> map3 = new HashMap<>();
        map3.put("name", "关羽");
        map3.put("age", "12");

        listMap.add(map);
        listMap.add(map2);
        listMap.add(map3);

        Map<Object, List<Map<String, Object>>> ret = listMap.stream().collect(Collectors.groupingBy(mapTmp -> mapTmp.get("age")));

        System.out.println(ret);
    }

    @Test
    public void test3() {
        String regex = "(((max|min|count|sum|avg){[.*?]})|([.*?][.*?]))+";
        String content = "max{[订单数量]}max{[订单号]}";
        System.out.println(content.matches(regex));
    }


    @Test
    public void test4() {
        String content = "{\"idList\":[\"c8ff124f6df0ad98\",\"c8ff12231234f6df0ad98\"]}";

        JSONObject jsonObject = JSON.parseObject(content);
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            System.out.println(entry.getKey());
            JSONArray objects = JSON.parseArray(entry.getValue().toString());
            Iterator<Object> iterator = objects.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }

//        Matcher matcherIdStr = Pattern.compile(regex).matcher(content);
//        if (matcherIdStr.find()) {
//            System.out.println(matcherIdStr.group(2));
//            String regexM = "[a-zA-Z0-9]+";
//            Matcher matcherIdMStr = Pattern.compile(regexM).matcher(content);
//            while (matcherIdMStr.find()) {
//                System.out.println(matcherIdMStr.group(0));
//            }
//        }
    }

    @Test
    public void test22() {
        String content = "INSERT INTO `base_component` VALUES (6, '矩形', NULL, '{}', NULL, 'rectangle', NULL, 'material', 'http://172.24.103.117:8888/group1/M00/00/2F/rBhndV7U4cuAAEfsAAADg4p73-E725.png', NULL, NULL, 0, NULL, 0, '2020-04-16 17:39:41', '2020-05-11 15:40:45', 101, NULL, NULL);";
        String idRegex = "([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://|[wW]{3}.|[wW][aA][pP].|[fF][tT][pP].|[fF][iI][lL][eE].)[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
        Matcher matcherIdStr = Pattern.compile(idRegex).matcher(content);
        while (matcherIdStr.find()) {
            // 匹配到的id及value
            String group = matcherIdStr.group(0);
            System.out.println(group);
        }

    }





}
