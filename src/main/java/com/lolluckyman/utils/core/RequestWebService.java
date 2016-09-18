package com.lolluckyman.utils.core;

import java.util.Date;

/**
 * Created by 魏源 on 2015/7/9 0009.
 */
public class RequestWebService {
    //交易码
    private String busTypeCode;
    //交易名称
    private String busTypeName;
    //渠道
    private String channel;
    //机构代码
    private String dept;
    //发起人
    private String author;
    //发起时间
    private Date busDate;
    //请求明细
    private Object[][] params;

    public RequestWebService() {
    }

    public String getBusTypeCode() {
        return busTypeCode;
    }

    public void setBusTypeCode(String busTypeCode) {
        this.busTypeCode = busTypeCode;
    }

    public String getBusTypeName() {
        return busTypeName;
    }

    public void setBusTypeName(String busTypeName) {
        this.busTypeName = busTypeName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getBusDate() {
        return busDate;
    }

    public void setBusDate(Date busDate) {
        this.busDate = busDate;
    }

    public Object[][] getParams() {
        return params;
    }

    public void setParams(Object[][] params) {
        this.params = params;
    }
}
