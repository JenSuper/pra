package com.jensuper.prc.ffmpeg;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2020/07/30
 */
public class RtspPlayDemo {

    public static void main(String[] args){
        RtspPlayDemo convertVideoPakcet = new RtspPlayDemo();

        // rtsp地址
        String rtspUrl = "rtsp://admin:admin12345@172.28.101.103:554";
        // Nginx rtmp地址
        // 1935 对应Nginx配置文件中rtmp所监听的端口
        // live 对应Nginx配置文件中rtmp下application的值
        // rtmpStream 对应播放页面中16行参数stream的值
        String nginxRtmpUrl = "rtmp://172.24.103.102:19999/myapp/rtmpStream";

        convertVideoPakcet.pushVideoAsRTSP(rtspUrl, nginxRtmpUrl);
    }

    public Integer pushVideoAsRTSP(String rtspUrl, String nginxRtmpUrl){
        int flag = -1;
        try {
            // ffmpeg 已经在系统环境变量中配置好了
            String command = "ffmpeg ";
            command += " -i \"" + rtspUrl + "\"";
            command += " -vcodec copy -acodec copy -f flv -s 800x600 " + nginxRtmpUrl;
            System.out.println("ffmpeg推流命令：" + command);

            Process process = Runtime.getRuntime().exec(command);
            BufferedReader br= new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println("视频推流信息[" + line + "]");
            }
            flag = process.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
