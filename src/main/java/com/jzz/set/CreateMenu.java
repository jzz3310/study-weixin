package com.jzz.set;

import com.jzz.button.*;
import com.jzz.service.impl.WxServiceImpl;
import com.jzz.talktool.TokenX;
import net.sf.json.JSONObject;

/**
 * @author:jzz
 * @date:2020/6/30
 */
public class CreateMenu {

    public static void main(String[] args) {
        Button button = new Button();

        button.getButton().add(new ClickButton("一级点击","1"));
        button.getButton().add(new ViewButton("一级跳转","http://www.baidu.com"));

        SubButton sb = new SubButton("有子菜单");
        sb.getSub_button().add(new PhotoOrAlbumButton("传图","31"));
        sb.getSub_button().add(new ClickButton("点击","32"));
        sb.getSub_button().add(new ViewButton("网易新闻","http://news.163.com"));
        button.getButton().add(sb);

        JSONObject jsonObject = JSONObject.fromObject(button);
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", WxServiceImpl.getAccessToken());
        String post = TokenX.post(url, jsonObject.toString());
        System.out.println(post);
    }
}
