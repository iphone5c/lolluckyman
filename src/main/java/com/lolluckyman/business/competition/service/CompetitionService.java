package com.lolluckyman.business.competition.service;

import com.lolluckyman.business.codebuilder.ICodeBuilder;
import com.lolluckyman.business.competition.dao.ICompetitionDao;
import com.lolluckyman.business.competition.entity.Competition;
import com.lolluckyman.business.competition.entity.em.CompetitionStatus;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseService;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
        if (competitionDao.getObject(code,true)==null)
            return true;
        int info = competitionDao.deleteObject(code);
        //TODO 删除对应的所有比赛局数信息和玩法信息
        return info>0?true:false;
    }

}
