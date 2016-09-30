package com.lolluckyman.business.account.service;

import com.lolluckyman.business.account.dao.IAccountDao;
import com.lolluckyman.business.account.entity.Account;
import com.lolluckyman.business.account.entity.em.*;
import com.lolluckyman.business.accountassets.entity.AccountAssets;
import com.lolluckyman.business.accountassets.service.IAccountAssetsService;
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
@Service("accountService")
public class AccountService extends BaseService implements IAccountService {

    public Logger log = Logger.getLogger(AccountService.class);//日志

    @Autowired
    private IAccountDao accountDao;
    @Autowired
    private ICodeBuilder codeBuilder;
    @Autowired
    private IAccountAssetsService accountAssetsService;

    /**
     *获取账户分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<Account> getAccountPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return accountDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     * 根据账户code查找账户
     * @param code 账户code
     * @return 账户对象
     */
    @Override
    public Account getAccountByCode(String code) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("根据code查找账户信息，code不能为空");
        Account account=accountDao.getObject(code,true);
        AccountAssets accountAssets=accountAssetsService.getAccountAssetsByAccountCode(account.getCode());
        account.setAccountAssets(accountAssets);
        return (account!=null&&accountAssets!=null)?account:null;
    }

    /**
     * 重置指定用户登录密码
     * @param code 用户code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public Boolean resetAccountPassword(String code) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("指定用户重置登录密码时，code不能为空或null");
        Account account=accountDao.getObject(code,true);
        if (account==null)
            throw new IllegalArgumentException("指定用户重置登录密码时，找不到此用户信息，code："+code);
        account.setPassword(LolUtils.encryptPassword("123456"));
        int info=accountDao.updateObject(account);
        return info>0?true:false;
    }

    /**
     * 重置指定用户取款密码
     * @param code 用户code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public Boolean resetAccountWithdrawalsPassword(String code) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("指定用户重置取款密码时，code不能为空或null");
        Account account=accountDao.getObject(code,true);
        if (account==null)
            throw new IllegalArgumentException("指定用户重置取款密码时，找不到此用户信息，code："+code);
        account.setWithdrawalsPassword(LolUtils.encryptPassword("123456"));
        int info=accountDao.updateObject(account);
        return info>0?true:false;
    }

    /**
     * 操作指定用户额状态
     * @param accountCode 用户code
     * @param accountStatus 用户状态
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean operationAccountStatus(String accountCode, AccountStatus accountStatus) {
        if (LolUtils.isEmptyOrNull(accountCode))
            throw new IllegalArgumentException("操作指定用户的状态时，code不能为空或null");
        if (accountStatus==null)
            throw new IllegalArgumentException("操作指定用户的状态时，修改的用户状态不能为空");
        Account account=accountDao.getObject(accountCode,true);
        if (account==null)
            throw new IllegalArgumentException("操作指定用户的状态时，找不到此用户信息，code："+accountCode);
        account.setAccountStatus(accountStatus);
        int info=accountDao.updateObject(account);
        return info>0?true:false;
    }

    /**
     * 禁用指定用户
     * @param uccountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean disableAccount(String uccountCode) {
        if (LolUtils.isEmptyOrNull(uccountCode))
            throw new IllegalArgumentException("禁用指定用户时，code不能为空或null");
        return this.operationAccountStatus(uccountCode,AccountStatus.禁用);
    }

    /**
     * 将指定用户解除禁用
     * @param uccountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean removeDisableAccount(String uccountCode) {
        if (LolUtils.isEmptyOrNull(uccountCode))
            throw new IllegalArgumentException("解除指定用户的禁用状态时，code不能为空或null");
        return this.operationAccountStatus(uccountCode,AccountStatus.正常);
    }

    /**
     * 操作指定用户提现状态
     * @param accountCode 用户code
     * @param rechargeStatus 提现状态
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean operationRechargeStatus(String accountCode, RechargeStatus rechargeStatus) {
        if (LolUtils.isEmptyOrNull(accountCode))
            throw new IllegalArgumentException("操作指定用户的提现状态时，code不能为空或null");
        if (rechargeStatus==null)
            throw new IllegalArgumentException("操作指定用户的提现状态时，修改的用户提现状态不能为空");
        Account account=accountDao.getObject(accountCode,true);
        if (account==null)
            throw new IllegalArgumentException("操作指定用户的提现状态时，找不到此用户信息，code："+accountCode);
        account.setRechargeStatus(rechargeStatus);
        int info=accountDao.updateObject(account);
        return info>0?true:false;
    }

    /**
     * 禁用指定用户提现
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean disableRechargeStatus(String accountCode) {
        if (LolUtils.isEmptyOrNull(accountCode))
            throw new IllegalArgumentException("禁用指定用户提现时，code不能为空或null");
        return this.operationRechargeStatus(accountCode, RechargeStatus.禁用);
    }

    /**
     * 将指定用户启用提现
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean enableRechargeStatus(String accountCode) {
        if (LolUtils.isEmptyOrNull(accountCode))
            throw new IllegalArgumentException("启用指定用户的提现状态时，code不能为空或null");
        return this.operationRechargeStatus(accountCode, RechargeStatus.正常);
    }

    /**
     * 操作指定用户充值状态
     * @param accountCode 用户code
     * @param withdrawalsStatus 充值状态
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean operationWithdrawalsStatus(String accountCode, WithdrawalsStatus withdrawalsStatus) {
        if (LolUtils.isEmptyOrNull(accountCode))
            throw new IllegalArgumentException("操作指定用户的充值状态时，code不能为空或null");
        if (withdrawalsStatus==null)
            throw new IllegalArgumentException("操作指定用户的充值状态时，修改的用户充值状态不能为空");
        Account account=accountDao.getObject(accountCode,true);
        if (account==null)
            throw new IllegalArgumentException("操作指定用户的充值状态时，找不到此用户信息，code："+accountCode);
        account.setWithdrawalsStatus(withdrawalsStatus);
        int info=accountDao.updateObject(account);
        return info>0?true:false;
    }

    /**
     * 禁用指定用户充值
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean disableWithdrawalsStatus(String accountCode) {
        if (LolUtils.isEmptyOrNull(accountCode))
            throw new IllegalArgumentException("禁用指定用户充值时，code不能为空或null");
        return this.operationWithdrawalsStatus(accountCode, WithdrawalsStatus.禁用);
    }

    /**
     * 将指定用户启用充值
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean enableWithdrawalsStatus(String accountCode) {
        if (LolUtils.isEmptyOrNull(accountCode))
            throw new IllegalArgumentException("启用指定用户的充值状态时，code不能为空或null");
        return this.operationWithdrawalsStatus(accountCode, WithdrawalsStatus.正常);
    }

    /**
     * 操作指定用户兑换状态
     * @param accountCode 用户code
     * @param exchangePrizeStatus 兑换状态
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean operationExchangePrizeStatus(String accountCode, ExchangePrizeStatus exchangePrizeStatus) {
        if (LolUtils.isEmptyOrNull(accountCode))
            throw new IllegalArgumentException("操作指定用户的兑换状态时，code不能为空或null");
        if (exchangePrizeStatus==null)
            throw new IllegalArgumentException("操作指定用户的兑换状态时，修改的用户兑换状态不能为空");
        Account account=accountDao.getObject(accountCode,true);
        if (account==null)
            throw new IllegalArgumentException("操作指定用户的兑换状态时，找不到此用户信息，code："+accountCode);
        account.setExchangePrizeStatus(exchangePrizeStatus);
        int info=accountDao.updateObject(account);
        return info>0?true:false;
    }

    /**
     * 禁用指定用户兑换
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean disableExchangePrizeStatus(String accountCode) {
        if (LolUtils.isEmptyOrNull(accountCode))
            throw new IllegalArgumentException("禁用指定用户兑换时，code不能为空或null");
        return this.operationExchangePrizeStatus(accountCode, ExchangePrizeStatus.禁用);
    }

    /**
     * 将指定用户启用兑换
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean enableExchangePrizeStatus(String accountCode) {
        if (LolUtils.isEmptyOrNull(accountCode))
            throw new IllegalArgumentException("启用指定用户的兑换状态时，code不能为空或null");
        return this.operationExchangePrizeStatus(accountCode, ExchangePrizeStatus.正常);
    }

    /**
     * 操作指定用户投注状态
     * @param accountCode 用户code
     * @param bettingStatus 投注状态
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean operationBettingStatus(String accountCode, BettingStatus bettingStatus) {
        if (LolUtils.isEmptyOrNull(accountCode))
            throw new IllegalArgumentException("操作指定用户的投注状态时，code不能为空或null");
        if (bettingStatus==null)
            throw new IllegalArgumentException("操作指定用户的投注状态时，修改的用户投注状态不能为空");
        Account account=accountDao.getObject(accountCode,true);
        if (account==null)
            throw new IllegalArgumentException("操作指定用户的投注状态时，找不到此用户信息，code："+accountCode);
        account.setBettingStatus(bettingStatus);
        int info=accountDao.updateObject(account);
        return info>0?true:false;
    }

    /**
     * 禁用指定用户投注
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean disableExchangeBettingStatus(String accountCode) {
        if (LolUtils.isEmptyOrNull(accountCode))
            throw new IllegalArgumentException("禁用指定用户投注时，code不能为空或null");
        return this.operationBettingStatus(accountCode, BettingStatus.禁用);
    }

    /**
     * 将指定用户启用投注
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean enableExchangeBettingStatus(String accountCode) {
        if (LolUtils.isEmptyOrNull(accountCode))
            throw new IllegalArgumentException("启用指定用户的投注状态时，code不能为空或null");
        return this.operationBettingStatus(accountCode, BettingStatus.正常);
    }

    /**
     * 登录验证
     * @param account 用户对象
     * @return 用户对象
     */
    @Override
    public Account loginAccount(Account account) {
        if (LolUtils.isEmptyOrNull(account.getLoginAccount()))
            throw new IllegalArgumentException("账户登录时，登录账号不能为空或null");
        if (LolUtils.isEmptyOrNull(account.getPassword()))
            throw new IllegalArgumentException("账户登录时，登录密码不能为空或null");
        Account temp=this.getAccountByloginAccount(account.getLoginAccount());
        if (temp==null)
            throw new IllegalArgumentException("无此账户");
        if (!LolUtils.verifyPassword(account.getPassword(),temp.getPassword()))
            throw new IllegalArgumentException("账户登录时，密码错误");
        if (temp.getAccountStatus()!=AccountStatus.正常)
            throw new IllegalArgumentException("此账户已被禁用");
        return temp;
    }

    /**
     * 根据登录账号查找账户信息
     * @param loginAccount 登录账号
     * @return 账户信息
     */
    @Override
    public Account getAccountByloginAccount(String loginAccount) {
        if (LolUtils.isEmptyOrNull(loginAccount))
            throw new IllegalArgumentException("根据登录账号查找账户信息，登录账号不能为空或null");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("loginAccount",loginAccount);
        List<Account> accountList=accountDao.queryList(queryParams,0,-1,true);
        return ((accountList!=null&&accountList.size()>0)?accountList.get(0):null);
    }

    /**
     * 注册用户
     * @param account
     * @return
     */
    @Override
    public Account registerAccount(Account account) {
        if (account==null)
            throw new IllegalArgumentException("注册新用户，用户信息不能为空");
        if (LolUtils.isEmptyOrNull(account.getLoginAccount()))
            throw new IllegalArgumentException("注册新用户，登陆账号不能为空");
        if (LolUtils.isEmptyOrNull(account.getPassword()))
            throw new IllegalArgumentException("注册新用户，登陆密码不能为空");
//        if (LolUtils.isEmptyOrNull(account.getRealName()))
//            throw new IllegalArgumentException("注册新用户，真实姓名不能为空");
//        if (LolUtils.isEmptyOrNull(account.getEmail()))
//            throw new IllegalArgumentException("注册新用户，邮箱地址不能为空");
//        if (account.getPasswordProblem()==null)
//            throw new IllegalArgumentException("注册新用户，密码问题不能为空");
//        if (LolUtils.isEmptyOrNull(account.getPasswordAnswer()))
//            throw new IllegalArgumentException("注册新用户，问题答案不能为空");
        if (LolUtils.isEmptyOrNull(account.getWithdrawalsPassword()))
            throw new IllegalArgumentException("注册新用户，取款密码不能为空");
        if (account.getPassword().equals(account.getWithdrawalsPassword()))
            throw new IllegalArgumentException("注册新用户，登录密码与取款密码不能一致");
        Account temp=this.getAccountByloginAccount(account.getLoginAccount());
        if (temp!=null)
            throw new IllegalArgumentException("注册新用户,此登录名已存在");
        String accountCode = codeBuilder.getAccountCode();
        account.setPassword(LolUtils.encryptPassword(account.getPassword()));
        account.setWithdrawalsPassword(LolUtils.encryptPassword(account.getWithdrawalsPassword()));
        account.setCode(accountCode);
        account.setCreateTime(new Date());
        account.setAccountStatus(AccountStatus.正常);
        account.setWithdrawalsStatus(WithdrawalsStatus.正常);
        account.setRechargeStatus(RechargeStatus.正常);
        account.setExchangePrizeStatus(ExchangePrizeStatus.正常);
        account.setBettingStatus(BettingStatus.正常);
        AccountAssets accountAssets = new AccountAssets();
        accountAssets.setAccountCode(accountCode);
        accountAssets.setQuizMoney(0d);
        accountAssets.setPensionMoney(0d);
        accountAssets.setVictoryMoney(0d);
        accountAssets.setFreezeQuizMoney(0d);
        accountAssets.setFreezePensionMoney(0d);
        accountAssets.setFreezeVictoryMoney(0d);
        //资金账户添加
        accountAssetsService.createAccountAssets(accountAssets);
        //账户信息添加
        int info = accountDao.insertObject(account);
        return info>0?account:null;
    }

}
