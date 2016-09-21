package com.lolluckyman.business.accountassets.controller;

import com.lolluckyman.business.account.entity.Account;
import com.lolluckyman.business.account.service.IAccountService;
import com.lolluckyman.business.accountassets.controller.model.AccountAssetsModel;
import com.lolluckyman.business.accountassets.entity.AccountAssets;
import com.lolluckyman.business.accountassets.service.IAccountAssetsService;
import com.lolluckyman.utils.core.BaseController;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/back/accountassets")
public class AccountAssetsController extends BaseController {

    public Logger log = Logger.getLogger(AccountAssetsController.class);//日志

    @Autowired
    private IAccountAssetsService accountAssetsService;
    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "/getAccountAssetsPageList")
    public Object getAccountAssetsPageList(int pageIndex,int pageSize){
        log.info("获取账户资产列表");
        PageList<AccountAssetsModel> accountAssetsModelPageList = new PageList<>();
        List<AccountAssetsModel> accountAssetsModelList =new ArrayList<>();

        QueryParams queryParams=new QueryParams();
        queryParams.addOrderBy("code",true);
        PageList<AccountAssets> accountAssetsPageList=accountAssetsService.getAccountAssetsPageList(queryParams,pageIndex,pageSize,true);
        for (AccountAssets accountAssets:accountAssetsPageList.getList()){
            AccountAssetsModel accountAssetsModel=new AccountAssetsModel();
            Account account= accountService.getAccountByCode(accountAssets.getAccountCode());
            if (account==null)
                return validationResult(1001,"账户资产信息code："+accountAssets.getAccountCode()+"找不到关联账户信息，accountCode："+accountAssets.getAccountCode());
            accountAssetsModel.setAccount(account);
            accountAssetsModel.setAccountAssets(accountAssets);
            accountAssetsModelList.add(accountAssetsModel);
        }
        accountAssetsModelPageList.setPageCount(accountAssetsPageList.getPageCount());
        accountAssetsModelPageList.setPageIndex(accountAssetsPageList.getPageIndex());
        accountAssetsModelPageList.setPageSize(accountAssetsPageList.getPageSize());
        accountAssetsModelPageList.setTotalSize(accountAssetsPageList.getTotalSize());
        accountAssetsModelPageList.setList(accountAssetsModelList);
        return result(accountAssetsModelPageList);
    }

    @RequestMapping(value = "/getAccountAssetsByCode")
    public Object getAccountAssetsByCode(String code){
        log.info("获取主键为"+code+"的账户资产信息");
        AccountAssets accountAssets=accountAssetsService.getAccountAssetsByCode(code);
        if (accountAssets==null)
            return validationResult(1001,"找不到此"+accountAssets+"的账户资产信息");
        return result(accountAssets);
    }

}
