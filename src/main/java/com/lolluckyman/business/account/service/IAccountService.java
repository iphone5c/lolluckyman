package com.lolluckyman.business.account.service;

import com.lolluckyman.business.account.entity.Account;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IAccountService {

    /**
     *获取账户分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<Account> getAccountPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     * 根据账户code查找账户
     * @param code 账户code
     * @return 账户对象
     */
    public Account getAccountByCode(String code);

}
