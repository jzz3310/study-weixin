package com.jzz.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * @author:jzz
 * @date:2020/6/27
 */
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage{

    @XStreamAlias("MediaId")
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public ImageMessage(Map<String, String> map,String mediaId ) {
        super(map);
        this.setMsgType("image");
        this.mediaId = mediaId;
    }

    @Override
    public String toString() {
        return "ImageMessage{" +
                "mediaId='" + mediaId + '\'' +
                ", toUserName='" + getToUserName() + '\'' +
                ", fromUserName='" + getFromUserName() + '\'' +
                ", createtime='" + getCreatetime() + '\'' +
                ", msgType='" + getMsgType() + '\'' +
                '}';
    }
}
