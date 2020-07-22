package com.jzz.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * @author:jzz
 * @date:2020/6/27
 */
@XStreamAlias("xml")
public class MusicMessage extends BaseMessage{
    private Music music;

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public MusicMessage(Map<String, String> map, Music music) {
        super(map);
        this.setMsgType("music");
        this.music = music;
    }
}
