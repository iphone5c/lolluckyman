package com.lolluckyman.business.exchangeprize.entity.em;

import com.lolluckyman.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 兑换状态状态
 * Created by lenovo on 2016/9/22.
 */
public enum ExchangeStatus {
    /**
     * 用户撤注
     */
    用户撤注,
    /**
     * 等待处理
     */
    等待处理,
    /**
     * 正在处理
     */
    正在处理,
    /**
     * 已结算
     */
    处理完成;

    public static boolean isExit(String name){
        for (ExchangeStatus exchangeStatus: ExchangeStatus.values()){
            if (name.equals(exchangeStatus.name()))
                return true;
        }
        return false;
    }

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (ExchangeStatus bettingRecordStatus : ExchangeStatus.values()){
            result.add(NameValue.create(bettingRecordStatus.toString(),bettingRecordStatus.toString()));
        }
        return result;
    }
}
