package com.lolluckyman.business.team.controller;

import com.lolluckyman.business.team.entity.Team;
import com.lolluckyman.business.team.service.ITeamService;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseController;
import com.lolluckyman.utils.core.NameValue;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teamistrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/back/team")
public class TeamController extends BaseController {

    public Logger log = Logger.getLogger(TeamController.class);//日志

    @Autowired
    private ITeamService teamService;

    @RequestMapping(value = "/getTeamPageList")
    public Object getTeamPageList(int pageIndex,int pageSize){
        log.info("获取战队信息列表");
        QueryParams queryParams = new QueryParams();
        queryParams.addOrderBy("code",true);
        return result(teamService.getTeamPageList(queryParams,pageIndex,pageSize,true));
    }

    @RequestMapping(value = "/getTeamList")
    public Object getTeamList(){
        log.info("获取的战队列表信息");
        QueryParams queryParams = new QueryParams();
        queryParams.addOrderBy("code",true);
        return result(teamService.getTeamList(queryParams));
    }

    @RequestMapping(value = "/getTeamListToNameValue")
    public Object getTeamListToNameValue(){
        log.info("获取的战队列表信息");
        QueryParams queryParams = new QueryParams();
        queryParams.addOrderBy("code",true);
        List<Team> teamList=teamService.getTeamList(queryParams);
        List<NameValue> nameValueList=new ArrayList<>();
        if (teamList==null&&teamList.size()<=0)
            return result(nameValueList);
        for (Team team:teamList){
            nameValueList.add(NameValue.create(team.getChinaName()+"("+team.getEnglishName()+")",team.getCode()));
        }
        return result(nameValueList);
    }

    @RequestMapping(value = "/getTeamByCode")
    public Object getTeamByCode(String teamCode){
        log.info("获取主键为"+teamCode+"的战队信息");
        if (LolUtils.isEmptyOrNull(teamCode))
            return validationResult(1001,"查询战队信息，teamCode不能为空");
        Team team=teamService.getTeamByCode(teamCode);
        if (team==null)
            return validationResult(1001,"找不到此"+teamCode+"的战队信息");
        return result(team);
    }

    /**
     * 删除指定战队
     * @param teamCode
     * @return
     */
    @RequestMapping(value = "/deleteTeam")
    public Object deleteTeam(String teamCode){
        if (LolUtils.isEmptyOrNull(teamCode))
            return validationResult(1001,"删除指定战队时，code不能为空或null");
        teamService.deleteTeam(teamCode);
        return result("删除成功");
    }

    /**
     * 新增战队信息
     * @param team
     * @return
     */
    @RequestMapping(value = "/createTeam")
    public Object createTeam(Team team){
        if (team==null)
            return validationResult(1001,"新建战队信息时，战队对象不能为空或null");
        if (team.getCreateTeamTime()==null)
            return validationResult(1001,"新建战队信息时，建队时间不能为空或者null");
        Team temp=teamService.createTeam(team);
        if (temp==null){
            return validationResult(1001,"新建失败");
        }else {
            return result(temp);
        }
    }

    /**
     * 修改战队信息
     * @param team
     * @return
     */
    @RequestMapping(value = "/updateTeam")
    public Object updateTeam(Team team){
        if (team==null)
            return validationResult(1001,"修改战队信息时，战队对象不能为空或null");
        if (LolUtils.isEmptyOrNull(team.getCode()))
            return validationResult(1001,"修改战队信息时，战队主键不能为空或null");
        if (team.getCreateTeamTime()==null)
            return validationResult(1001,"修改战队信息时，建队时间不能为空或者null");
        Team updateTeam = teamService.getTeamByCode(team.getCode());
        if (updateTeam==null)
            return validationResult(1001,"修改战队信息时，找不到此战队信息code："+team.getCode());
        updateTeam.setChinaName(team.getChinaName());
        updateTeam.setEnglishName(team.getEnglishName());
        updateTeam.setTeamTeacher(team.getTeamTeacher());
        updateTeam.setTeamWeb(team.getTeamWeb());
        updateTeam.setCreateTeamTime(team.getCreateTeamTime());
        Team temp=teamService.updateTeam(updateTeam);
        if (temp==null){
            return validationResult(1001,"修改失败");
        }else {
            return result(temp);
        }
    }

}
