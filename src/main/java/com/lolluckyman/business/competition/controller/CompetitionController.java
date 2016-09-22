package com.lolluckyman.business.competition.controller;

import com.lolluckyman.business.competition.controller.model.CompetitionModel;
import com.lolluckyman.business.competition.entity.Competition;
import com.lolluckyman.business.competition.service.ICompetitionService;
import com.lolluckyman.business.team.entity.Team;
import com.lolluckyman.business.team.service.ITeamService;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseController;
import com.lolluckyman.utils.core.NameValue;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Competitionistrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/back/competition")
public class CompetitionController extends BaseController {

    public Logger log = Logger.getLogger(CompetitionController.class);//日志

    @Autowired
    private ICompetitionService competitionService;
    @Autowired
    private ITeamService teamService;

    @RequestMapping(value = "/getCompetitionPageList")
    public Object getCompetitionPageList(int pageIndex,int pageSize){
        log.info("获取赛事信息信息列表");
        PageList<CompetitionModel> competitionModelPageList=new PageList<>();
        List<CompetitionModel> competitionModelList=new ArrayList<>();

        QueryParams queryParams = new QueryParams();
        queryParams.addOrderBy("code",true);
        PageList<Competition> competitionPageList=competitionService.getCompetitionPageList(queryParams,pageIndex,pageSize,true);
        for (Competition competition:competitionPageList.getList()){
            CompetitionModel competitionModel=new CompetitionModel();
            Team teamA=teamService.getTeamByCode(competition.getTeamCodeA());
            Team teamB=teamService.getTeamByCode(competition.getTeamCodeB());
            if (teamA==null)
                return validationResult(1001,"赛事信息信息code："+competition.getCode()+"找不到关联赛事A信息，accountCode："+competition.getTeamCodeA());
            if (teamB==null)
                return validationResult(1001,"赛事信息信息code："+competition.getCode()+"找不到关联赛事B信息，accountCode："+competition.getTeamCodeB());

            competitionModel.setTeamA(teamA);
            competitionModel.setTeamB(teamB);
            competitionModel.setCompetition(competition);
            competitionModelList.add(competitionModel);
        }
        competitionModelPageList.setTotalSize(competitionPageList.getTotalSize());
        competitionModelPageList.setPageCount(competitionPageList.getPageCount());
        competitionModelPageList.setPageIndex(competitionPageList.getPageIndex());
        competitionModelPageList.setPageSize(competitionPageList.getPageSize());
        competitionModelPageList.setList(competitionModelList);
        return result(competitionModelPageList);
    }

    @RequestMapping(value = "/getCompetitionList")
    public Object getCompetitionList(){
        log.info("获取的赛事列表信息");
        QueryParams queryParams = new QueryParams();
        queryParams.addOrderBy("code",true);
        return result(competitionService.getCompetitionList(queryParams));
    }

    @RequestMapping(value = "/getCompetitionListToNameValue")
    public Object getCompetitionListToNameValue(){
        log.info("获取的赛事列表信息");
        QueryParams queryParams = new QueryParams();
        queryParams.addOrderBy("code",true);
        List<Competition> competitionList=competitionService.getCompetitionList(queryParams);
        List<NameValue> nameValueList=new ArrayList<>();
        if (competitionList==null&&competitionList.size()<=0)
            return result(nameValueList);
        for (Competition competition:competitionList){
            nameValueList.add(NameValue.create(competition.getCompetitionName()+"("+teamService.getTeamByCode(competition.getTeamCodeA()).getChinaName()+"-"+teamService.getTeamByCode(competition.getTeamCodeB()).getChinaName()+")",competition.getCode()));
        }
        return result(nameValueList);
    }

    @RequestMapping(value = "/getCompetitionByCode")
    public Object getCompetitionByCode(String competitionCode){
        log.info("获取主键为"+competitionCode+"的赛事信息信息");
        if (LolUtils.isEmptyOrNull(competitionCode))
            return validationResult(1001,"查询赛事信息信息，competitionCode不能为空");
        Competition competition=competitionService.getCompetitionByCode(competitionCode);
        if (competition==null)
            return validationResult(1001,"找不到此"+competitionCode+"的赛事信息信息");
        return result(competition);
    }

    /**
     * 删除指定赛事信息
     * @param competitionCode
     * @return
     */
    @RequestMapping(value = "/deleteCompetition")
    public Object deleteCompetition(String competitionCode){
        if (LolUtils.isEmptyOrNull(competitionCode))
            return validationResult(1001,"删除指定赛事信息时，code不能为空或null");
        boolean flag=competitionService.deleteCompetition(competitionCode);
        if (flag){
            return result("删除成功");
        }else{
            return validationResult(1001,"删除失败");
        }
    }

