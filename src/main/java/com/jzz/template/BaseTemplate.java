package com.jzz.template;

/**
 * @author:jzz
 * @date:2020/7/2
 */
public abstract class BaseTemplate {
    private String touser;
    private String template_id;

    public BaseTemplate(String touser, String template_id) {
        this.touser = touser;
        this.template_id = template_id;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }
}
