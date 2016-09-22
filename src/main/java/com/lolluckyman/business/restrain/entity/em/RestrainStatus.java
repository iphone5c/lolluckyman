package com.lolluckyman.business.restrain.entity.em;

import com.lolluckyman.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 魏源 on 2016/9/23.
 */
public enum RestrainStatus {
    /**
     * 未启用
     */
    未启用,
    /**
     * 投注中
     */
    投注中,
    /**
     * 禁止竞猜
     */
    禁止投注;

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (RestrainStatus restrainStatus : RestrainStatus.values()){
            result.add(NameValue.create(restrainStatus.toString(),restrainStatus.toString()));
        }
        return result;
    }
}
