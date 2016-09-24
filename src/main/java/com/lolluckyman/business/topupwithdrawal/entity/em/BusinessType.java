package com.lolluckyman.business.topupwithdrawal.entity.em;

import com.lolluckyman.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务类型
 * Created by lenovo on 2016/9/22.
 */
public enum BusinessType {
    /**
     * 充值
     */
    充值,
    /**
     * 提现
     */
    提现;

    public static boolean isExit(String name){
        for (BusinessType businessType:BusinessType.values()){
            if (name.equals(businessType.name()))
                return true;
        }
        return false;
    }

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (BusinessType businessType : BusinessType.values()){
            result.add(NameValue.create(businessType.toString(),businessType.toString()));
        }
        return result;
    }
}
