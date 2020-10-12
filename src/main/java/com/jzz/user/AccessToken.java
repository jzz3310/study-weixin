package com.jzz.user;

/**
 * @author:jzz
 * @date:2020/6/29
 */

public class AccessToken {
    private String accessToke;
    private Long expireTime;

    public String getAccessToke() {
        return accessToke;
    }

    public void setAccessToke(String accessToke) {
        this.accessToke = accessToke;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public AccessToken(String accessToke, String expireIn) {
        this.accessToke = accessToke;
        this.expireTime = System.currentTimeMillis() + Integer.parseInt(expireIn)*1000;
    }

    //判断token是否过期
    public boolean isExpired(){
        return expireTime < System.currentTimeMillis();
    }
}
