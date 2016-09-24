package com.lolluckyman.business.competition.service;

import com.lolluckyman.business.codebuilder.ICodeBuilder;
import com.lolluckyman.business.competition.dao.ICompetitionDao;
import com.lolluckyman.business.competition.entity.Competition;
import com.lolluckyman.business.competition.entity.em.CompetitionStatus;
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

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("competitionService")
public class CompetitionService extends BaseService implements ICompetitionService {

    public Logger log = Logger.getLogger(CompetitionService.class);//日志

    @Autowired
    private ICompetitionDao competitionDao;
    @Autowired
    private ICodeBuilder codeBuilder;
    @Autowired
    private ITeamService teamService;

    /**
     *获取赛事信息分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<Competition> getCompetitionPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return competitionDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     *获取赛事信息列表
     * @param wheres    条件
     * @return 对象列表
     */
    @Override
    public List<Competition> getCompetitionList(QueryParams wheres) {
        return competitionDao.queryList(wheres,0,-1,true);
    }

    /**
     * 根据赛事信息code查找赛事信息
     * @param competitionCode 赛事信息code
     * @return 赛事信息对象
     */
    @Override
    public Competition getCompetitionByCode(String competitionCode) {
        if (LolUtils.isEmptyOrNull(competitionCode))
            throw new IllegalArgumentException("根据code查找赛事信息信息时，Code不能为空");
        return competitionDao.getObject(competitionCode,true);
    }

    /**
     * 添加赛事信息信息
     * @param competition 赛事信息对象
     * @return 赛事信息对象
     */
    @Override
    public Competition createCompetition(Competition competition) {
        if (competition==null)
            throw new IllegalArgumentException("新增赛事信息时，赛事信息对象不能为空或者null");
        if(LolUtils.isEmptyOrNull(competition.getCompetitionName()))
            throw new IllegalArgumentException("新增赛事信息时，比赛名称不能为空或者null");
        if(LolUtils.isEmptyOrNull(competition.getTeamCodeA()))
            throw new IllegalArgumentException("新增赛事信息时，pk战队A不能为空或者null");
        if(LolUtils.isEmptyOrNull(competition.getTeamCodeB()))
            throw new IllegalArgumentException("新增赛事信息时，pk战队B不能为空或者null");
        if (teamService.getTeamByCode(competition.getTeamCodeA())==null)
            throw new IllegalArgumentException("新增赛事信息时，pk战队A的战队信息找不到");
        if (teamService.getTeamByCode(competition.getTeamCodeB())==null)
            throw new IllegalArgumentException("新增赛事信息时，pk战队B的战队信息找不到");
        if(competition.getGameStartTime()==null)
            throw new IllegalArgumentException("新增赛事信息时，比赛开始时间不能为空或者null");
        competition.setCode(codeBuilder.getCompetitionCode());
        competition.setCompetitionStatus(CompetitionStatus.未启用);
        competition.setCreateTime(new Date());
        int info=competitionDao.insertObject(competition);
        return info>0?competition:null;
    }

    /**
     * 修改赛事信息信息
     * @param competition 赛事信息对象
     * @return 赛事信息对象
     */
    @Override
    public Competition updateCompetition(Competition competition) {
        if (competition==null)
            throw new IllegalArgumentException("修改赛事信息时，competition对象不能为空");
        if (LolUtils.isEmptyOrNull(competition.getCode()))
            throw new IllegalArgumentException("修改赛事信息时，赛事信息主键不能为空");
        if(LolUtils.isEmptyOrNull(competition.getCompetitionName()))
            throw new IllegalArgumentException("修改赛事信息时，比赛名称不能为空或者null");
        if(LolUtils.isEmptyOrNull(competition.getTeamCodeA()))
            throw new IllegalArgumentException("修改赛事信息时，pk战队A不能为空或者null");
        if(LolUtils.isEmptyOrNull(competition.getTeamCodeB()))
            throw new IllegalArgumentException("修改赛事信息时，pk战队B不能为空或者null");
        if (teamService.getTeamByCode(competition.getTeamCodeA())==null)
            throw new IllegalArgumentException("修改赛事信息时，pk战队A的战队信息找不到");
        if (teamService.getTeamByCode(competition.getTeamCodeB())==null)
            throw new IllegalArgumentException("修改赛事信息时，pk战队B的战队信息找不到");
        if(competition.getGameStartTime()==null)
            throw new IllegalArgumentException("修改赛事信息时，比赛开始时间不能为空或者null");

        Competition team=competitionDao.getObject(competition.getCode(),true);
        if (team==null)
            throw new IllegalArgumentException("修改赛事信息时，找不到赛事信息，code："+competition.getCode());
        if (team.getCompetitionStatus()!=CompetitionStatus.未启用)
            throw new IllegalArgumentException("修改赛事信息时，只有在未启用状态下才能修改信息，code："+competition.getCode());
        int info=competitionDao.updateObject(competition);
        return info>0?competition:null;
    }

    /**
     * 根据code删除指定赛事信息
     * @param code 赛事信息code
     * @return true表示成功 false表示失败
     */
    @Override
    public Boolean deleteCompetition(String code) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("删除指定赛事信息时，赛事信息主键不能为空");
        Competition competition=competitionDao.getObject(code,true);
        if (competition==null)
            return true;
        if (competition.getCompetitionStatus()!=CompetitionStatus.未启用)
            throw new IllegalArgumentException("删除赛事信息时，只有在未启用状态下才能删除信息，code："+competition.getCode());
        int info = competitionDao.deleteObject(code);
        //TODO 删除对应的所有比赛局数信息和玩法信息
        return info>0?true:false;
    }

    /**
     * 操作指定赛事状态
     * @param code 赛事code
     * @param competitionStatus 赛事状态
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean operationCompetitionStatus(String code, CompetitionStatus competitionStatus) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("操作指定赛事的状态时，code不能为空或null");
        if (competitionStatus==null)
            throw new IllegalArgumentException("操作指定赛事的状态时，修改的赛事状态不能为空");
        Competition competition=competitionDao.getObject(code,true);
        if (competition==null)
            throw new IllegalArgumentException("操作指定赛事的状态时，找不到此赛事信息，code："+code);
        competition.setCompetitionStatus(competitionStatus);
        int info=competitionDao.updateObject(competition);
        //TODO 启用和禁用都将控制局数的状态，这是一个总状态
        return info>0?true:false;
    }

    /**
     * 将指定赛事开启
     * @param code 赛事code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean openBetting(String code) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("禁用指定赛事时，code不能为空或null");
        return this.operationCompetitionStatus(code,CompetitionStatus.投注中);
    }

    /**
     * 将指定赛事禁止投注
     * @param code 赛事code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean prohibitBetting(String code) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("解除指定赛事的禁用状态时，code不能为空或null");
        return this.operationCompetitionStatus(code,CompetitionStatus.禁止投注);
    }

    /**
     * 根据战队code判断在比赛战队AB中是否存在
     * @param teamCode 战队测试
     * @return true表示操作存在 false表示操作不存在
     */
    @Override
    public boolean isExistCompetitionByTeam(String teamCode) {
        if (LolUtils.isEmptyOrNull(teamCode))
            throw new IllegalArgumentException("根据战队code判断在比赛战队AB中是否存在，teamCode不能为空或null");
        QueryParams queryParams=new QueryParams();
        queryParams.addMulAttrParameter("teamCodeA",teamCode);
        queryParams.addMulAttrParameter("teamCodeB",teamCode);
        List<Competition> competitionList = competitionDao.queryList(queryParams,0,-1,true);
        return (competitionList!=null&&competitionList.size()>0)?true:false;
    }

}
