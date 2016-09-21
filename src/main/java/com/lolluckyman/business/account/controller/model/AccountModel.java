package com.lolluckyman.business.account.controller.model;

import com.lolluckyman.business.account.entity.Account;

/**
 * Created by 魏源 on 2016/9/22.
 */
public class AccountModel {
    //问题
    private String problem;
    //账户信息
    private Account account;

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
