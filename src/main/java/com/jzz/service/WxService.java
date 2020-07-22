package com.jzz.service;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Map;

/**
 * @author:jzz
 * @date:2020/6/26
 */
public interface WxService {


    boolean check(String timestamp, String nonce, String signature);

    Map<String, String> postRequest(InputStream input);

    String getResponse(Map<String, String> map);
}
