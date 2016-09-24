package com.lolluckyman.business.exchangeprize.entity.em;

import com.lolluckyman.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 币种类型
 * Created by lenovo on 2016/9/22.
 */
public enum ConsumptionCurrency {
    /**
     * 竞猜币
     */
    竞猜币,
    /**
     * 抚恤金
     */
    抚恤金,
    /**
     * 胜利币
     */
    胜利币;

    public static boolean isExit(String name){
        for (ConsumptionCurrency consumptionCurrency: ConsumptionCurrency.values()){
            if (name.equals(consumptionCurrency.name()))
                return true;
        }
        return false;
    }

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (ConsumptionCurrency consumptionCurrency : ConsumptionCurrency.values()){
            result.add(NameValue.create(consumptionCurrency.toString(),consumptionCurrency.toString()));
        }
        return result;
    }
}
