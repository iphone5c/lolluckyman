package com.lolluckyman.business.account.entity.em;

import com.lolluckyman.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 充值状态
 * Created by 魏源 on 2016/9/21.
 */
public enum WithdrawalsStatus {
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
        for (WithdrawalsStatus withdrawalsStatus : WithdrawalsStatus.values()){
            result.add(NameValue.create(withdrawalsStatus.toString(),withdrawalsStatus.toString()));
        }
        return result;
    }
}
