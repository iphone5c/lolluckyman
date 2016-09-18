package com.lolluckyman.utils.core;

import java.util.Date;
import java.util.List;

/**
 * Created by 魏源 on 2015/7/9 0009.
 */
public class ResponseWebService {
    //响应码
    private String busTypeCode;
    //响应标志
    private boolean busTypeFlag;
    //响应时间
    private Date reDate;
    //备注
    private String remark;
    //异常信息
    private String exceptionMessage;
    //处理成功的条数
    private int totalSize;
    //响应明细
    private List results;
    //响应明细
    private Object[] rs;

    public ResponseWebService() {
    }

    public ResponseWebService(String busTypeCode, boolean busTypeFlag, Date reDate, String remark, String exceptionMessage, int totalSize, List results) {
        this.busTypeCode = busTypeCode;
        this.busTypeFlag = busTypeFlag;
        this.reDate = reDate;
        this.remark = remark;
        this.exceptionMessage = exceptionMessage;
        this.totalSize = totalSize;
        this.results = results;
    }

    public List getResults() {
        return results;
    }

    public void setResults(List results) {
        this.results = results;
    }

    public String getBusTypeCode() {
        return busTypeCode;
    }

    public void setBusTypeCode(String busTypeCode) {
        this.busTypeCode = busTypeCode;
    }

    public boolean isBusTypeFlag() {
        return busTypeFlag;
    }

    public void setBusTypeFlag(boolean busTypeFlag) {
        this.busTypeFlag = busTypeFlag;
    }

    public Date getReDate() {
        return reDate;
    }

    public void setReDate(Date reDate) {
        this.reDate = reDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public Object[] getRs() {
        return rs;
    }

    public void setRs(Object[] rs) {
        this.rs = rs;
    }
}
