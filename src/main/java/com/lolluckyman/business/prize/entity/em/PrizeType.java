package com.lolluckyman.business.prize.entity.em;

import com.lolluckyman.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/9/22.
 */
public enum PrizeType {
    /**
     * 实物
     */
    实物,
    /**
     * 虚拟
     */
    虚拟;

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (PrizeType prizeType : PrizeType.values()){
            result.add(NameValue.create(prizeType.toString(),prizeType.toString()));
        }
        return result;
    }
}
