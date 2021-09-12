package com.jensuper.prc.word;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2021/08/06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableInfo {
    private String tableName;
    private String remark;
    private List<ColumnInfo> columnInfo;

}
