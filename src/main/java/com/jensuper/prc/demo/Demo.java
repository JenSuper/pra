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
        String content = "\"id\":64,\"map\":100";
        String idRegex = "(\"[a-zA-Z]*?(Id|id)\"):([0-9]+)\\,";
        Matcher matcherIdStr = Pattern.compile(idRegex).matcher(content);
        while (matcherIdStr.find()) {
            // 匹配到的id及value
            String group = matcherIdStr.group(0);
            String idKey = matcherIdStr.group(1);
            String idValue = matcherIdStr.group(3);
            System.out.println(group);
            System.out.println(idKey);
            System.out.println(idValue);
            String s = "(\\(\\)|\\[\\])";
        }

    }

    @Test
    public void test33() {
        String bodyStr = "\"[a-zA-Z]*?(Id|id)\":[0-9]+";
        String content = "{\"code\":0,\"data\":{\"count\":8,\"current\":1,\"data\":[{\"avatar\":\"http://172.24.103.102:8888/group1/M00/01/27/rBhnZl7Hj_aAdP0GAAB22Jwq9og20.jpeg\",\"cockpitId\":123,\"createBy\":null,\"createByAvatar\":\"\",\"createByName\":\"\",\"createByNickName\":\"\",\"dashboardDataSetCount\":2,\"dataSetCount\":11,\"dataSourceCount\":20,\"description\":\"\",\"gmtCreate\":\"2020-05-08 10:46:44\",\"id\":146,\"markStatus\":1,\"name\":\"20200508_jichao\",\"orgId\":1,\"orgName\":\"卓朗科技\",\"type\":\"open\",\"userId\":120,\"visualWidgetCount\":2},{\"avatar\":\"\",\"cockpitCount\":null,\"createBy\":null,\"createByAvatar\":\"\",\"createByName\":\"\",\"createByNickName\":\"\",\"dashboardDataSetCount\":0,\"dataSetCount\":1,\"dataSourceCount\":1,\"description\":\"\",\"gmtCreate\":\"2020-06-18 09:27:31\",\"id\":246,\"markStatus\":0,\"name\":\"20200618_jichao\",\"orgId\":1,\"orgName\":\"\",\"type\":\"open\",\"userId\":120,\"visualWidgetCount\":0},{\"avatar\":\"\",\"cockpitCount\":null,\"createBy\":null,\"createByAvatar\":\"\",\"createByName\":\"\",\"createByNickName\":\"\",\"dashboardDataSetCount\":0,\"dataSetCount\":1,\"dataSourceCount\":1,\"description\":\"\",\"gmtCreate\":\"2020-05-29 10:36:27\",\"id\":195,\"markStatus\":0,\"name\":\"20200529_jichao\",\"orgId\":1,\"orgName\":\"\",\"type\":\"open\",\"userId\":120,\"visualWidgetCount\":0},{\"avatar\":\"\",\"cockpitCount\":null,\"createBy\":null,\"createByAvatar\":\"\",\"createByName\":\"\",\"createByNickName\":\"\",\"dashboardDataSetCount\":0,\"dataSetCount\":0,\"dataSourceCount\":1,\"description\":\"\",\"gmtCreate\":\"2020-05-26 10:01:11\",\"id\":172,\"markStatus\":0,\"name\":\"工作区02\",\"orgId\":1,\"orgName\":\"\",\"type\":\"open\",\"userId\":120,\"visualWidgetCount\":0},{\"avatar\":\"\",\"cockpitCount\":null,\"createBy\":null,\"createByAvatar\":\"\",\"createByName\":\"\",\"createByNickName\":\"\",\"dashboardDataSetCount\":0,\"dataSetCount\":0,\"dataSourceCount\":0,\"description\":\"\",\"gmtCreate\":\"2020-05-15 14:59:44\",\"id\":148,\"markStatus\":0,\"name\":\"20200515_jichao\",\"orgId\":1,\"orgName\":\"卓朗科技\",\"type\":\"open\",\"userId\":120,\"visualWidgetCount\":0},{\"avatar\":\"\",\"cockpitCount\":null,\"createBy\":null,\"createByAvatar\":\"\",\"createByName\":\"\",\"createByNickName\":\"\",\"dashboardDataSetCount\":1,\"dataSetCount\":2,\"dataSourceCount\":2,\"description\":\"\",\"gmtCreate\":\"2020-04-26 11:17:39\",\"id\":140,\"markStatus\":0,\"name\":\"工作区-jichao-0426\",\"orgId\":1,\"orgName\":\"卓朗科技\",\"type\":\"open\",\"userId\":120,\"visualWidgetCount\":2},{\"avatar\":\"\",\"cockpitCount\":null,\"createBy\":null,\"createByAvatar\":\"\",\"createByName\":\"\",\"createByNickName\":\"\",\"dashboardDataSetCount\":1,\"dataSetCount\":3,\"dataSourceCount\":3,\"description\":\"\",\"gmtCreate\":\"2020-04-08 16:06:28\",\"id\":134,\"markStatus\":0,\"name\":\"jichao_0408\",\"orgId\":1,\"orgName\":\"卓朗科技\",\"type\":\"open\",\"userId\":120,\"visualWidgetCount\":2},{\"avatar\":\"\",\"cockpitCount\":null,\"createBy\":null,\"createByAvatar\":\"\",\"createByName\":\"\",\"createByNickName\":\"\",\"dashboardDataSetCount\":10,\"dataSetCount\":18,\"dataSourceCount\":25,\"description\":\"\",\"gmtCreate\":\"2020-03-03 16:54:49\",\"id\":112,\"markStatus\":0,\"name\":\"jichao_0303\",\"orgId\":1,\"orgName\":\"卓朗科技\",\"type\":\"open\",\"userId\":120,\"visualWidgetCount\":9}],\"size\":15},\"msg\":\"操作成功\"}";
        Matcher matcher = Pattern.compile(bodyStr).matcher(content);
        while (matcher.find()) {
            String group0 = matcher.group(0);
            String group1 = matcher.group(1);
//            String s = group1.replaceAll("^[0-9]+", "");
//            String group = group1.replaceAll(s, "\"id\":" + "123");
            System.out.println(group0);
            System.out.println(group1);
        }
    }


    @Test
    public void test12312(){
        String s = "`123_tmp";
        String tmp = s.substring(0, s.indexOf("_tmp"));
        System.out.println(tmp);
    }



}
