package com.lolluckyman.business.topupwithdrawal.service;

import com.lolluckyman.business.codebuilder.ICodeBuilder;
import com.lolluckyman.business.competition.service.ICompetitionService;
import com.lolluckyman.business.team.service.ITeamService;
import com.lolluckyman.business.topupwithdrawal.dao.ITopUpWithdrawalDao;
import com.lolluckyman.business.topupwithdrawal.entity.TopUpWithdrawal;
import com.lolluckyman.business.topupwithdrawal.entity.em.DisposalStatus;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseService;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
