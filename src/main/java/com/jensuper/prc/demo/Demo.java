package com.jensuper.prc.demo;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jensuper.prc.aop.entity.Record;
import com.jensuper.prc.entity.SankeyChartData;
import com.jensuper.prc.entity.TreeDTO;
import com.jensuper.prc.entity.User;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpResponse;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/08/22
 */
public class Demo {
    public static void sum(int a, int b) {
        int sumCount = a + b;
        System.out.println(sumCount);
    }

    public static void main1(String[] args) {
        int a = 10;
        int b = 10;
        sum(a, b);
    }

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

    public static void main2(String[] args) {
        String host = "https://aliv18.data.moji.com";
        String path = "/whapi/json/alicityweather/limit";
        String method = "POST";
        String appcode = "e42fd2c1c87c4955a0ef12d585c9dcbe";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("cityId", "2");
        bodys.put("token", "27200005b3475f8b0e26428f9bfb13e9");


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testP() throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://fchxxn.com/api/Pay/CheckOrder?productid=6b419bee74244f93bb4d57b02bb71d12&id=";
        while (true) {
            Thread.sleep(RandomUtils.nextInt(1,10000));
            String id = UUID.randomUUID().toString().replace("-", "");
            ResponseEntity<String> forEntity = restTemplate.getForEntity(url + id, String.class);
            JSONObject jsonObject = JSON.parseObject(forEntity.getBody());
            if ((Boolean) jsonObject.get("success")) {
                System.out.println(forEntity);
                break;
            }
        }
    }
    
    
    @Test
    public void test222(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://aliv18.data.moji.com/whapi/json/alicityweather/condition";
        String appcode = "";
        // headers
        HttpHeaders requestHeaders = new HttpHeaders();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        requestHeaders.add("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        requestHeaders.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        //body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("cityId", "2");
        requestBody.add("token", "");
        //HttpEntity
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(requestBody, requestHeaders);
        //post
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        System.out.println(responseEntity.getBody());
    }


    @Test
    public void test22222() throws IOException {

        String entryFile = "F:\\bz\\test\\新建文件夹.zip";

        File file = new File(entryFile);

        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));

