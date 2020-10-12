package com.jzz.template;

/**
 * @author:jzz
 * @date:2020/7/2
 */
public class Template extends BaseTemplate{
    private BaseData data;

    public Template(String touser, String template_id, BaseData data) {
        super(touser, template_id);
        this.data = data;
    }
    public Template(String touser, String template_id) {
        super(touser, template_id);
    }

    public BaseData getData() {
        return data;
    }

    public void setData(BaseData data) {
        this.data = data;
    }

}
