package com.jzz.controller;

import com.jzz.service.WxService;
import com.jzz.util.StreamClose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author:jzz
 * @date:2020/6/26
 * 控制层
 * 接受以及响应微信端传来的请求
 */
@RestController
public class TestController {

    @Autowired
    private WxService wxService;

    @GetMapping("wx")
    public String test(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");  //微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
        response.setCharacterEncoding("UTF-8"); //在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
//        signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
//        timestamp	时间戳 nonce	随机数 echostr	随机字符串
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        String echostr = request.getParameter("echostr");//随机字符串

        if(wxService.check(timestamp,nonce,signature)){
            PrintWriter out = response.getWriter();
            out.println(echostr);
            StreamClose.close(out);
        }else {
            System.out.println("接入失败");
        }
        return echostr;
    }

    @PostMapping("wx")
    public void talk(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String,String> map = wxService.postRequest(request.getInputStream());
        System.out.println(map);
        String resxml = wxService.getResponse(map);
        PrintWriter out = response.getWriter();
        out.println(resxml);
        StreamClose.close(out);
    }

    public static void main(String[] args) {
        String s = "3.4";
        int ceil = (int)Math.ceil(new Double(s));
        System.out.println(ceil);
    }
}
