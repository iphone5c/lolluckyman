package com.lolluckyman.business.account.entity.em;

import com.lolluckyman.utils.core.NameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 密码问题
 * Created by 魏源 on 2016/9/21.
 */
public enum Problem {
    /**
     *你的车牌号是多少？
     */
    A,
    /**
     *你的初中同桌的名字？
     */
    B,
    /**
     *你就读的第一所学校的名称？
     */
    C,
    /**
     *你第一次亲吻的对象是谁？
     */
    D,
    /**
     *少年时代你心目中的英雄是谁？
     */
    E,
    /**
     *你最喜欢的休闲运动是什么？
     */
    F,
    /**
     *你最喜欢哪支运动队？
     */
    G,
    /**
     *你最喜欢的运动员是谁？
     */
    H,
    /**
     *你的辆车是什么牌子？
     */
    I;

    public static String convertNameByValue(Problem problem){
        String info="";
        if (problem==Problem.A){
            info="你的车牌号是多少?";
        }else if (problem==Problem.B){
            info="你的初中同桌的名字?";
        }else if (problem==Problem.C){
            info="你就读的第一所学校的名称?";
        }else if (problem==Problem.D){
            info="你第一次亲吻的对象是谁?";
        }else if (problem==Problem.E){
            info="少年时代你心目中的英雄是谁?";
        }else if (problem==Problem.F){
            info="你最喜欢的休闲运动是什么?";
        }else if (problem==Problem.G){
            info="你最喜欢哪支运动队?";
        }else if (problem==Problem.H){
            info="你最喜欢的运动员是谁?";
        }else if (problem==Problem.I){
            info="你的辆车是什么牌子?";
        }
        return info;
    }

    public static List<NameValue> getAllConvertName(){
        List<NameValue> result = new ArrayList<>();
        for (Problem problem : Problem.values()){
            result.add(NameValue.create(problem.toString(),Problem.convertNameByValue(problem)));
        }
        return result;
    }

}
