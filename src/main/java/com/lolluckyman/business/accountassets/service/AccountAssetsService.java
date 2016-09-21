package com.lolluckyman.business.accountassets.service;

import com.lolluckyman.business.account.entity.Account;
import com.lolluckyman.business.account.service.IAccountService;
import com.lolluckyman.business.accountassets.dao.IAccountAssetsDao;
import com.lolluckyman.business.accountassets.entity.AccountAssets;
import com.lolluckyman.business.admin.dao.IAdminDao;
import com.lolluckyman.business.admin.entity.Admin;
import com.lolluckyman.business.admin.service.IAdminService;
import com.lolluckyman.business.codebuilder.ICodeBuilder;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseService;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("accountAssetsService")
public class AccountAssetsService extends BaseService implements IAccountAssetsService {

    public Logger log = Logger.getLogger(AccountAssetsService.class);//日志

    @Autowired
    private IAccountAssetsDao accountAssetsDao;
    @Autowired
    private ICodeBuilder codeBuilder;
    @Autowired
    private IAccountService accountService;

    /**
     *获取账户资产分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<AccountAssets> getAccountAssetsPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return accountAssetsDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     * 根据账户资产code查找账户资产
     * @param code 账户资产code
     * @return 账户资产对象
     */
    @Override
    public AccountAssets getAccountAssetsByCode(String code) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("根据code查找账户资产信息，code不能为空");
        return accountAssetsDao.getObject(code,true);
    }

    /**
     * 根据账户code查找账户资产
     * @param accountCode 账户code
     * @return 账户资产对象
     */
    @Override
    public AccountAssets getAccountAssetsByAccountCode(String accountCode) {
        if (LolUtils.isEmptyOrNull(accountCode))
            throw new IllegalArgumentException("根据账户code查找账户资产信息，accountCode不能为空");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("accountCode",accountCode);
        List<AccountAssets> accountAssetsList = accountAssetsDao.queryList(queryParams,0,-1,true);
        return (accountAssetsList!=null&&accountAssetsList.size()>0)?accountAssetsList.get(0):null;
    }

    /**
     * 添加账户资产信息
     * @param accountAssets 账户资产对象
     * @return 账户资产对象
     */
    @Override
    public AccountAssets createAccountAssets(AccountAssets accountAssets) {
        if (accountAssets==null)
            throw new IllegalArgumentException("新增账户资产时，账户资产对象不能为空或者null");
        if (LolUtils.isEmptyOrNull(accountAssets.getAccountCode()))
            throw new IllegalArgumentException("新增账户资产时，账户编号不能为空或者null");
        Account account= accountService.getAccountByCode(accountAssets.getAccountCode());
        if (account==null)
            throw new IllegalArgumentException("新增账户资产时，找不到关联的账户信息，accountCode："+accountAssets.getAccountCode());
        accountAssets.setCode(codeBuilder.getAccountAssetsCode());
        int info=accountAssetsDao.insertObject(accountAssets);
        return info>0?accountAssets:null;
    }

    /**
     * 根据账户code删除账户资产信息
     * @param accountCode 账户code
     * @return
     */
    @Override
    public Boolean deleteAccountAssetsByAccountCode(String accountCode) {
        if (LolUtils.isEmptyOrNull(accountCode))
            throw new IllegalArgumentException("根据账户code删除账户资产信息，accountCode不能为空");
        AccountAssets accountAssets=this.getAccountAssetsByAccountCode(accountCode);
        if (accountAssets!=null){
            int info=accountAssetsDao.deleteObject(accountAssets.getCode());
            return info>0?true:false;
        }
        return true;
    }
}
