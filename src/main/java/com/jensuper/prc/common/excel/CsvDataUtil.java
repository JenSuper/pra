package com.jensuper.prc.common.excel;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jichao
 * @version V1.0
 * @description: csv文件处理
 * @date 2019/09/03
 */
@Slf4j
public class CsvDataUtil {

    /** *
     * 读取csv文件
     * 格式：表名 =》 所有行数据
     * @author jichao
     * @date 2019/09/16 11:30
     * @param file
     * @return java.util.Map<java.lang.String,java.util.List<java.util.List<java.lang.String>>>
     */
    public static Map<String, List<List<String>>> readDataStructure(MultipartFile file) throws IOException {
        // 文件名为表名
        String tableName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
        // 文件流-读取内容
        InputStream inputStream = file.getInputStream();
        // 文件流-读取编码
        InputStream inputStreamCode = file.getInputStream();

        // 返回结果 k 表名 v 所有行对应的数据
        Map<String, List<List<String>>> mapRet = new HashMap<>();
        // 表内的数据
        List<List<String>> listRet = new ArrayList<>();
        // 读取csv文件内容
        InputStreamReader streamReader = null;
        try {
            // 获取文件编码格式
            String fileCode = TextDataUtil.codeString(inputStreamCode);
            streamReader = new InputStreamReader(inputStream, fileCode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CSVReader reader = new CSVReader(streamReader);
        try {
            // 获取所有行，转换为list
            List<String[]> listData = reader.readAll();
            for (int i = 0; i < listData.size(); i++) {
                // 首行为空，抛异常
                if (listData.get(0).length == 0) {
//                    throw new CustomException(DataSourceEnum.FILE_FIRSTROW_ERROR.getCode(), DataSourceEnum.FILE_FIRSTROW_ERROR.getMsg());
                }
                String[] dataArr = listData.get(i);
                List<String> csvData = getCsvData(dataArr);
                if (CollectionUtils.isEmpty(csvData)) {
                    continue;
                }
                // 将日期格式化
                List<String> csvDataList = csvData.stream().map(data -> formateDate(data)).collect(Collectors.toList());
                listRet.add(csvDataList);
            }
        } catch (IOException e) {
//            throw new CustomException(DataSourceEnum.UPLOAD_FILE_ERROR.getCode(), DataSourceEnum.UPLOAD_FILE_ERROR.getMsg());
        }
        mapRet.put(tableName, listRet);
        return mapRet;
    }


    /**
     * 获取csv文件每一行数据 数组转换为集合
     *
     * @param dataArr
     * @return java.util.List<java.lang.String>
     * @author jichao
     * @date 2019/09/03 10:05
     */
    private static List<String> getCsvData(String[] dataArr) {
        if (dataArr == null || dataArr.length == 0) {
            return null;
        }
        List<String> listRet = new ArrayList<>();
        for (String data : dataArr) {
            listRet.add(data.trim());
        }
        return listRet;
    }

    /**
     * 格式化日期
     * 转换后的格式：yyyy-MM-dd
     *
     * @param param
     * @return java.lang.String
     * @author jichao
     * @date 2019/09/04 18:41
     */
    private static String formateDate(String param) {
        if (StringUtils.isEmpty(param)) {
            return param;
        }
        // 日期正则集合
        List<String> dateRegexList = DataSourceConst.dateRegexList;
        // 匹配标识
        Optional<String> findFirst = dateRegexList.stream().filter(regex -> param.matches(regex)).findFirst();
        if (findFirst.isPresent()) {
            return param.replaceAll("/", "-")
                    .replaceAll("年", "-")
                    .replaceAll("月", "-")
                    .replaceAll("日", "");
        }
        // 金额数字去逗号
        if (param.matches(DataSourceConst.DECIMALRREGEX) && !param.matches(DataSourceConst.INTREGEX)) {
            return param.replaceAll(",", "").replaceAll("，", "");
        }
        return param;
    }

}
