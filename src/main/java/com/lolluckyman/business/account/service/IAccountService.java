package com.lolluckyman.business.account.service;

import com.lolluckyman.business.account.entity.Account;
import com.lolluckyman.business.account.entity.em.*;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.xpath.operations.Bool;

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

    /**
     * 重置指定用户登录密码
     * @param code 用户code
     * @return true表示操作成功 false表示操作失败
     */
    public Boolean resetAccountPassword(String code);

    /**
     * 重置指定用户取款密码
     * @param code 用户code
     * @return true表示操作成功 false表示操作失败
     */
    public Boolean resetAccountWithdrawalsPassword(String code);

    /**
     * 操作指定用户额状态
     * @param accountCode 用户code
     * @param accountStatus 用户状态
     * @return true表示操作成功 false表示操作失败
     */
    public boolean operationAccountStatus(String accountCode,AccountStatus accountStatus);

    /**
     * 禁用指定用户
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean disableAccount(String accountCode);

    /**
     * 将指定用户解除禁用
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean removeDisableAccount(String accountCode);

    /**
     * 操作指定用户提现状态
     * @param accountCode 用户code
     * @param rechargeStatus 提现状态
     * @return true表示操作成功 false表示操作失败
     */
    public boolean operationRechargeStatus(String accountCode,RechargeStatus rechargeStatus);

    /**
     * 禁用指定用户提现
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean disableRechargeStatus(String accountCode);

    /**
     * 将指定用户启用提现
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean enableRechargeStatus(String accountCode);

    /**
     * 操作指定用户充值状态
     * @param accountCode 用户code
     * @param withdrawalsStatus 充值状态
     * @return true表示操作成功 false表示操作失败
     */
    public boolean operationWithdrawalsStatus(String accountCode,WithdrawalsStatus withdrawalsStatus);

    /**
     * 禁用指定用户充值
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean disableWithdrawalsStatus(String accountCode);

    /**
     * 将指定用户启用充值
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean enableWithdrawalsStatus(String accountCode);

    /**
     * 操作指定用户兑换状态
     * @param accountCode 用户code
     * @param exchangePrizeStatus 兑换状态
     * @return true表示操作成功 false表示操作失败
     */
    public boolean operationExchangePrizeStatus(String accountCode,ExchangePrizeStatus exchangePrizeStatus);

    /**
     * 禁用指定用户兑换
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean disableExchangePrizeStatus(String accountCode);

    /**
     * 将指定用户启用兑换
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean enableExchangePrizeStatus(String accountCode);

    /**
     * 操作指定用户投注状态
     * @param accountCode 用户code
     * @param bettingStatus 投注状态
     * @return true表示操作成功 false表示操作失败
     */
    public boolean operationBettingStatus(String accountCode,BettingStatus bettingStatus);

    /**
     * 禁用指定用户投注
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean disableExchangeBettingStatus(String accountCode);

    /**
     * 将指定用户启用投注
     * @param accountCode 用户code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean enableExchangeBettingStatus(String accountCode);

}
