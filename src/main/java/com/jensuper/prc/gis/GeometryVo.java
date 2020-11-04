package com.jensuper.prc.gis;

import lombok.Data;

import java.util.List;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2020/10/26
 */
@Data
public class GeometryVo {
    
    private String type;
    private List<Object> coordinates;
}
