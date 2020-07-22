package com.jzz.talktool;

import com.jzz.util.StreamClose;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @author:jzz
 * @date:2020/6/29
 */
public class TokenX {


    //通过url获取数据
    public static String get(String url) {
        try {
            URL url1 = new URL(url);
            URLConnection urlConnection = url1.openConnection();
            InputStream is = urlConnection.getInputStream();
            byte[] b = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while((len = is.read(b)) != -1) {
                sb.append(new String(b,0,len));
            }

            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //通过url，携带一个数据，获取数据
    public static String post(String url ,String data){
        try {
            URL url1 = new URL(url);
            URLConnection urlConnection = url1.openConnection();
            //要发送数据出去，必须设置未可发送数据状态
            urlConnection.setDoOutput(true);
            //获取输出流
            OutputStream out = urlConnection.getOutputStream();
            out.write(data.getBytes());
            StreamClose.close(out);
            InputStream is = urlConnection.getInputStream();
            byte[] b = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while((len = is.read(b)) != -1) {
                sb.append(new String(b,0,len));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, List<String>> download(String url) {
        try {
            URL url1 = new URL(url);
            URLConnection urlConnection = url1.openConnection();

            Map<String, List<String>> headerFields = urlConnection.getHeaderFields();

            return headerFields;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    
}
