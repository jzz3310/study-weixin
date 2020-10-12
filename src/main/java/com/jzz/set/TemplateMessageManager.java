package com.jzz.set;

import com.jzz.service.impl.WxServiceImpl;
import com.jzz.talktool.TokenX;
import com.jzz.template.BaseTemplate;
import com.jzz.template.FontColorTemp;
import com.jzz.template.ResumeData;
import com.jzz.template.Template;
import net.sf.json.JSONObject;

/**
 * @author:jzz
 * @date:2020/7/2
 */
public class TemplateMessageManager {

    public static void main(String[] args) {
        sendTemplateMessage();
    }

    //设置行业，每月只能设置一次
    public static void set(){
        String at = WxServiceImpl.getAccessToken();
        String data = "{\n" +
                "    \"industry_id1\":\"1\",\n" +
                "    \"industry_id2\":\"4\"\n" +
                "}";
        String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token="+at;
        String post = TokenX.post(url, data);
        System.out.println(post);
    }

    //获得当前行业设置
    public static void get() {
        String at = WxServiceImpl.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token="+at;
        String s = TokenX.get(url);
        System.out.println(s);
    }

    public static void sendTemplateMessage(){
        String at = WxServiceImpl.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+at;
        Template template = new Template("oZ00Pw-LbMj2110Gk3Elx01Pgl-4","rOJ-LmsbC-87ULX5dgYgIUncGt_IBUCavFxjjVMEPLw");
        ResumeData resumeData = new ResumeData();
        resumeData.setFirst(new FontColorTemp("你有新的反馈信息啦!"));
        resumeData.setCompany(new FontColorTemp("深圳腾讯计算机公司"));
        resumeData.setTime(new FontColorTemp("2020年7月2日"));
        resumeData.setResult(new FontColorTemp("面试通过"));
        resumeData.setRemark(new FontColorTemp("请和本公司人事专员联系"));
        template.setData(resumeData);
        JSONObject jsonObject = JSONObject.fromObject(template);
        System.out.println(jsonObject.toString());
        String post = TokenX.post(url, jsonObject.toString());
        System.out.println(post);

    }

}
