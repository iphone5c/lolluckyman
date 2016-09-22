package com.lolluckyman.business.playrecord.entity.em;

import com.lolluckyman.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 玩法
 * Created by lenovo on 2016/9/22.
 */
public enum Play {
    /**
     * 总局输赢
     */
    总局输赢,
    /**
     * 单局输赢
     */
    单局输赢,
    /**
     * 比分
     */
    比分,
    /**
     * 一血
     */
    一血,
    /**
     * 一塔
     */
    一塔,
    /**
     * 一小龙
     */
    一小龙,
    /**
     * 第一亚龙属性
     */
    第一亚龙属性,
    /**
     * 小龙数量
     */
    小龙数量,
    /**
     * 一大龙
     */
    一大龙,
    /**
     * 一峡谷先锋
     */
    一峡谷先锋,
    /**
     * 单局四杀
     */
    单局四杀,
    /**
     * 单局五杀
     */
    单局五杀,
    /**
     * 率先十杀
     */
    率先十杀,
    /**
     * 单局超神
     */
    单局超神,
    /**
     * 一水晶
     */
    一水晶,
    /**
     * 单方人头数单双
     */
    单方人头数单双,
    /**
     * 总人头数单双
     */
    总人头数单双;

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (Play play : Play.values()){
            result.add(NameValue.create(play.toString(),play.toString()));
        }
        return result;
    }
}
