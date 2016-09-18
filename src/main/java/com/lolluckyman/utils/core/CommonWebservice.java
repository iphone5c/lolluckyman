package com.lolluckyman.utils.core;

import java.util.Date;
import java.util.List;

public class CommonWebservice {

    public ResponseWebService success(String busTypeCode,String remark,int totalSize,List results){
        ResponseWebService result= new ResponseWebService();
        result.setBusTypeCode(busTypeCode);
        result.setBusTypeFlag(true);
        result.setReDate(new Date());
        result.setRemark(remark);
        result.setTotalSize(totalSize);
        result.setResults(results);
        return result;
    }

    public ResponseWebService failer(String busTypeCode,String remark,String exceptionMessage){
        ResponseWebService result= new ResponseWebService();
        result.setBusTypeCode(busTypeCode);
        result.setBusTypeFlag(false);
        result.setReDate(new Date());
        result.setRemark(remark);
        result.setExceptionMessage(exceptionMessage);
        return result;
    }
}
