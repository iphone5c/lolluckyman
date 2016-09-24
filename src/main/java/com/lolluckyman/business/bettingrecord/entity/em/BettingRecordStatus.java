package com.lolluckyman.business.bettingrecord.entity.em;

import com.lolluckyman.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 投注状态
 * Created by lenovo on 2016/9/22.
 */
public enum BettingRecordStatus {
    /**
     * 用户撤注
     */
    用户撤注,
    /**
     * 未结算
     */
    未结算,
    /**
     * 已结算
     */
    已结算;

    public static boolean isExit(String name){
        for (BettingRecordStatus bettingRecordStatus:BettingRecordStatus.values()){
            if (name.equals(bettingRecordStatus.name()))
                return true;
        }
        return false;
    }

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (BettingRecordStatus bettingRecordStatus : BettingRecordStatus.values()){
            result.add(NameValue.create(bettingRecordStatus.toString(),bettingRecordStatus.toString()));
        }
        return result;
    }
}
