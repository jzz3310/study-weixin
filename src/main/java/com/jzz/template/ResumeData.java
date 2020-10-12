package com.jzz.template;

/**
 * @author:jzz
 * @date:2020/7/2
 */
public class ResumeData implements BaseData{
    private FontColorTemp first;
    private FontColorTemp company;
    private FontColorTemp time;
    private FontColorTemp result;
    private FontColorTemp remark;

    public FontColorTemp getFirst() {
        return first;
    }

    public void setFirst(FontColorTemp first) {
        this.first = first;
    }

    public FontColorTemp getCompany() {
        return company;
    }

    public void setCompany(FontColorTemp company) {
        this.company = company;
    }

    public FontColorTemp getTime() {
        return time;
    }

    public void setTime(FontColorTemp time) {
        this.time = time;
    }

    public FontColorTemp getResult() {
        return result;
    }

    public void setResult(FontColorTemp result) {
        this.result = result;
    }

    public FontColorTemp getRemark() {
        return remark;
    }

    public void setRemark(FontColorTemp remark) {
        this.remark = remark;
    }

    public ResumeData(FontColorTemp first, FontColorTemp company, FontColorTemp time, FontColorTemp result, FontColorTemp remark) {
        this.first = first;
        this.company = company;
        this.time = time;
        this.result = result;
        this.remark = remark;
    }

    public ResumeData() {
    }
}
