package com.jensuper.prc.common;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/09/30
 */
public class Common {
    /**
     * 起组件名称
     * 1. 如果集合中存在，则会返回原名称+1
     * @param listName
     * @param widgetName
     * @return java.lang.String
     * @author jichao
     * @date 2019/09/20 16:24
     */
    private String createWidgetName(List<String> listName, String widgetName) {
        // 记录原始名称
        String firstFindName = null;
        while (listName.contains(widgetName)) {
            // 找到已经存在的表名
            String areadyName = null;
            for (String name : listName) {
                if (name.equalsIgnoreCase(widgetName)) {
                    areadyName = name;
                    if (firstFindName == null) {
                        firstFindName = name;
                    }
                }
            }
            // 替换参数中名称
            String str = areadyName.replace(firstFindName, "");
            // 如果为空 名称1 即可
            if (StringUtils.isBlank(str)) {
                widgetName = widgetName + "1";
            } else {
                widgetName = firstFindName + String.valueOf(Integer.parseInt(str) + 1);
            }
        }
        listName.add(widgetName);
        return widgetName;
    }
}
