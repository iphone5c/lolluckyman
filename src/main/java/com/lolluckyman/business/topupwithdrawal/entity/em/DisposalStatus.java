package com.lolluckyman.business.topupwithdrawal.entity.em;

import com.lolluckyman.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 充值提现状态
 * Created by lenovo on 2016/9/22.
 */
public enum DisposalStatus {
    /**
     * 等待处理
     */
    等待处理,
    /**
     * 正在处理
     */
    正在处理,
    /**
     * 处理完成
     */
    处理完成,
    /**
     * 用户撤销
     */
    用户撤销;


    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (DisposalStatus disposalStatus : DisposalStatus.values()){
            result.add(NameValue.create(disposalStatus.toString(),disposalStatus.toString()));
        }
        return result;
    }
}