        File fout = null;
        String parent = file.getParent();
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null && !entry.isDirectory()) {
            fout = new File(parent, entry.getName());
            if (!fout.exists()) {
                (new File(fout.getParent())).mkdirs();
            }

            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fout));

            int b = -1;
            byte[] buffer = new byte[1024];
            while ((b = zis.read(buffer)) != -1) {
                bos.write(buffer, 0, b);
            }
            bos.close();
        }

        zis.close();
    }

    @Test
    public void test123123213(){
        //        map_Kd ./贴图/Earth.png
//        map_ks ./贴图/EarthSpec.png
//        norm ./贴图/EarthNormal.png
        String data = "norm ./贴图/EarthNormal.png";

        String regex = "(map_[a-zA-Z]{2}|norm)\\s(.*)";

        String filePath = "F:\\bz\\01-测试文件\\car.mtl";
        try {
            List<String> str = FileUtils.readLines(new File(filePath), "utf-8");
            for (int i = 0; i < str.size(); i++) {
                String s = str.get(i);
                if (s.contains("map")) {
                    Matcher mac = Pattern.compile(regex).matcher(s);
                    if (mac.find()) {
                        System.out.println("mac.group(2)==>" + mac.group(2));
                        String newStr = s.replace(mac.group(2), "test.png");
                        str.set(i, newStr);
                    }
                }
            }
            FileUtils.writeLines(new File(filePath), str);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test1233123(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("test");
        List<Record> listNew = new ArrayList<>();
        List<Record> list = new ArrayList<>();
        list.stream().collect(Collectors.groupingBy(Record::getBegin)).forEach((k,v)->{
            listNew.addAll(v.stream().distinct().collect(Collectors.toList()));
        });
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    @Test
    public void testtest(){
        Deque dequeLeft = new ArrayDeque();
        dequeLeft.push(1);
        dequeLeft.push(2);
        dequeLeft.push(3);
        System.out.println("left=>"+dequeLeft);
        Deque dequeRight = new ArrayDeque();

        // 前进
        Object poll = dequeLeft.poll();
        System.out.println("前进"+poll);
        dequeRight.push(poll);

        System.out.println("left=>"+dequeLeft);
        System.out.println("right=>"+dequeRight);
    }

    @Test
    public void test2222(){
        List<TreeDTO> nodeList = new TreeDTO().init();
        List<TreeDTO> rootList = nodeList.stream().filter(t -> t.getPid().equals(0)).collect(Collectors.toList());
        rootList.forEach(root->{
            List<TreeDTO> child = this.getChild(root, nodeList);
            root.setChildren(child);
        });
        System.out.println();
    }

    private List<TreeDTO> getChild(TreeDTO node,List<TreeDTO> nodeList) {
        List<TreeDTO> childList = nodeList.stream().filter(t -> t.getPid().equals(node.getId())).collect(Collectors.toList());
        childList.forEach(childNodeTmp->{
            List<TreeDTO> child = this.getChild(childNodeTmp, nodeList);
            childNodeTmp.setChildren(child);
        });
        return childList;
    }
    
    @Test
    public void test3() {
        DecimalFormat format = new DecimalFormat("###,##00");
        String s = format.format(9050173587L);
        System.out.println(s);
    }

    /**
     * 千分位
     * @param text
     * @return
     */
    public static String formatThousand(String text) {
        if (StringUtils.isBlank(text) || NumberUtils.isCreatable(text)) {
            return null;
        }
        DecimalFormat df = null;
        if (text.indexOf(".") > 0) {
            int index = text.length() - text.indexOf(".") - 1;
            if (index == 0) {
                df = new DecimalFormat("###,##0.");
            } else if (index == 1) {
                df = new DecimalFormat("###,##0.0");
            } else {
                df = new DecimalFormat("###,##0.00");
            }
        } else {
            df = new DecimalFormat("###,##0");
        }
        double number = 0.0;
        try {
            number = Double.parseDouble(text);
        } catch (Exception e) {
            number = 0.0;
        }
        return df.format(number);
    }

    @Test
    public void test4() throws IOException {
        String projectInfo = "project_info";
        String pageSetting = "page_setting";
        String updates = "updates";
        String type = "boss";
        StringBuilder sResult = new StringBuilder();
        // 读取文件内容:
        List<String> dataList = FileUtils.readLines(new File("/Users/chaoji/devlop/project/test/无标题.csv"), "UTF-8");
        // 拼接数据
        for (int i = 0; i < dataList.size(); i++) {
            StringBuilder sValue = new StringBuilder();
            String data = dataList.get(0);
            String[] str = data.split(",");
            for (String s : str) {
                if (StringUtils.isBlank(s)) {
                    sValue.append("null").append(",");
                }else{
                    if (NumberUtils.isCreatable(s)) {
                        sValue.append(s).append(",");
                    }else{
                        sValue.append("'").append(s).append("'").append(",");
                    }
                }
            }
            sValue.deleteCharAt(sValue.length() - 1);

            if (i == 0) {
                String projectInfoSql = "INSERT INTO `" + projectInfo + "` (`display`, `code_name`, `dis_name`, `path_name`, `default_page`, `title`, `description`, `project`, `has_manual`, `dimensions`, `format_info`, `company_name`, `stock_code`) VALUES (%s);";
                String result = String.format(projectInfoSql, sValue);
                sResult.append(result);
            }
            if (i == 1) {
                String pageSettingSql = "INSERT INTO `" + pageSetting + "` (`project_id`, `table_name`, `date_type`, `dimension_text`, `download_type`, `sort`, `hide_num`) VALUES (%s);";
                String result = String.format(pageSettingSql, sValue);
                sResult.append(result);
            }
            if (i == 2) {
                String updatesSql = "INSERT INTO `" + updates + "` (`type`, `update_date`, `update_text`, `display_name`, `has_mail`, `project_id`, `update_params`) VALUES (%s);";
                String result = String.format(updatesSql, sValue);
                sResult.append(result);
            }
        }

    }

    // 读取CSV并且拼接成SQL直接执行（Java）
    public static void main(String[] args) {
        /**
         *
         */
        try {
            File file = new File("/Users/chaoji/devlop/project/test/无标题.csv");
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    //id,table_name,field_name
                    String[] split = line.split(",");
                    String id = split[0];
                    String tableName = split[1];
                    String fileName = split[2];
                    Integer order = Integer.parseInt(split[3]);
                    System.out.println(String.format("update table_info_copy1 set parent = (select data.id from (select id from table_info_copy1 where table_name = '%s' and field_name = '%s' and `order` = %s)data) where table_name = '%s' and parent = %s;", tableName, fileName,order, tableName, id));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test789() {
        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.get());
    }

    @Test
    public void test7890() {
        List<User> list = new ArrayList<>();
        list.add(User.builder().id(1L).name("小明").build());
        list.add(User.builder().id(2L).name("liushan").build());
        List<User> collect = list.stream().peek(e -> {
            if (e.getId() == 1) {
                e.setName("1111");
            }
        }).collect(Collectors.toList());
        System.out.println(collect);
    }




}
