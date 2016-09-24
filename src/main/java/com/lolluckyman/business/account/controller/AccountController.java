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
        if (LolUtils.isEmptyOrNull(code))
            return validationResult(1001,"查询账户信息，Code不能为空");
        Account account=accountService.getAccountByCode(code);
        if (account==null)
            return validationResult(1001,"找不到此"+code+"的账户信息");
        return result(account);
    }

    /**
     * 指定用户重置登录密码
     * @param code
     * @return
     */
    @RequestMapping(value = "/resetAccountPassword")
    public Object resetAccountPassword(String code){
        if (LolUtils.isEmptyOrNull(code))
            return validationResult(1001,"指定用户重置登录密码时，code不能为空或null");
        boolean flag=accountService.resetAccountPassword(code);
        if (flag){
            return result("重置成功");
        }else{
            return validationResult(1001,"重置失败");
        }
    }

    /**
     * 指定用户重置取款密码
     * @param code
     * @return
     */
    @RequestMapping(value = "/resetAccountWithdrawalsPassword")
    public Object resetAccountWithdrawalsPassword(String code){
        if (LolUtils.isEmptyOrNull(code))
            return validationResult(1001,"指定用户重置取款密码时，code不能为空或null");
        boolean flag=accountService.resetAccountWithdrawalsPassword(code);
        if (flag){
            return result("重置成功");
        }else{
            return validationResult(1001,"重置失败");
        }
    }

    @RequestMapping(value = "/disableAccount")
    public Object disableAccount(String code){
        log.info("禁用用户code："+code);
        if (LolUtils.isEmptyOrNull(code))
            return validationResult(1001,"禁用账户信息，Code不能为空");
        boolean flag=accountService.disableAccount(code);
        if (flag){
            return result("禁用成功");
        }else {
            return validationResult(1001,"禁用失败");
        }
    }

    @RequestMapping(value = "/removeDisableAccount")
    public Object removeDisableAccount(String code){
        log.info("解除禁用用户code："+code);
        if (LolUtils.isEmptyOrNull(code))
            return validationResult(1001,"解除禁用账户信息，Code不能为空");
        boolean flag=accountService.removeDisableAccount(code);
        if (flag){
            return result("解除禁用成功");
        }else{
            return validationResult(1001,"解除禁用失败");
        }
    }

    @RequestMapping(value = "/disableRechargeStatus")
    public Object disableRechargeStatus(String code){
        log.info("禁用用户提现code："+code);
        if (LolUtils.isEmptyOrNull(code))
            return validationResult(1001,"禁用账户提现，Code不能为空");
        boolean flag=accountService.disableRechargeStatus(code);
        if (flag){
            return result("禁用成功");
        }else {
            return validationResult(1001,"禁用失败");
        }
    }

    @RequestMapping(value = "/enableRechargeStatus")
    public Object enableRechargeStatus(String code){
        log.info("启用提现用户code："+code);
        if (LolUtils.isEmptyOrNull(code))
            return validationResult(1001,"启用提现账户信息，Code不能为空");
        boolean flag=accountService.enableRechargeStatus(code);
        if (flag){
            return result("启用成功");
        }else{
            return validationResult(1001,"启用失败");
        }
    }

    @RequestMapping(value = "/disableWithdrawalsStatus")
    public Object disableWithdrawalsStatus(String code){
        log.info("禁用用户充值code："+code);
        if (LolUtils.isEmptyOrNull(code))
            return validationResult(1001,"禁用账户充值，Code不能为空");
        boolean flag=accountService.disableWithdrawalsStatus(code);
        if (flag){
            return result("禁用成功");
        }else {
            return validationResult(1001,"禁用失败");
        }
    }

    @RequestMapping(value = "/enableWithdrawalsStatus")
    public Object enableWithdrawalsStatus(String code){
        log.info("启用充值用户code："+code);
        if (LolUtils.isEmptyOrNull(code))
            return validationResult(1001,"启用充值账户信息，Code不能为空");
        boolean flag=accountService.enableWithdrawalsStatus(code);
        if (flag){
            return result("启用成功");
        }else{
            return validationResult(1001,"启用失败");
        }
    }

    @RequestMapping(value = "/disableExchangePrizeStatus")
    public Object disableExchangePrizeStatus(String code){
        log.info("禁用用户兑换code："+code);
        if (LolUtils.isEmptyOrNull(code))
            return validationResult(1001,"禁用账户兑换，Code不能为空");
        boolean flag=accountService.disableExchangePrizeStatus(code);
        if (flag){
            return result("禁用成功");
        }else {
            return validationResult(1001,"禁用失败");
        }
    }

    @RequestMapping(value = "/enableExchangePrizeStatus")
    public Object enableExchangePrizeStatus(String code){
        log.info("启用兑换用户code："+code);
        if (LolUtils.isEmptyOrNull(code))
            return validationResult(1001,"启用兑换账户信息，Code不能为空");
        boolean flag=accountService.enableExchangePrizeStatus(code);
        if (flag){
            return result("启用成功");
        }else{
            return validationResult(1001,"启用失败");
        }
    }

    @RequestMapping(value = "/disableExchangeBettingStatus")
    public Object disableExchangeBettingStatus(String code){
        log.info("禁用用户投注code："+code);
        if (LolUtils.isEmptyOrNull(code))
            return validationResult(1001,"禁用账户投注，Code不能为空");
        boolean flag=accountService.disableExchangeBettingStatus(code);
        if (flag){
            return result("禁用成功");
        }else {
            return validationResult(1001,"禁用失败");
        }
    }

    @RequestMapping(value = "/enableExchangeBettingStatus")
    public Object enableExchangeBettingStatus(String code){
        log.info("启用投注用户code："+code);
        if (LolUtils.isEmptyOrNull(code))
            return validationResult(1001,"启用投注账户信息，Code不能为空");
        boolean flag=accountService.enableExchangeBettingStatus(code);
        if (flag){
            return result("启用成功");
        }else{
            return validationResult(1001,"启用失败");
        }
    }
}
