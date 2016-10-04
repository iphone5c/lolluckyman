package com.lolluckyman.business.topupwithdrawal.service;

import com.lolluckyman.business.topupwithdrawal.entity.TopUpWithdrawal;
import com.lolluckyman.business.topupwithdrawal.entity.em.DisposalStatus;
import com.lolluckyman.business.topupwithdrawal.entity.em.TradeType;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;

import java.util.List;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface ITopUpWithdrawalService {

    /**
     *获取充值提现信息分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<TopUpWithdrawal> getTopUpWithdrawalPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     *获取充值提现信息列表
     * @param wheres    条件
     * @return 对象列表
     */
    public List<TopUpWithdrawal> getTopUpWithdrawalList(QueryParams wheres);

    /**
     * 根据充值提现信息code查找充值提现信息
     * @param topUpWithdrawalCode 充值提现信息code
     * @return 充值提现信息对象
     */
    public TopUpWithdrawal getTopUpWithdrawalByCode(String topUpWithdrawalCode);

    /**
     * 操作指定充值提现状态
     * @param code 充值提现code
     * @param disposalStatus 充值提现状态
     * @return true表示操作成功 false表示操作失败
     */
    public boolean operationTopUpWithdrawalStatus(String code, DisposalStatus disposalStatus);

    /**
     * 申请充值
     * @param applyMoney 申请充值金额
     * @param applyAccountCode 申请人
     * @param tradeType 交易类型
     * @param tradeNumber 交易账号
     * @return 充值记录
     */
    public TopUpWithdrawal applyTopUp(Double applyMoney,String applyAccountCode ,TradeType tradeType,String tradeNumber);

    /**
     * 锁定
     * @param code
     * @return
     */
    public boolean locking(String code);

    /**
     * 审核
     * @param code
     * @return
     */
    public boolean examine(String code,Double money);

    /**
     * 撤销
     * @param code
     * @return
     */
    public boolean revoke(String code);
}
