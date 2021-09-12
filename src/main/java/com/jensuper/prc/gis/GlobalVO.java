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
public class GlobalVO {
    /**
     * {
     *   "type": "Feature",
     *   "geometry": {
     *     "type": "Point",
     *     "coordinates": [125.6, 10.1]
     *   },
     *   "properties": {
     *     "name": "Dinagat Islands"
     *   }
     * }
     */

    private String type;
    private List<FeaturesVo> features;

    public GlobalVO() {
        this.type = "FeatureCollection";
    }

}
