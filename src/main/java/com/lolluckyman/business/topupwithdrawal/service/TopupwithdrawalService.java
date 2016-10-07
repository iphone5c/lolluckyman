package com.lolluckyman.business.topupwithdrawal.service;

import com.lolluckyman.business.account.entity.Account;
import com.lolluckyman.business.account.entity.em.AccountStatus;
import com.lolluckyman.business.account.entity.em.WithdrawalsStatus;
import com.lolluckyman.business.account.service.IAccountService;
import com.lolluckyman.business.accountassets.entity.AccountAssets;
import com.lolluckyman.business.accountassets.service.IAccountAssetsService;
import com.lolluckyman.business.codebuilder.ICodeBuilder;
import com.lolluckyman.business.competition.service.ICompetitionService;
import com.lolluckyman.business.team.service.ITeamService;
import com.lolluckyman.business.topupwithdrawal.dao.ITopUpWithdrawalDao;
import com.lolluckyman.business.topupwithdrawal.entity.TopUpWithdrawal;
import com.lolluckyman.business.topupwithdrawal.entity.em.BusinessType;
import com.lolluckyman.business.topupwithdrawal.entity.em.DisposalStatus;
import com.lolluckyman.business.topupwithdrawal.entity.em.TradeType;
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
@Service("topUpWithdrawalService")
public class TopUpWithdrawalService extends BaseService implements ITopUpWithdrawalService {

    public Logger log = Logger.getLogger(TopUpWithdrawalService.class);//日志

    @Autowired
    private ITopUpWithdrawalDao topUpWithdrawalDao;
    @Autowired
    private ICodeBuilder codeBuilder;
    @Autowired
    private ITeamService teamService;
    @Autowired
    private ICompetitionService competitionService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IAccountAssetsService accountAssetsService;

    /**
     *获取充值提现信息分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<TopUpWithdrawal> getTopUpWithdrawalPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return topUpWithdrawalDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     *获取充值提现信息列表
     * @param wheres    条件
     * @return 对象列表
     */
    @Override
    public List<TopUpWithdrawal> getTopUpWithdrawalList(QueryParams wheres) {
        return topUpWithdrawalDao.queryList(wheres,0,-1,true);
    }

    /**
     * 根据充值提现信息code查找充值提现信息
     * @param topUpWithdrawalCode 充值提现信息code
     * @return 充值提现信息对象
     */
    @Override
    public TopUpWithdrawal getTopUpWithdrawalByCode(String topUpWithdrawalCode) {
        if (LolUtils.isEmptyOrNull(topUpWithdrawalCode))
            throw new IllegalArgumentException("根据code查找充值提现信息信息时，Code不能为空");
        return topUpWithdrawalDao.getObject(topUpWithdrawalCode,true);
    }

