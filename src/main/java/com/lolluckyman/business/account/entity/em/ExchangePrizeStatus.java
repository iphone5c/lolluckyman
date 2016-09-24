package com.lolluckyman.business.account.entity.em;

import com.lolluckyman.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 兑换状态
 * Created by 魏源 on 2016/9/21.
 */
public enum ExchangePrizeStatus {
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
        for (ExchangePrizeStatus exchangePrizeStatus : ExchangePrizeStatus.values()){
            result.add(NameValue.create(exchangePrizeStatus.toString(),exchangePrizeStatus.toString()));
        }
        return result;
    }
}
