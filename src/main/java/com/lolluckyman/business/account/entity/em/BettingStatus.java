package com.lolluckyman.business.account.entity.em;

import com.lolluckyman.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 投注状态
 * Created by 魏源 on 2016/9/21.
 */
public enum BettingStatus {
    /**
     * 正常
     */
    正常,
    /**
     * 禁用
     */
    禁用;

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (BettingStatus bettingStatus : BettingStatus.values()){
            result.add(NameValue.create(bettingStatus.toString(),bettingStatus.toString()));
        }
        return result;
    }
}
