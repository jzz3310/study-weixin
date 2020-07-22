package com.jzz.template;

/**
 * @author:jzz
 * @date:2020/7/2
 */
public class FontColorTemp {
    private String value;
    private String color = "#173177";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public FontColorTemp(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public FontColorTemp(String value) {
        this.value = value;
    }
}
