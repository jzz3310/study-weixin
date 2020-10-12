package com.jzz.service.impl;

import com.baidu.aip.ocr.AipOcr;
import com.jzz.entity.*;
import com.jzz.service.WxService;
import com.jzz.talktool.Joke;
import com.jzz.talktool.News;
import com.jzz.talktool.TokenX;
import com.jzz.user.AccessToken;
import com.thoughtworks.xstream.XStream;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author:jzz
 * @date:2020/6/26
 */
@Service
public class WxServiceImpl implements WxService {
    public static final String TOKEN = "userId";
    private static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static final String APPID = "wx63847d5168ddd228";
    private static final String APPSECRET = "6ebc80e1508163b08bc95d0a723e3a44";
    private final static String APP_ID = "20666534";
    private final static String API_KEY = "HjaWNpiIBAD4k4fSuep9XNrV";
    private final static String SECRET_KEY = "6wKjzmN994dDDMh8TWPpci5EhcD6WUq7";
    private static AccessToken at;

    //检查是否正确
    @Override
    public boolean check(String timestamp, String nonce, String signature) {
//        将token、timestamp、nonce三个参数进行字典序排序
        String[] strs = new String[]{TOKEN,timestamp,nonce};
        Arrays.sort(strs);
//        2）将三个参数字符串拼接成一个字符串进行sha1加密
        String str = strs[0]+strs[1]+strs[2];
//        3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        str = encrypt(str);
        return signature.equals(str);
    }

    //通过一个输入流，对数据进行解析，返回一个map映射键值对
    @Override
    public Map<String, String> postRequest(InputStream input)  {
        HashMap<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            //读取输入流，获取文档对象
            document = reader.read(input);
            //根据文档对象获取根节点
            Element root = document.getRootElement();
            //获取根节点下的子节点集合
            List<Element> elements = root.elements();
            //将节点信息存入map中
            for (Element element : elements) {
                map.put(element.getName(),element.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    //通过前端发送消息，进行处理，返回数据
    @Override
    public String getResponse(Map<String, String> map) {
        BaseMessage msg = null;
        String msgType = map.get("MsgType");
        switch (msgType){
            case "text": //文本消息
                msg = dealTextMessage(map);
                break;
            case "image":
                msg = dealImage(map);
                break;
            case "voice":
                break;
            case "shortvideo":
                break;
            case "location":
                break;
            case "link":
                break;
            case "event":
                msg = dealEvent(map);
                break;
            default:
                break;
        }
        //把消息对象处理成xml
        if(msg != null){
            return beanToXml(msg);
        }
        return null;
    }


    //图片识别，识别图中文字
    private BaseMessage dealImage(Map<String, String> map) {
        String picUrl = map.get("PicUrl");
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("probability", "true");

        org.json.JSONObject res = client.basicGeneralUrl(picUrl, options);
        JSONObject jsonObject = JSONObject.fromObject(res.toString());
        JSONArray words_result = jsonObject.getJSONArray("words_result");
        StringBuilder sb = new StringBuilder();
        Iterator<JSONObject> iterator = words_result.iterator();
        while (iterator.hasNext()) {
            JSONObject next = iterator.next();
            sb.append(next.getString("words"));
        }



        return new TextMessage(map,sb.toString());
    }

    private BaseMessage dealEvent(Map<String, String> map) {
        System.out.println("点击按钮");
        String event = map.get("Event");
        switch (event) {
            case "CLICK":
                return dealClick(map);
            case "VIEW":
                return dealView(map);
            default:
                break;
        }

        return null;
    }

    private BaseMessage dealView(Map<String, String> map) {
        return null;
    }

    private BaseMessage dealClick(Map<String, String> map) {
        String eventKey = map.get("EventKey");
        switch (eventKey) {
            case "1":
                //处理第一个单击按钮
                return new TextMessage(map,"你单击了第一个按钮");
            case "32":
                break;
            default:
                break;
        }
        return null;
    }

    //将对象转换成xml，用来发给微信端
    private String beanToXml(BaseMessage msg) {
        XStream stream = new XStream();
        stream.processAnnotations(TextMessage.class);
        stream.processAnnotations(ImageMessage.class);
        stream.processAnnotations(MusicMessage.class);
        stream.processAnnotations(NewsMessage.class);
        stream.processAnnotations(VideoMessage.class);
        stream.processAnnotations(VoiceMessage.class);
        String s = stream.toXML(msg);
        return s;
    }

    //当用户发送文本消息时，处理
    private BaseMessage dealTextMessage(Map<String, String> map) {
        if(map.get("Content").contains("笑话")){
            return new TextMessage(map, Joke.getNewJoke());
        }else if(map.get("Content").contains("新闻")){
            Article aNew = News.getNew();
            ArrayList<Article> articles = new ArrayList<>();
            articles.add(aNew);
            return new NewsMessage(map,articles.size()+"",articles);
        }
        return null;
    }

    //进行加密
    private static String encrypt(String str) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("sha1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] digest = md.digest(str.getBytes());
        char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(chars[(b>>4)&15]);
            sb.append(chars[b&15]);
        }

        return sb.toString();
    }

    //发送请求获得token
    private static String getToken() {
        String url = GET_TOKEN_URL.replace("APPID",APPID).replace("APPSECRET",APPSECRET);
        String tokenStr = TokenX.get(url);

        JSONObject jsonObject = JSONObject.fromObject(tokenStr);
        String accessToken = jsonObject.getString("access_token");
        String expiresIn = jsonObject.getString("expires_in");
        at = new AccessToken(accessToken, expiresIn);
        return tokenStr;
    }

    //从token中获取Accesstoken
    public static String getAccessToken() {
        if(at == null || at.isExpired()){
            getToken();
        }
        return at.getAccessToke();
    }
}
