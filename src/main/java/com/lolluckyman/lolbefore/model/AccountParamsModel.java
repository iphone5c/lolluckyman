package com.lolluckyman.lolbefore.model;

import com.lolluckyman.business.account.entity.em.Problem;

/**
 * Created by lenovo on 2016/9/29.
 */
public class AccountParamsModel {
    private String passwordProblem;

    public Problem getPasswordProblem() {
        Problem result=null;
        for (Problem temp:Problem.values()){
            if (temp.name().equals(this.passwordProblem)){
                result=temp;
            }
        }
        if (result==null)
            throw new IllegalArgumentException("密码问题超出规定范围值");
        return result;
    }

    public void setPasswordProblem(String passwordProblem) {
        this.passwordProblem = passwordProblem;
    }
}