    /**
     * 新增赛事信息信息
     * @param competition
     * @return
     */
    @RequestMapping(value = "/createCompetition")
    public Object createCompetition(Competition competition){
        if (competition==null)
            return validationResult(1001,"新建赛事信息信息时，赛事信息对象不能为空或null");
        if(LolUtils.isEmptyOrNull(competition.getCompetitionName()))
            return validationResult(1001,"新增赛事信息时，比赛名称不能为空或者null");
        if (LolUtils.isEmptyOrNull(competition.getTeamCodeA()))
            return validationResult(1001,"新增赛事信息时，pk赛事A不能为空或者null");
        if (LolUtils.isEmptyOrNull(competition.getTeamCodeB()))
            return validationResult(1001,"新增赛事信息时，pk赛事B不能为空或者null");
        if(competition.getGameStartTime()==null)
            return validationResult(1001,"新增赛事信息时，比赛开始时间不能为空或者null");
        Competition temp=competitionService.createCompetition(competition);
        if (temp==null){
            return validationResult(1001,"新建失败");
        }else {
            return result(temp);
        }
    }

    /**
     * 修改赛事信息信息
     * @param competition
     * @return
     *
     *
     */
    @RequestMapping(value = "/updateCompetition")
    public Object updateCompetition(Competition competition){
        if (competition==null)
            return validationResult(1001,"修改赛事信息信息时，赛事信息对象不能为空或null");
        if (LolUtils.isEmptyOrNull(competition.getCode()))
            return validationResult(1001,"修改赛事信息信息时，赛事信息主键不能为空或null");
        if(LolUtils.isEmptyOrNull(competition.getCompetitionName()))
            return validationResult(1001,"修改赛事信息信息时，比赛名称不能为空或者null");
        if(LolUtils.isEmptyOrNull(competition.getTeamCodeA()))
            return validationResult(1001,"修改赛事信息信息时，pk赛事A不能为空或者null");
        if(LolUtils.isEmptyOrNull(competition.getTeamCodeB()))
            return validationResult(1001,"修改赛事信息信息时，pk赛事B不能为空或者null");
        if (teamService.getTeamByCode(competition.getTeamCodeA())==null)
            return validationResult(1001,"修改赛事信息信息时，pk赛事A的赛事信息找不到");
        if (teamService.getTeamByCode(competition.getTeamCodeB())==null)
            return validationResult(1001,"修改赛事信息信息时，pk赛事B的赛事信息找不到");
        if(competition.getGameStartTime()==null)
            return validationResult(1001,"修改赛事信息信息时，比赛开始时间不能为空或者null");

        Competition updateCompetition = competitionService.getCompetitionByCode(competition.getCode());
        if (updateCompetition==null)
            return validationResult(1001,"修改赛事信息信息时，找不到此赛事信息信息code："+competition.getCode());
        updateCompetition.setCompetitionName(competition.getCompetitionName());
        updateCompetition.setTeamCodeA(competition.getTeamCodeA());
        updateCompetition.setTeamCodeB(competition.getTeamCodeB());
        updateCompetition.setGameStartTime(competition.getGameStartTime());
        updateCompetition.setDescription(competition.getDescription());
        Competition temp=competitionService.updateCompetition(updateCompetition);
        if (temp==null){
            return validationResult(1001,"修改失败");
        }else {
            return result(temp);
        }
    }

    /**
     * 开启投注
     * @param competitionCode
     * @return
     */
    @RequestMapping(value = "/openBetting")
    public Object openBetting(String competitionCode){
        if (LolUtils.isEmptyOrNull(competitionCode))
            return validationResult(1001,"开启赛事投注时，code不能为空或null");
        boolean flag=competitionService.openBetting(competitionCode);
        if (flag){
            return result("投注已开启");
        }else{
            return validationResult(1001,"开启投注失败");
        }
    }

    /**
     * 禁止投注
     * @param competitionCode
     * @return
     */
    @RequestMapping(value = "/prohibitBetting")
    public Object prohibitBetting(String competitionCode){
        if (LolUtils.isEmptyOrNull(competitionCode))
            return validationResult(1001,"禁止指定赛事投注时，code不能为空或null");
        boolean flag=competitionService.prohibitBetting(competitionCode);
        if (flag){
            return result("禁止投注成功");
        }else{
            return validationResult(1001,"禁止投注失败");
        }
    }

}
