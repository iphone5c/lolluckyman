package com.lolluckyman.business.bettingrecord.service;

import com.lolluckyman.business.bettingrecord.dao.IBettingRecordDao;
import com.lolluckyman.business.bettingrecord.entity.BettingRecord;
import com.lolluckyman.business.bettingrecord.entity.em.BettingRecordStatus;
import com.lolluckyman.business.codebuilder.ICodeBuilder;
import com.lolluckyman.business.competition.service.ICompetitionService;
import com.lolluckyman.business.team.service.ITeamService;
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
@Service("bettingRecordService")
public class BettingRecordService extends BaseService implements IBettingRecordService {

    public Logger log = Logger.getLogger(BettingRecordService.class);//日志

    @Autowired
    private IBettingRecordDao bettingRecordDao;
    @Autowired
    private ICodeBuilder codeBuilder;
    @Autowired
    private ITeamService teamService;
    @Autowired
    private ICompetitionService competitionService;

    /**
     *获取投注信息分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<BettingRecord> getBettingRecordPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return bettingRecordDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     *获取投注信息列表
     * @param wheres    条件
     * @return 对象列表
     */
    @Override
    public List<BettingRecord> getBettingRecordList(QueryParams wheres) {
        return bettingRecordDao.queryList(wheres,0,-1,true);
    }

    /**
     * 根据投注信息code查找投注信息
     * @param bettingRecordCode 投注信息code
     * @return 投注信息对象
     */
    @Override
    public BettingRecord getBettingRecordByCode(String bettingRecordCode) {
        if (LolUtils.isEmptyOrNull(bettingRecordCode))
            throw new IllegalArgumentException("根据code查找投注信息信息时，Code不能为空");
        return bettingRecordDao.getObject(bettingRecordCode,true);
    }

    /**
     * 操作指定投注状态
     * @param code 投注code
     * @param bettingRecordStatus 投注状态
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean operationBettingRecordStatus(String code, BettingRecordStatus bettingRecordStatus) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("操作指定投注的状态时，code不能为空或null");
        if (bettingRecordStatus==null)
            throw new IllegalArgumentException("操作指定投注的状态时，修改的投注状态不能为空");
        BettingRecord bettingRecord=bettingRecordDao.getObject(code,true);
        if (bettingRecord==null)
            throw new IllegalArgumentException("操作指定投注的状态时，找不到此投注信息，code："+code);
        bettingRecord.setBettingRecordStatus(bettingRecordStatus);
        int info=bettingRecordDao.updateObject(bettingRecord);
        return info>0?true:false;
    }

}
