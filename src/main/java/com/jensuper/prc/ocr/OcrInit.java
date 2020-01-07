package com.jensuper.prc.ocr;

import com.jensuper.prc.ocr.util.Base64Util;
import com.jensuper.prc.ocr.util.FileUtil;
import com.jensuper.prc.ocr.util.HttpUtil;
import com.sun.imageio.plugins.common.ImageUtil;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/08/27
 */
public class OcrInit {

    @Test
    public void test() throws UnsupportedEncodingException {
        // 请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");
        // 请求体
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        // HttpEntity
        HttpEntity<MultiValueMap> httpEntity = new HttpEntity<>(httpHeaders,multiValueMap);
        // URL
        String url = Const.url + "?access_token=" + Const.accessToken;
        
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> responseEntity = template.postForEntity(url, httpEntity, String.class);
        System.out.println(responseEntity.getBody());
    }

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static void main(String[] args) {
        // 通用识别url
        String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/general";
        // 本地图片路径
        String filePath = Const.imagePath;
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String accessToken = Const.accessToken;
            String result = HttpUtil.post(otherHost, accessToken, params);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
