package com.lolluckyman.business.accountassets.controller.model;

import com.lolluckyman.business.account.entity.Account;
import com.lolluckyman.business.accountassets.entity.AccountAssets;

/**
 * 账户资产列表视图model
 * Created by 魏源 on 2016/9/21.
 */
public class AccountAssetsModel {
    //账户信息
    private Account account;
    //账户资产信息
    private AccountAssets accountAssets;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AccountAssets getAccountAssets() {
        return accountAssets;
    }

    public void setAccountAssets(AccountAssets accountAssets) {
        this.accountAssets = accountAssets;
    }
}
