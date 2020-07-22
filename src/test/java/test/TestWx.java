package test;

import com.baidu.aip.ocr.AipOcr;
import com.jzz.button.*;
import com.jzz.entity.BaseMessage;
import com.jzz.entity.TextMessage;
import com.jzz.service.impl.WxServiceImpl;
import com.thoughtworks.xstream.XStream;
import org.json.JSONObject;

import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author:jzz
 * @date:2020/6/27
 */
public class TestWx {

    private final static String APP_ID = "20666534";
    private final static String API_KEY = "HjaWNpiIBAD4k4fSuep9XNrV";
    private final static String SECRET_KEY = "6wKjzmN994dDDMh8TWPpci5EhcD6WUq7";

    public static void main(String[] args) {
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("probability", "true");


        String url = "http://mmbiz.qpic.cn/mmbiz_jpg/c3vibaBVKibrbdr4nyBfcWrMuHicWFMHE2E6JiaotE3Ipe4kI7mxwDAu6HyyrogeWYl3euPRm8hmmXZI6aHZufz0ag/0";
        JSONObject res = client.basicGeneralUrl(url, options);

        System.out.println(res.toString(2));

        // 参数为本地图片二进制数组
//        byte[] file = readImageFile(image);
//        res = client.basicAccurateGeneral(file, options);
//        System.out.println(res.toString(2));



    }

}
