package com.lolluckyman.business.accountassets.service;

import com.lolluckyman.business.accountassets.entity.AccountAssets;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IAccountAssetsService {

    /**
     *获取账户资产分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<AccountAssets> getAccountAssetsPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     * 根据账户资产code查找账户资产
     * @param code 账户资产code
     * @return 账户资产对象
     */
    public AccountAssets getAccountAssetsByCode(String code);

    /**
     * 根据账户code查找账户资产
     * @param accountCode 账户code
     * @return 账户资产对象
     */
    public AccountAssets getAccountAssetsByAccountCode(String accountCode);

    /**
     * 添加账户资产信息
     * @param accountAssets 账户资产对象
     * @return 账户资产对象
     */
    public AccountAssets createAccountAssets(AccountAssets accountAssets);

    /**
     * 根据账户code删除账户资产信息
     * @param accountCode 账户code
     * @return
     */
    public Boolean deleteAccountAssetsByAccountCode(String accountCode);

}
