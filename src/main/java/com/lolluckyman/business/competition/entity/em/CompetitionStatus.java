package com.lolluckyman.business.competition.entity.em;

import com.lolluckyman.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 比赛状态
 * Created by lenovo on 2016/9/22.
 */
public enum CompetitionStatus {
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
        for (CompetitionStatus competitionStatus : CompetitionStatus.values()){
            result.add(NameValue.create(competitionStatus.toString(),competitionStatus.toString()));
        }
        return result;
    }
}
