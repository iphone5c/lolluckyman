package com.lolluckyman.business.bettingrecord.entity.em;

import com.lolluckyman.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 投注结果
 * Created by lenovo on 2016/9/22.
 */
public enum BettingRecordResult {
    /**
     * 等待结果
     */
    等待结果,
    /**
     * 输
     */
    输,
    /**
     * 赢
     */
    赢,
    /**
     * 平
     */
    平;


    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (BettingRecordResult bettingRecordResult : BettingRecordResult.values()){
            result.add(NameValue.create(bettingRecordResult.toString(),bettingRecordResult.toString()));
        }
        return result;
    }
}
