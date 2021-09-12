package com.jensuper.prc.gis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.MapGeometry;
import com.esri.core.geometry.OperatorImportFromJson;
import com.esri.core.geometry.Point;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2020/10/26
 */
public class GeoJsonFormat {
    
    private static String dataStr;

    static {
        try {
            dataStr = FileUtils.readFileToString(new File("F:\\bz\\地图\\dataStr.json"), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main1(String[] args) {
        String data = "{\"序号\":1,\"省份\":\"北京\",\"城市\":\"北京\",\"地名\":\"人民大会堂管理局\",\"经度\":116.398704,\"纬度\":39.911227,\"spatialReference\":{\"wkid\":4326}}";
        String jsonString = "{\"x\":-106.4453583,\"y\":39.11775,\"spatialReference\":{\"wkid\":4326}}";
        MapGeometry mapGeom = OperatorImportFromJson.local().execute(Geometry.Type.Point, data);


        Point geometry = (Point) mapGeom.getGeometry();

        System.out.println(geometry);
    }

    /**
     * {
     * 	"features":[
     *                {
     * 			"geometry":{
     * 				"coordinates":[
     * 					"116.398704",
     * 					"39.911227"
     * 				],
     * 				"type":"Point"
     *            },
     * 			"properties":{
     * 				"name":"人民大会堂管理局"
     *            },
     * 			"type":"Feature"
     *        }
     * 	],
     * 	"type":"FeatureCollection"
     * }
     */
    @Test
    public void test() throws IOException {
        // global
        GlobalVO global = new GlobalVO();
        // features
        List<FeaturesVo> features = new ArrayList<>();

        JSONArray jsonArray = JSONArray.parseArray(dataStr);
        jsonArray.forEach(data->{
            JSONObject jsonObject = JSON.parseObject(String.valueOf(data));
            String name = jsonObject.getString("地名");
            String x = jsonObject.getString("经度");
            String y = jsonObject.getString("纬度");
            // feature
            FeaturesVo feature = new FeaturesVo();
            // properties
            PropertiesVO properties = new PropertiesVO();
            properties.setName(name);

            feature.setProperties(properties);

            // geometry
            GeometryVo geometry = new GeometryVo();
            geometry.setType("Point");
            List<Object> coordinates = new ArrayList<>();
            coordinates.add(x);
            coordinates.add(y);
            geometry.setCoordinates(coordinates);

            feature.setGeometry(geometry);

            features.add(feature);
        });

        global.setFeatures(features);

        String out = JSON.toJSONString(global, true);

        System.out.println(out);
//        FileUtils.write(new File("F:\\bz\\地图\\out.json"), out, "utf-8");
    }
}
