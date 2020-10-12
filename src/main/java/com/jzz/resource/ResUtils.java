package com.jzz.resource;

import com.jzz.service.impl.WxServiceImpl;
import com.jzz.talktool.TokenX;
import com.jzz.util.StreamClose;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author:jzz
 * @date:2020/7/2
 */
public class ResUtils {

    //上传文件 path:上传文件路径 type:上传文件类型
    //增加临时素材
    public static String upload(String path, String type) {
        File file = new File(path);
        String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
        url = url.replace("ACCESS_TOKEN", WxServiceImpl.getAccessToken()).replace("TYPE", type);
        try {
            URL urlObj = new URL(url);
            //强制转成安全链接
            HttpsURLConnection connection = (HttpsURLConnection)urlObj.openConnection();
            //设置连接的信息
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            //设置请求头
            connection.setRequestProperty("Connection","Keep-Alive");
            connection.setRequestProperty("Charset","utf-8");
            //设置数据的边界
            String boundary = "-----"+System.currentTimeMillis();
            connection.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);
            //获取输出流
            OutputStream out = connection.getOutputStream();
            //获取文件输入流
            FileInputStream is = new FileInputStream(file);
            //第一部分：头部信息
            StringBuilder sb = new StringBuilder();
            sb.append("--");
            sb.append(boundary);
            sb.append("\r\n");
            sb.append("Content-Disposition:form-data;name=\"media\";filename=\""+file.getName()+"\"");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            out.write(sb.toString().getBytes());
            //第二部分：文件内容
            byte[] b = new byte[1024];
            int len;
            while((len = is.read(b)) != -1){
                out.write(b,0,len);
            }
            //第三部分：
            String foot = "\r\n--"+boundary+"--\r\n";
            out.write(foot.getBytes());
            StreamClose.close(out);
            //读取数据
            InputStream is2 = connection.getInputStream();
            StringBuilder resp = new StringBuilder();
            while((len = is2.read(b)) != -1) {
                resp.append(new String(b,0,len));
            }
            return resp.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, List<String>> download(String media_id) {
        String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
        url = url.replace("ACCESS_TOKEN", WxServiceImpl.getAccessToken()).replace("MEDIA_ID", media_id);
        Map<String, List<String>> download = TokenX.download(url);
        return download;
    }

    public static void main(String[] args) {
//        String path = "C:\\Users\\asus\\Desktop\\8617.jpg";
//
//        String image = upload(path, "image");
//        System.out.println("image = " + image);

        //nmjaK18tLNWt-IEZaOPIaw-u4FiANcswRQADBGCayx4Dw7UCaQkmDhrRin0LB3Cj

        Map<String, List<String>> map = download("nmjaK18tLNWt-IEZaOPIaw-u4FiANcswRQADBGCayx4Dw7UCaQkmDhrRin0LB3Cj");

        Set<String> strings = map.keySet();
        for (String string : strings) {
            System.out.println(string+"="+map.get(string).get(0));
        }


    }
}
