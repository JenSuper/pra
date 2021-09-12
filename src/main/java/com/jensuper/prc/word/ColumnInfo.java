package com.jensuper.prc.word;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class ColumnInfo {
    private String columnName;
    private String columnType;
    private String columnSize;
    private String remark;
}
