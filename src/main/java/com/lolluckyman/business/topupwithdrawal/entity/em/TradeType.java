package com.lolluckyman.business.topupwithdrawal.entity.em;

import com.lolluckyman.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 交易类型
 * Created by lenovo on 2016/9/22.
 */
public enum TradeType {
    /**
     * 微信
     */
    微信,
    /**
     * 支付宝
     */
    支付宝;


    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (TradeType tradeType : TradeType.values()){
            result.add(NameValue.create(tradeType.toString(),tradeType.toString()));
        }
        return result;
    }
}
