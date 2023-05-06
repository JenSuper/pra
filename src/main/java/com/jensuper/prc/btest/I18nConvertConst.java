package com.jensuper.prc.btest;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 中英文常量生成
 * </p>
 *
 * @author jichao
 * @date 2021/12/6 18:28
 * @since
 */
public class I18nConvertConst {

    public static final String OPERATOR_RATIO = "operator.ratio";
    String tmpleatePro = "edis.competitor.brand=竞品品牌";

    private String tmplate_const="public static final String %s = \"%s\";";
    private String tmpleate_pre = "operator.app=";


    public static void main11(String[] args) {
        I18nConvertConst i18nConvertConst = new I18nConvertConst();
        i18nConvertConst.convert(Lists.newArrayList(OPERATOR_RATIO));
    }

    public void convert(List<String> originWord) {
        List<String> constList = new ArrayList<>();
        List<String> enList = new ArrayList<>();
        originWord.forEach(word->{
            constList.add(String.format(tmplate_const, word.replace(".", "_"), word));
            enList.add(String.format(tmpleate_pre, word));
        });

        constList.forEach(System.out::println);
        enList.forEach(System.out::println);
    }


    /**
     * 新闻标签top
     */
    private static Map<String, List<String>> newsPositiveTagMap = new LinkedHashMap<>();
    private static Map<String, List<String>> newsNegativeTagMap = new LinkedHashMap<>();

    static {
        newsPositiveTagMap.put("瑞幸咖啡(北京)有限公司", Lists.newArrayList("Operation/Positive", "Finance/Positive", "Other Positive Sides", "Products/Positive", "Production/Positive", "Products/Positive", "Rating Upgrade", "Governance/Positive", "Capital/Positive"));
        newsNegativeTagMap.put("瑞幸咖啡(北京)有限公司", Lists.newArrayList("Finance Alerts", "Operation Alerts", "Management Warnings", "Funding Warnings", "Production Warnings", "Product Warnings", "Asset/Equity Risks", "Market Alerts", "Rating Downgrade", "Credit Warnings", "Legal Disputes", "Investigations by Regulators", "Other Negative Sides"));

        newsPositiveTagMap.put("上海幻电信息科技有限公司", Lists.newArrayList("Operation/Positive", "Finance/Positive", "Products/Positive", "Products/Positive", "Production/Positive", "Rating Upgrade", "Governance/Positive", "Capital/Positive", "Other Positive Sides"));
        newsNegativeTagMap.put("上海幻电信息科技有限公司", Lists.newArrayList("Legal Disputes", "Finance Alerts", "Operation Alerts", "Funding Warnings", "Production Warnings", "Product Warnings", "Asset/Equity Risks", "Market Alerts", "Rating Downgrade", "Credit Warnings", "Investigations by Regulators", "Management Warnings", "Other Negative Sides"));

        newsPositiveTagMap.put("阿里巴巴集团控股有限公司", Lists.newArrayList("Operation/Positive", "Products/Positive", "Capital/Positive", "Finance/Positive", "Products/Positive", "Production/Positive", "Rating Upgrade", "Governance/Positive", "Other Positive Sides"));
        newsNegativeTagMap.put("阿里巴巴集团控股有限公司", Lists.newArrayList("Market Alerts","Operation Alerts",  "Finance Alerts","Funding Warnings", "Production Warnings", "Product Warnings", "Asset/Equity Risks",  "Rating Downgrade", "Credit Warnings", "Legal Disputes", "Investigations by Regulators", "Management Warnings", "Other Positive Sides"));
    }


    public static void main(String[] args) throws IOException {

        List<String> strings = FileUtils.readLines(new File("/Users/chaoji/devlop/project/id.txt"), "utf-8");
        List<String> sqlList = FileUtils.readLines(new File("/Users/chaoji/devlop/project/sql.txt"), "utf-8");
        List<String> out = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            sqlList.forEach(sql->{
                out.add(sql.replace("451", string));
            });

        }
        FileUtils.writeLines(new File("/Users/chaoji/devlop/project/out.txt"), out);
    }
}
