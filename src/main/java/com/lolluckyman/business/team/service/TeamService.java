package com.lolluckyman.business.team.service;

import com.lolluckyman.business.codebuilder.ICodeBuilder;
import com.lolluckyman.business.competition.service.ICompetitionService;
import com.lolluckyman.business.team.dao.ITeamDao;
import com.lolluckyman.business.team.entity.Team;
import com.lolluckyman.business.teamplayer.service.ITeamPlayerService;
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
@Service("teamService")
public class TeamService extends BaseService implements ITeamService {

    public Logger log = Logger.getLogger(TeamService.class);//日志

    @Autowired
    private ITeamDao teamDao;
    @Autowired
    private ICodeBuilder codeBuilder;
    @Autowired
    private ITeamPlayerService teamPlayerService;
    @Autowired
    private ICompetitionService competitionService;

    /**
     *获取战队分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<Team> getTeamPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return teamDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     *获取战队列表
     * @param wheres    条件
     * @return 对象列表
     */
    @Override
    public List<Team> getTeamList(QueryParams wheres) {
        return teamDao.queryList(wheres,0,-1,true);
    }

    /**
     * 根据战队code查找战队
     * @param teamCode 战队code
     * @return 战队对象
     */
    @Override
    public Team getTeamByCode(String teamCode) {
        if (LolUtils.isEmptyOrNull(teamCode))
            throw new IllegalArgumentException("根据code查找战队信息时，Code不能为空");
        return teamDao.getObject(teamCode,true);
    }

    /**
     * 添加战队信息
     * @param team 战队对象
     * @return 战队对象
     */
    @Override
    public Team createTeam(Team team) {
        if (team==null)
            throw new IllegalArgumentException("新增战队时，战队对象不能为空或者null");
        if(LolUtils.isEmptyOrNull(team.getChinaName()))
            team.setChinaName("无");
        if(LolUtils.isEmptyOrNull(team.getEnglishName()))
            team.setEnglishName("无");
        if(LolUtils.isEmptyOrNull(team.getTeamTeacher()))
            team.setTeamTeacher("无");
        if(LolUtils.isEmptyOrNull(team.getTeamWeb()))
            team.setTeamWeb("无");
        if (team.getCreateTeamTime()==null)
            throw new IllegalArgumentException("新增战队时，建队时间不能为空或者null");
        team.setCode(codeBuilder.getTeamCode());
        team.setCreateTime(new Date());
        int info=teamDao.insertObject(team);
        return info>0?team:null;
    }

    /**
     * 修改战队信息
     * @param team 战队对象
     * @return 战队对象
     */
    @Override
    public Team updateTeam(Team team) {
        if (team==null)
            throw new IllegalArgumentException("修改战队时，team对象不能为空");
        if (LolUtils.isEmptyOrNull(team.getCode()))
            throw new IllegalArgumentException("修改战队时，战队主键不能为空");
        if(LolUtils.isEmptyOrNull(team.getChinaName()))
            team.setChinaName("无");
        if(LolUtils.isEmptyOrNull(team.getEnglishName()))
            team.setEnglishName("无");
        if(LolUtils.isEmptyOrNull(team.getTeamTeacher()))
            team.setTeamTeacher("无");
        if(LolUtils.isEmptyOrNull(team.getTeamWeb()))
            team.setTeamWeb("无");
        if (team.getCreateTeamTime()==null)
            throw new IllegalArgumentException("修改战队时，建队时间不能为空或者null");
        int info=teamDao.updateObject(team);
        return info>0?team:null;
    }

    /**
     * 根据code删除指定战队
     * @param code 战队code
     */
    @Override
    public void deleteTeam(String code) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("删除指定战队时，战队主键不能为空");
        //判断战队是否加入了比赛
        if (competitionService.isExistCompetitionByTeam(code))
            throw new IllegalArgumentException("删除指定战队时，这个战队已经加入了比赛，不能进行删除");
        int info = teamDao.deleteObject(code);
        if (info<0)
            throw new IllegalArgumentException("删除指定战队失败");
        //删除战队对应的战队成员信息
        boolean flag=teamPlayerService.deleteTeamPlayerListByTeamCode(code);
        if (!flag)
            throw new IllegalArgumentException("删除指定战队的战队成员信息失败");
    }

}
