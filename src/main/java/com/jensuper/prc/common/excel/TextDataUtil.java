package com.jensuper.prc.common.excel;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jichao
 * @version V1.0
 * @description: 文本数据工具类
 * @date 2019/09/11
 */
public class TextDataUtil {
    /**
     * 特殊符号需要转换
     */
    private static String symbol = "- 0";
    /**
     * 百分号 无负号
     */
    private static String nubmerRegex = "^-?[0-9.，,]+%";


    /**
     * 文件编码格式
     */
    private static int codeUtf160 = -1;
    private static int codeUtf161 = -2;

    private static int codeUnicode0 = -2;
    private static int codeUnicode1 = -1;

    private static int codeUtf80 = -17;
    private static int codeUtf81 = -69;
    private static int codeUtf82 = -65;

    /**
     * 日期格式化
     */
    public static String datePattern = "yyyy-MM-dd";

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern,Locale.CHINA);

    /**
     * 验证数据合法性
     * 1. 首行不能为空
     *
     * @param mapRet
     * @return void
     * @author jichao
     * @date 2019/09/11 10:07
     */
    public static void checkData(Map<String, List<List<String>>> mapRet) {
        // 验证数据合法性 首行不能为空
        if (mapRet == null || mapRet.size() == 0) {
//            throw new CustomException(DataSourceEnum.FILE_FIRSTROW_ERROR.getCode(), DataSourceEnum.FILE_FIRSTROW_ERROR.getMsg());
        }
        // sheet中不能为空
        for (String key : mapRet.keySet()) {
            List<List<String>> listRows = mapRet.get(key);
            if (CollectionUtils.isEmpty(listRows)) {
//                throw new CustomException(DataSourceEnum.FILE_FIRSTROW_ERROR.getCode(), DataSourceEnum.FILE_FIRSTROW_ERROR.getMsg());
            }
        }

        for (String key : mapRet.keySet()) {
            List<String> listData = mapRet.get(key).get(0);
            String dataStr = listData.parallelStream().filter(data -> StringUtils.isEmpty(data.trim()) || "\uFEFF".equals(data.trim())).findFirst().orElse(null);
            if (dataStr != null) {
//                throw new CustomException(DataSourceEnum.FILE_FIRSTROW_ERROR.getCode(), DataSourceEnum.FILE_FIRSTROW_ERROR.getMsg());
            }
        }
    }

    /**
     * 验证空集合
     *
     * @param listData
     * @return java.lang.Boolean
     * @author jichao
     * @date 2019/09/11 15:23
     */
    public static Boolean checkNullList(List<String> listData) {
        // 找到集合中不为空的元素
        for (String data : listData) {
            if (data == null) {
                continue;
            }
            data = data.trim();
            if (StringUtils.isNotEmpty(data)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 校验是否为日期
     * 1. 通过日期正则进行完全匹配
     *
     * @param paramList
     * @return java.lang.Boolean
     * @author jichao
     * @date 2019/09/03 11:24
     */
    public static Boolean checkDateColumn(List<String> paramList) {
        if (CollectionUtils.isEmpty(paramList)) {
            return false;
        }
        // 日期正则集合
        List<String> dateRegexList = DataSourceConst.dateRegexList;
        // 匹配标识
        boolean flag = false;
        for (String param : paramList) {
            Optional<String> findFirst = dateRegexList.stream().filter(regex -> param.matches(regex)).findFirst();
            if (!findFirst.isPresent()) {
                return false;
            }
            flag = true;
        }
        return flag;
    }

    /**
     * 验证单个元素是否为日期格式
     * 如果是日期格式则格式化为普通格式 yyyy-mm-dd HH:mm:ss
     *
     * @param param
     * @return java.lang.Boolean
     * @author jichao
     * @date 2019/09/11 16:45
     */
    public static String checkDateColumnOne(String param) {
        if (StringUtils.isEmpty(param)) {
            return param;
        }
        // 日期正则集合
        List<String> dateRegexList = DataSourceConst.dateRegexList;
        // 匹配日期
        Optional<String> findFirst = dateRegexList.stream().filter(regex -> param.matches(regex)).findFirst();
        if (findFirst.isPresent()) {
            return formateDate(param);
        }
        return param;
    }

    /**
     * 格式化日期
     *
     * @param content
     * @return java.lang.String
     * @author jichao
     * @date 2019/09/16 16:45
     */
    private static String formateDate(String content) {
        if (StringUtils.isEmpty(content)) {
            return content;
        }
        return content.replaceAll("年", "-")
                .replaceAll("月", "-")
                .replaceAll("日", "");
    }


    /**
     * 检查是否为金额
     * 1. 去掉逗号及点
     * 2. 判断是否为数字
     *
     * @param paramList
     * @return
     */
    public static Boolean checkDecimalColumn(List<String> paramList) {
        if (CollectionUtils.isEmpty(paramList)) {
            return false;
        }
        // 匹配标识
        boolean flag = false;
        for (String param : paramList) {
            if (!param.matches(DataSourceConst.DECIMALRREGEX)) {
                return false;
            }
            flag = true;
        }
        return flag;
    }




    /**
     * 检查是否为数字
     * 1. 判断是纯数字
     *
     * @param paramList
     * @return java.lang.Boolean
     * @author jichao
     * @date 2019/09/03 13:48
     */
    public static Boolean checkIntColumn(List<String> paramList) {
        if (CollectionUtils.isEmpty(paramList)) {
            return false;
        }
        // 匹配标识
        boolean flag = false;
        for (String param : paramList) {
            if (!param.matches(DataSourceConst.INTREGEX)) {
                return false;
            }
            flag = true;
        }
        return flag;
    }

    /**
     * 处理特殊格式
     * 1. - 0
     * 2. 百分号转小数
     *
     * @param param
     * @return java.lang.String
     * @author jichao
     * @date 2019/09/04 16:43
     */
    public static String formatSymbol(String param) {
        if (StringUtils.isEmpty(param)) {
            return null;
        }
        param = param.trim();
        // 1. - 0
        if (symbol.equals(param)) {
            return "0";
        }
        // 2. 百分号转小数 保留2位小数
        if (param.matches(nubmerRegex)) {
            return decimalFormatNumber(param);
        }
        // 3. 金额转换 去掉千分位
        if (param.matches(DataSourceConst.DECIMALRREGEX) && !param.matches(DataSourceConst.INTREGEX)) {
            return pointNumber(param);
        }
        return param;
    }

    /** *
     * 百分号保留2位小数点
     * @author jichao
     * @date 2019/09/17 9:34
     * @param param
     * @return java.lang.String
     */
    private static String decimalFormatNumber(String param) {
        if (StringUtils.isEmpty(param)) {
            return param;
        }
        param = param.replaceAll(",", "").replaceAll("，", "");
        // 去掉百分号
        param = param.substring(0, param.length() - 1);
        BigDecimal point = new BigDecimal(100);
        BigDecimal retBigDecimal = new BigDecimal(param).divide(point).setScale(2, BigDecimal.ROUND_HALF_UP);
        return ObjectUtils.nullSafeToString(retBigDecimal);
    }

    /** *
     * 保留2位小数
     * @author jichao
     * @date 2019/09/17 12:01
     * @param param
     * @return java.lang.String
     */
    private static String pointNumber(String param) {
        if (StringUtils.isEmpty(param)) {
            return param;
        }
        param = param.replaceAll(",", "").replaceAll("，", "");
        BigDecimal retBigDecimal = new BigDecimal(param).setScale(2, BigDecimal.ROUND_HALF_UP);
        return ObjectUtils.nullSafeToString(retBigDecimal);
    }


    /**
     * 获取文件编码格式
     *
     * @param inputStream
     * @return java.lang.String
     * @author jichao
     * @date 2019/09/12 15:24
     */
    public static String codeString(InputStream inputStream) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(inputStream);
        int p = (bin.read() << 8) + bin.read();
        String code = null;
        // 其中的 0xefbb、0xfffe、0xfeff、0x5c75这些都是这个文件的前面两个字节的16进制数
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            case 0x5c75:
                code = "ANSI|ASCII";
                break;
            default:
                code = "GBK";
        }
        return code;
    }

    /**
     * 去掉空集合
     *
     * @param mapParam
     * @return java.util.Map<java.lang.String   ,   java.util.List   <   java.util.List   <   java.lang.String>>>
     * @author jichao
     * @date 2019/09/16 15:18
     */
    public static Map<String, List<List<String>>> clearEmptyRows(Map<String, List<List<String>>> mapParam) {
        if (mapParam == null || mapParam.size() == 0) {
            return mapParam;
        }
        Map<String, List<List<String>>> mapRet = new HashMap<>(mapParam.size());
        // 遍历map，将不为空的行保存为新结果
        for (String key : mapParam.keySet()) {
            List<List<String>> listAdd = new ArrayList<>();
            List<List<String>> listData = mapParam.get(key);
            for (List<String> data : listData) {
                if (CollectionUtils.isNotEmpty(data)) {
                    listAdd.add(data);
                }
            }
            mapRet.put(key, listAdd);
        }
        return mapRet;
    }

    /** *
     * 字符串日期格式化
     * @author jichao
     * @date 2019/09/17 18:17
     * @param param
     * @return java.lang.String
     */
    public static String stringDateFormate(String param){
        if (StringUtils.isEmpty(param)) {
            return param;
        }
        Matcher matcher = Pattern.compile(DataSourceConst.SINGLE_DATE_REGEX).matcher(param);
        if (matcher.find()) {
            return matcher.group();
        }
//        try {
//            Date parse = DateFormat.getDateInstance().parse(param);
//            return simpleDateFormat.format(parse);
//        } catch (ParseException e) {
//            throw new CustomException(-1, e.getMessage());
//        }
        return param;
    }

    public static void main(String[] args) {
        System.out.println(stringDateFormate("2019-06-28 00:00:00"));
    }

}
