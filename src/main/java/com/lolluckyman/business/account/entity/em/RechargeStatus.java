package com.lolluckyman.business.account.entity.em;

import com.lolluckyman.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 提现状态
 * Created by 魏源 on 2016/9/21.
 */
public enum RechargeStatus {
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
        for (RechargeStatus rechargeStatus : RechargeStatus.values()){
            result.add(NameValue.create(rechargeStatus.toString(),rechargeStatus.toString()));
        }
        return result;
    }
}
