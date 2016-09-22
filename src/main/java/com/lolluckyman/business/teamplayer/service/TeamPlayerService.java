package com.lolluckyman.business.teamplayer.service;

import com.lolluckyman.business.codebuilder.ICodeBuilder;
import com.lolluckyman.business.team.entity.Team;
import com.lolluckyman.business.team.service.ITeamService;
import com.lolluckyman.business.teamplayer.dao.ITeamPlayerDao;
import com.lolluckyman.business.teamplayer.entity.TeamPlayer;
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
@Service("teamPlayerService")
public class TeamPlayerService extends BaseService implements ITeamPlayerService {

    public Logger log = Logger.getLogger(TeamPlayerService.class);//日志

    @Autowired
    private ITeamPlayerDao teamPlayerDao;
    @Autowired
    private ICodeBuilder codeBuilder;
    @Autowired
    private ITeamService teamService;

    /**
     *获取战队成员分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<TeamPlayer> getTeamPlayerPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return teamPlayerDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     * 根据战队成员code查找战队成员
     * @param teamPlayerCode 战队成员code
     * @return 战队成员对象
     */
    @Override
    public TeamPlayer getTeamPlayerByCode(String teamPlayerCode) {
        if (LolUtils.isEmptyOrNull(teamPlayerCode))
            throw new IllegalArgumentException("根据code查找战队成员信息时，Code不能为空");
        return teamPlayerDao.getObject(teamPlayerCode,true);
    }

    /**
     * 添加战队成员信息
     * @param teamPlayer 战队成员对象
     * @return 战队成员对象
     */
    @Override
    public TeamPlayer createTeamPlayer(TeamPlayer teamPlayer) {
        if (teamPlayer==null)
            throw new IllegalArgumentException("新增战队成员时，战队成员对象不能为空或者null");
        if(LolUtils.isEmptyOrNull(teamPlayer.getChinaName()))
            teamPlayer.setChinaName("无");
        if(LolUtils.isEmptyOrNull(teamPlayer.getEnglishName()))
            teamPlayer.setEnglishName("无");
        if(LolUtils.isEmptyOrNull(teamPlayer.getAlias()))
            teamPlayer.setAlias("无");
        if(LolUtils.isEmptyOrNull(teamPlayer.getTeamLocation()))
            throw new IllegalArgumentException("新增战队成员时，战队位置不能为空或者null");
        if (LolUtils.isEmptyOrNull(teamPlayer.getTemaCode()))
            throw new IllegalArgumentException("新增战队成员时，所属战队编号不能为空或者null");
        Team team=teamService.getTeamByCode(teamPlayer.getTemaCode());
        if (team==null)
            throw new IllegalArgumentException("新增战队成员时，找不到所属战队信息，teamCode："+teamPlayer.getTemaCode());
        teamPlayer.setCode(codeBuilder.getTeamPlayerCode());
        teamPlayer.setCreateTime(new Date());
        int info=teamPlayerDao.insertObject(teamPlayer);
        return info>0?teamPlayer:null;
    }

    /**
     * 修改战队成员信息
     * @param teamPlayer 战队成员对象
     * @return 战队成员对象
     */
    @Override
    public TeamPlayer updateTeamPlayer(TeamPlayer teamPlayer) {
        if (teamPlayer==null)
            throw new IllegalArgumentException("修改战队成员时，teamPlayer对象不能为空");
        if (LolUtils.isEmptyOrNull(teamPlayer.getCode()))
            throw new IllegalArgumentException("修改战队成员时，战队成员主键不能为空");
        if(LolUtils.isEmptyOrNull(teamPlayer.getChinaName()))
            teamPlayer.setChinaName("无");
        if(LolUtils.isEmptyOrNull(teamPlayer.getEnglishName()))
            teamPlayer.setEnglishName("无");
        if(LolUtils.isEmptyOrNull(teamPlayer.getAlias()))
            teamPlayer.setAlias("无");
        if(LolUtils.isEmptyOrNull(teamPlayer.getTeamLocation()))
            throw new IllegalArgumentException("修改战队成员时，战队位置不能为空或者null");
        if (LolUtils.isEmptyOrNull(teamPlayer.getTemaCode()))
            throw new IllegalArgumentException("修改战队成员时，所属战队编号不能为空或者null");
        Team team=teamService.getTeamByCode(teamPlayer.getTemaCode());
        if (team==null)
            throw new IllegalArgumentException("新增战队成员时，找不到所属战队信息，teamCode："+teamPlayer.getTemaCode());
        int info=teamPlayerDao.updateObject(teamPlayer);
        return info>0?teamPlayer:null;
    }

    /**
     * 根据code删除指定战队成员
     * @param code 战队成员code
     * @return true表示成功 false表示失败
     */
    @Override
    public Boolean deleteTeamPlayer(String code) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("删除指定战队成员时，战队成员主键不能为空");
        if (teamPlayerDao.getObject(code,true)==null)
            return true;
        int info = teamPlayerDao.deleteObject(code);
        return info>0?true:false;
    }

    /**
     * 根据战队编号删除战队成员信息
     * @param teamCode 战队编号
     * @return true表示成功 false表示失败
     */
    @Override
    public Boolean deleteTeamPlayerListByTeamCode(String teamCode) {
        if (LolUtils.isEmptyOrNull(teamCode))
            throw new IllegalArgumentException("根据战队编号删除战队成员信息时，战队编号不能为空");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("temaCode",teamCode);
        List<TeamPlayer> teamPlayerList=teamPlayerDao.queryList(queryParams,0,-1,true);
        if (teamPlayerList==null || teamPlayerList.size()<=0)
            return true;
        int info=teamPlayerDao.deleteObjectByWhere(queryParams);
        return info>0?true:false;
    }

}
