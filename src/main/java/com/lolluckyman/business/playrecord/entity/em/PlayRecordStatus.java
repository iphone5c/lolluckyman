package com.lolluckyman.business.playrecord.entity.em;

import com.lolluckyman.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 玩法
 * Created by lenovo on 2016/9/22.
 */
public enum PlayRecordStatus {
    /**
     * 未处理
     */
    未处理,
    /**
     * 已处理
     */
    已处理;

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (PlayRecordStatus playRecordStatus : PlayRecordStatus.values()){
            result.add(NameValue.create(playRecordStatus.toString(),playRecordStatus.toString()));
        }
        return result;
    }
}
