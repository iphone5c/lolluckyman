package com.lolluckyman.business.teamplayer.controller;

import com.lolluckyman.business.team.entity.Team;
import com.lolluckyman.business.team.service.ITeamService;
import com.lolluckyman.business.teamplayer.controller.model.TeamPlayerModel;
import com.lolluckyman.business.teamplayer.entity.TeamPlayer;
import com.lolluckyman.business.teamplayer.service.ITeamPlayerService;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseController;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TeamPlayeristrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/back/teamPlayer")
public class TeamPlayerController extends BaseController {

    public Logger log = Logger.getLogger(TeamPlayerController.class);//日志

    @Autowired
    private ITeamPlayerService teamPlayerService;
    @Autowired
    private ITeamService teamService;

    @RequestMapping(value = "/getTeamPlayerPageList")
    public Object getTeamPlayerPageList(int pageIndex,int pageSize){
        log.info("获取战队成员信息列表");
        PageList<TeamPlayerModel> teamPlayerModelPageList=new PageList<>();
        List<TeamPlayerModel> teamPlayerModelList=new ArrayList<>();

        QueryParams queryParams = new QueryParams();
        queryParams.addOrderBy("code",true);
        PageList<TeamPlayer> teamPlayerPageList=teamPlayerService.getTeamPlayerPageList(queryParams,pageIndex,pageSize,true);
        for (TeamPlayer teamPlayer:teamPlayerPageList.getList()){
            TeamPlayerModel teamPlayerModel=new TeamPlayerModel();
            Team team=teamService.getTeamByCode(teamPlayer.getTemaCode());
            if (team==null)
                return validationResult(1001,"战队成员信息code："+teamPlayer.getCode()+"找不到关联战队信息，accountCode："+teamPlayer.getTemaCode());
            teamPlayerModel.setTeam(team);
            teamPlayerModel.setTeamPlayer(teamPlayer);
            teamPlayerModelList.add(teamPlayerModel);
        }
        teamPlayerModelPageList.setTotalSize(teamPlayerPageList.getTotalSize());
        teamPlayerModelPageList.setPageCount(teamPlayerPageList.getPageCount());
        teamPlayerModelPageList.setPageIndex(teamPlayerPageList.getPageIndex());
        teamPlayerModelPageList.setPageSize(teamPlayerPageList.getPageSize());
        teamPlayerModelPageList.setList(teamPlayerModelList);
        return result(teamPlayerModelPageList);
    }

    @RequestMapping(value = "/getTeamPlayerByCode")
    public Object getTeamPlayerByCode(String teamPlayerCode){
        log.info("获取主键为"+teamPlayerCode+"的战队成员信息");
        if (LolUtils.isEmptyOrNull(teamPlayerCode))
            return validationResult(1001,"查询战队成员信息，teamPlayerCode不能为空");
        TeamPlayer teamPlayer=teamPlayerService.getTeamPlayerByCode(teamPlayerCode);
        if (teamPlayer==null)
            return validationResult(1001,"找不到此"+teamPlayerCode+"的战队成员信息");
        return result(teamPlayer);
    }

    /**
     * 删除指定战队成员
     * @param teamPlayerCode
     * @return
     */
    @RequestMapping(value = "/deleteTeamPlayer")
    public Object deleteTeamPlayer(String teamPlayerCode){
        if (LolUtils.isEmptyOrNull(teamPlayerCode))
            return validationResult(1001,"删除指定战队成员时，code不能为空或null");
        boolean flag=teamPlayerService.deleteTeamPlayer(teamPlayerCode);
        if (flag){
            return result("删除成功");
        }else{
            return validationResult(1001,"删除失败");
        }
    }

    /**
     * 新增战队成员信息
     * @param teamPlayer
     * @return
     */
    @RequestMapping(value = "/createTeamPlayer")
    public Object createTeamPlayer(TeamPlayer teamPlayer){
        if (teamPlayer==null)
            return validationResult(1001,"新建战队成员信息时，战队成员对象不能为空或null");
        if(LolUtils.isEmptyOrNull(teamPlayer.getTeamLocation()))
            return validationResult(1001,"新增战队成员时，战队位置不能为空或者null");
        if (LolUtils.isEmptyOrNull(teamPlayer.getTemaCode()))
            return validationResult(1001,"新增战队成员时，所属战队编号不能为空或者null");
        TeamPlayer temp=teamPlayerService.createTeamPlayer(teamPlayer);
        if (temp==null){
            return validationResult(1001,"新建失败");
        }else {
            return result(temp);
        }
    }

    /**
     * 修改战队成员信息
     * @param teamPlayer
     * @return
     *
     *
     */
    @RequestMapping(value = "/updateTeamPlayer")
    public Object updateTeamPlayer(TeamPlayer teamPlayer){
        if (teamPlayer==null)
            return validationResult(1001,"修改战队成员信息时，战队成员对象不能为空或null");
        if (LolUtils.isEmptyOrNull(teamPlayer.getCode()))
            return validationResult(1001,"修改战队成员信息时，战队成员主键不能为空或null");
        if(LolUtils.isEmptyOrNull(teamPlayer.getTeamLocation()))
            return validationResult(1001,"修改战队成员信息时，战队位置不能为空或者null");
        if (LolUtils.isEmptyOrNull(teamPlayer.getTemaCode()))
            return validationResult(1001,"修改战队成员信息时，所属战队编号不能为空或者null");
        TeamPlayer updateTeamPlayer = teamPlayerService.getTeamPlayerByCode(teamPlayer.getCode());
        if (updateTeamPlayer==null)
            return validationResult(1001,"修改战队成员信息时，找不到此战队成员信息code："+teamPlayer.getCode());
        updateTeamPlayer.setChinaName(teamPlayer.getChinaName());
        updateTeamPlayer.setEnglishName(teamPlayer.getEnglishName());
        updateTeamPlayer.setAlias(teamPlayer.getAlias());
        updateTeamPlayer.setTeamLocation(teamPlayer.getTeamLocation());
        updateTeamPlayer.setTemaCode(teamPlayer.getTemaCode());
        updateTeamPlayer.setSkilledLocation(teamPlayer.getSkilledLocation());
        updateTeamPlayer.setSkilledHero(teamPlayer.getSkilledHero());
        updateTeamPlayer.setDescription(teamPlayer.getDescription());
        TeamPlayer temp=teamPlayerService.updateTeamPlayer(updateTeamPlayer);
        if (temp==null){
            return validationResult(1001,"修改失败");
        }else {
            return result(temp);
        }
    }

}
