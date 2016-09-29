package com.lolluckyman.business.bettingrecord.service;

import com.lolluckyman.business.account.entity.Account;
import com.lolluckyman.business.account.entity.em.AccountStatus;
import com.lolluckyman.business.account.entity.em.BettingStatus;
import com.lolluckyman.business.account.service.IAccountService;
import com.lolluckyman.business.accountassets.entity.AccountAssets;
import com.lolluckyman.business.accountassets.service.IAccountAssetsService;
import com.lolluckyman.business.bettingrecord.dao.IBettingRecordDao;
import com.lolluckyman.business.bettingrecord.entity.BettingRecord;
import com.lolluckyman.business.bettingrecord.entity.em.BettingRecordResult;
import com.lolluckyman.business.bettingrecord.entity.em.BettingRecordStatus;
import com.lolluckyman.business.codebuilder.ICodeBuilder;
import com.lolluckyman.business.competition.entity.Competition;
import com.lolluckyman.business.competition.entity.em.CompetitionStatus;
import com.lolluckyman.business.competition.service.ICompetitionService;
import com.lolluckyman.business.playrecord.entity.PlayRecord;
import com.lolluckyman.business.playrecord.entity.em.Play;
import com.lolluckyman.business.playrecord.service.IPlayRecordService;
import com.lolluckyman.business.restrain.entity.Restrain;
import com.lolluckyman.business.restrain.entity.em.RestrainStatus;
import com.lolluckyman.business.restrain.service.IRestrainService;
import com.lolluckyman.business.team.service.ITeamService;
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
import java.util.UUID;

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
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IAccountAssetsService accountAssetsService;
    @Autowired
    private IPlayRecordService playRecordService;
    @Autowired
    private IRestrainService restrainService;


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

    /**
     * 根据玩法删除投注记录
     * @param playRecordCode 玩法code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean deleteBettingRecordByPlayRecordCode(String playRecordCode) {
        if (LolUtils.isEmptyOrNull(playRecordCode))
            throw new IllegalArgumentException("根据玩法删除投注记录,玩法code不能为空或null");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("",playRecordCode);
        bettingRecordDao.deleteObjectByWhere(queryParams);
        return true;
    }

    /**
     * 用户投注
     * @param accountCode 投注人
     * @param playRecordCode 投注玩法
     * @param quizMoney 竞猜币数量
     * @param betting 投注结果
     * @return 投注信息
     */
    @Override
    public BettingRecord accountBetting(String accountCode, String playRecordCode, Double quizMoney, String betting) {
        if (LolUtils.isEmptyOrNull(accountCode))
            throw new IllegalArgumentException("投注人不能为空");
        if (LolUtils.isEmptyOrNull(playRecordCode))
            throw new IllegalArgumentException("投注联盟玩法不能为空");
        if (quizMoney==null||quizMoney<10)
            throw new IllegalArgumentException("投注的竞猜币数量最小为10个");
        if (LolUtils.isEmptyOrNull(betting))
            throw new IllegalArgumentException("投注结果尚未选择");
        Account account=accountService.getAccountByCode(accountCode);
        if (account==null)
            throw new IllegalArgumentException("投注人信息找不到");
        if (account.getAccountStatus()!= AccountStatus.正常)
            throw new IllegalArgumentException("此投注用户已被禁用，不能进行投注");
        if (account.getBettingStatus()!= BettingStatus.正常)
            throw new IllegalArgumentException("此用户已禁用投注");
        if (account.getAccountAssets().getQuizMoney()<quizMoney)
            throw new IllegalArgumentException("此账户可用竞猜币数量不足，请充值");
        PlayRecord playRecord=playRecordService.getPlayRecordByCode(playRecordCode);
        if (playRecord==null)
            throw new IllegalArgumentException("没有找到此投注联盟玩法");
        if (playRecord.getPlay()== Play.总局输赢||playRecord.getPlay()==Play.比分){
            Competition competition=competitionService.getCompetitionByCode(playRecord.getForeignKey());
            if (competition==null)
                throw new IllegalArgumentException("没有找到这场比赛信息");
            if (competition.getCompetitionStatus()== CompetitionStatus.禁止投注)
                throw new IllegalArgumentException("比赛当前禁止投注");
        }else {
            Restrain restrain=restrainService.getRestrainByCode(playRecord.getForeignKey());
            if (restrain==null)
                throw new IllegalArgumentException("没有找到这一局比赛信息");
            if (restrain.getRestrainStatus()== RestrainStatus.禁止投注)
                throw new IllegalArgumentException("这局比赛当前禁止投注");
            Competition competition=competitionService.getCompetitionByCode(restrain.getCompetitionCode());
            if (competition==null)
                throw new IllegalArgumentException("没有找到这场比赛信息");
            if (competition.getCompetitionStatus()== CompetitionStatus.禁止投注)
                throw new IllegalArgumentException("比赛当前禁止投注");
        }
        AccountAssets updateAccountAssets=account.getAccountAssets();
        //可用竞猜币
        Double quizMoneyTemp=updateAccountAssets.getQuizMoney();
        //冻结竞猜币
        Double freezeQuizMoneyTemp=updateAccountAssets.getFreezeQuizMoney();
        updateAccountAssets.setQuizMoney(quizMoneyTemp - quizMoney);
        updateAccountAssets.setFreezeQuizMoney(freezeQuizMoneyTemp + quizMoney);
        AccountAssets temp=accountAssetsService.updateAccountAssets(updateAccountAssets);
        if (temp==null)
            throw new IllegalArgumentException("账户资金更新失败");
        BettingRecord bettingRecord=new BettingRecord();
        bettingRecord.setCode(UUID.randomUUID().toString());
        bettingRecord.setAccountCode(accountCode);
        bettingRecord.setPlayRecordCode(playRecordCode);
        bettingRecord.setQuizMoney(quizMoney);
        bettingRecord.setBetting(betting);
        bettingRecord.setBettingRecordTime(new Date());
        bettingRecord.setBettingRecordResult(BettingRecordResult.等待结果);
        bettingRecord.setBettingRecordStatus(BettingRecordStatus.未结算);
        int info = bettingRecordDao.insertObject(bettingRecord);
        return info>0?bettingRecord:null;
    }

}
