package com.jensuper.prc.common.excel;

import java.util.Arrays;
import java.util.List;

/**
 * @author jichao
 * @version V1.0
 * @description: 数据源常量
 * @date 2019/09/03
 */
public class DataSourceConst {

    /**
     * 文件大小
     */
    public static final Integer IMAGE_SIZE = 104857600;

    /**
     * xls文件格式
     */
    public static final String XLS = "xls";
    /**
     * xlsx文件格式
     */
    public static final String XLSX = "xlsx";
    /**
     * csv文件格式
     */
    public static final String CSV = "csv";

    /**
     * 日期正则
     * 1. 年月日时分秒
     * 2. 年月日时分
     * 3. 年月日时
     * 4  年月日
     * 5. 时分秒  ^[0-9]{2}:[0-9]{2}:[0-9]{2}$ 日期格式无法入库
     * 6. 时分 ^[0-9]{2}:[0-9]{2}$ 日期格式无法入库
     */
    public static List<String> dateRegexList =
            Arrays.asList("^2[0-9]{3}[-/年]{1}[0-9]{1,2}[-/月]{1}[0-9]{1,2}日?\\s+[0-9]{2}:[0-9]{2}:[0-9]{2}$",
                    "^2[0-9]{3}[-/年]{1}[0-9]{1,2}[-/月]{1}[0-9]{1,2}日?\\s+[0-9]{2}:[0-9]{2}$",
                    "^2[0-9]{3}[-/年]{1}[0-9]{1,2}[-/月]{1}[0-9]{1,2}日?\\s+[0-9]{2}:$",
                    "^2[0-9]{3}[-/年]{1}[0-9]{1,2}[-/月]{1}[0-9]{1,2}日?$");

    /**
     * 单个日期正则 2019-09-18 用来格式化H2 日期
     */
    public static final String SINGLE_DATE_REGEX = "2[0-9]{3}[-]{1}[0-9]{1,2}[-]{1}[0-9]{1,2}";

    /**
     * 金额正则
     */
    public static final String DECIMALRREGEX = "^-?[0-9.，,]+";
    /**
     * 纯数字正则
     */
    public static final String INTREGEX = "^-?[0-9]+";
    /**
     * 创建表时类型验证行数
     */
    public static final int ROWS = 10;
}
