package com.lolluckyman.business.account.controller;

import com.lolluckyman.business.account.controller.model.AccountModel;
import com.lolluckyman.business.account.entity.Account;
import com.lolluckyman.business.account.entity.em.Problem;
import com.lolluckyman.business.account.service.IAccountService;
import com.lolluckyman.utils.cmd.LolUtils;
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
@RequestMapping(value = "/back/account")
public class AccountController extends BaseController {

    public Logger log = Logger.getLogger(AccountController.class);//日志

    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "/getAccountPageList")
    public Object getAccountPageList(int pageIndex,int pageSize){
        log.info("获取账户信息列表");
        PageList<AccountModel> accountModelPageList =new PageList<>();
        List<AccountModel> accountModelList = new ArrayList<>();
        QueryParams queryParams=new QueryParams();
        queryParams.addOrderBy("code",true);
        PageList<Account> accountPageList= accountService.getAccountPageList(queryParams,pageIndex,pageSize,true);
        for (Account account:accountPageList.getList()){
            AccountModel accountModel=new AccountModel();
            accountModel.setProblem(Problem.convertNameByValue(account.getPasswordProblem()));
            accountModel.setAccount(account);
            accountModelList.add(accountModel);
        }
        accountModelPageList.setTotalSize(accountPageList.getTotalSize());
        accountModelPageList.setPageSize(accountPageList.getPageSize());
        accountModelPageList.setPageIndex(accountPageList.getPageIndex());
        accountModelPageList.setPageCount(accountPageList.getPageCount());
        accountModelPageList.setList(accountModelList);
        return result(accountModelPageList);
    }

    @RequestMapping(value = "/getAccountByCode")
    public Object getAccountByCode(String code){
        log.info("获取主键为"+code+"的账户信息");
        Account account=accountService.getAccountByCode(code);
        if (account==null)
            return validationResult(1001,"找不到此"+code+"的账户信息");
        return result(account);
    }

}