    /**
     * 操作指定充值提现状态
     * @param code 充值提现code
     * @param disposalStatus 充值提现状态
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean operationTopUpWithdrawalStatus(String code, DisposalStatus disposalStatus) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("操作指定充值提现的状态时，code不能为空或null");
        if (disposalStatus==null)
            throw new IllegalArgumentException("操作指定充值提现的状态时，修改的充值提现状态不能为空");
        TopUpWithdrawal topUpWithdrawal=topUpWithdrawalDao.getObject(code,true);
        if (topUpWithdrawal==null)
            throw new IllegalArgumentException("操作指定充值提现的状态时，找不到此充值提现信息，code："+code);
        topUpWithdrawal.setDisposalStatus(disposalStatus);
        int info=topUpWithdrawalDao.updateObject(topUpWithdrawal);
        return info>0?true:false;
    }

    /**
     * 申请充值
     * @param applyMoney 申请充值金额
     * @param applyAccountCode 申请人
     * @param tradeType 交易类型
     * @param tradeNumber 交易账号
     * @return 充值记录
     */
    @Override
    public TopUpWithdrawal applyTopUp(Double applyMoney, String applyAccountCode , TradeType tradeType, String tradeNumber) {
        if (applyMoney==null||applyMoney<=0)
            throw new IllegalArgumentException("充值金额必须大于零");
        if (LolUtils.isEmptyOrNull(applyAccountCode))
            throw new IllegalArgumentException("申请人不能为空");
        if (tradeType==null)
            throw new IllegalArgumentException("交易类型不能为空");
        if (LolUtils.isEmptyOrNull(tradeNumber))
            throw new IllegalArgumentException("交易账号不能为空");
        Account temp = accountService.getAccountByCode(applyAccountCode);
        if (temp==null)
            throw new IllegalArgumentException("找不到此申请人的信息");
        if (temp.getAccountStatus()!= AccountStatus.正常)
            throw new IllegalArgumentException("此申请人的账号已被禁用，不能进行充值操作");
        if (temp.getWithdrawalsStatus()!= WithdrawalsStatus.正常)
            throw new IllegalArgumentException("此申请人的账号充值状态已被禁用，不能进行充值操作");
        TopUpWithdrawal topUpWithdrawal=new TopUpWithdrawal();
        topUpWithdrawal.setCode(codeBuilder.getTopUpWithdrawalCode());
        topUpWithdrawal.setApplyMoney(applyMoney);
        topUpWithdrawal.setApplyAccountCode(temp.getCode());
        topUpWithdrawal.setApplyTime(new Date());
        topUpWithdrawal.setBusinessType(BusinessType.充值);
        topUpWithdrawal.setTradeType(tradeType);
        topUpWithdrawal.setTradeNumber(tradeNumber);
        topUpWithdrawal.setDisposalStatus(DisposalStatus.等待处理);
        int info=topUpWithdrawalDao.insertObject(topUpWithdrawal);
        return info>0?topUpWithdrawal:null;
    }

    /**
     * 锁定
     * @param code
     * @return
     */
    @Override
    public boolean locking(String code) {
        if(LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("锁定时，code不能为空");
        TopUpWithdrawal topUpWithdrawal=topUpWithdrawalDao.getObject(code,true);
        if(topUpWithdrawal==null)
            throw new IllegalArgumentException("找不到此充值提现记录");
        if(topUpWithdrawal.getDisposalStatus()!=DisposalStatus.等待处理)
            throw new IllegalArgumentException("只有等带处理的记录才能进行锁定操作");
        return this.operationTopUpWithdrawalStatus(code,DisposalStatus.正在处理);
    }

    /**
     * 审核
     * @param code
     * @return
     */
    @Override
    public boolean examine(String code,Double money) {
        if(LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("审核时，code不能为空");
        TopUpWithdrawal topUpWithdrawal=topUpWithdrawalDao.getObject(code,true);
        if(topUpWithdrawal==null)
            throw new IllegalArgumentException("找不到此充值提现记录");
        if(topUpWithdrawal.getDisposalStatus()!=DisposalStatus.正在处理)
            throw new IllegalArgumentException("只有锁定的记录才能进行审核操作");
        topUpWithdrawal.setMoney(money);
        topUpWithdrawal.setDisposalStatus(DisposalStatus.处理完成);
        topUpWithdrawal.setDisposalTime(new Date());
        topUpWithdrawalDao.updateObject(topUpWithdrawal);
        AccountAssets accountAssets  = accountAssetsService.getAccountAssetsByAccountCode(topUpWithdrawal.getApplyAccountCode());
        if(accountAssets==null)
            throw new IllegalArgumentException("找不到此申请人资产信息");
        accountAssets.setQuizMoney(accountAssets.getQuizMoney()+money*10);
        accountAssetsService.updateAccountAssets(accountAssets);
        return true;
    }

    /**
     * 撤销
     * @param code
     * @return
     */
    @Override
    public boolean revoke(String code) {
        if(LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("撤销时，code不能为空");
        TopUpWithdrawal topUpWithdrawal=topUpWithdrawalDao.getObject(code,true);
        if(topUpWithdrawal==null)
            throw new IllegalArgumentException("找不到此充值提现记录");
        if(topUpWithdrawal.getDisposalStatus()!=DisposalStatus.等待处理)
            throw new IllegalArgumentException("只有等带处理的记录才能进行撤销操作");
        topUpWithdrawal.setDisposalStatus(DisposalStatus.用户撤销);
        topUpWithdrawal.setDisposalTime(new Date());
        int info=topUpWithdrawalDao.updateObject(topUpWithdrawal);
        return info>0?true:false;
    }
}
