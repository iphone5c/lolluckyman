package com.lolluckyman.lolbefore;

import com.lolluckyman.business.competition.controller.model.CompetitionModel;
import com.lolluckyman.business.competition.entity.Competition;
import com.lolluckyman.business.competition.service.ICompetitionService;
import com.lolluckyman.business.restrain.entity.Restrain;
import com.lolluckyman.business.restrain.service.IRestrainService;
import com.lolluckyman.business.team.entity.Team;
import com.lolluckyman.business.team.service.ITeamService;
import com.lolluckyman.lolbefore.model.CompetitionParamsModel;
import com.lolluckyman.utils.LolConvert;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/lol/main/jsp")
public class LolMainControllerJSP extends BaseController {

    public Logger log = Logger.getLogger(LolMainControllerJSP.class);//日志

    @Autowired
    private ICompetitionService competitionService;
    @Autowired
    private ITeamService teamService;
    @Autowired
    private IRestrainService restrainService;

    /**
     * 首页
     */
    @RequestMapping(value = "/mainIndex")
    public ModelAndView mainIndex(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/lolluckman/main/index");
        Map<String,List<CompetitionParamsModel>> listMap1=new HashMap<>();
        for (int i=0;i<=3;i++){
            String dateInfo="";
            List<Competition> competitionList=null;
            List<CompetitionParamsModel> competitionParamsModelList=new ArrayList<>();
            if (i==0){
                dateInfo="2016-09-30";
                competitionList=competitionService.getCompetitionListByDate(dateInfo);
            }else {
                dateInfo="2016-10-"+i;
                competitionList=competitionService.getCompetitionListByDate(dateInfo);
            }
            if (competitionList==null||competitionList.size()<=0)
                continue;
            for (Competition competition:competitionList){
                List<Restrain> restrainList=restrainService.getRestrainListByCompetition(competition.getCode());

                for(Restrain restrain:restrainList){

                    CompetitionParamsModel competitionParamsModel=new CompetitionParamsModel();
                    Team teamA=teamService.getTeamByCode(competition.getTeamCodeA());
                    Team teamB=teamService.getTeamByCode(competition.getTeamCodeB());
                    competitionParamsModel.setTeamA(teamA);
                    competitionParamsModel.setTeamB(teamB);
                    competitionParamsModel.setRestrain(restrain);

                    competitionParamsModelList.add(competitionParamsModel);
                }
            }
            try {
                Date date = LolConvert.toDate(dateInfo+" 00:00:00",LolConvert.DATEFORMAT_DATETIME_EN_LONG);
                listMap1.put(LolConvert.dateToString(date,LolConvert.DATEFORMAT_DATA_M_D),competitionParamsModelList);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Map<String,List<CompetitionParamsModel>> listMap2=new HashMap<>();
        for (int i=7;i<=10;i++){
            String dateInfo="";
            List<Competition> competitionList=null;
            List<CompetitionParamsModel> competitionParamsModelList=new ArrayList<>();
            dateInfo="2016-10-"+i;
            competitionList=competitionService.getCompetitionListByDate(dateInfo);
            if (competitionList==null||competitionList.size()<=0)
                continue;
            for (Competition competition:competitionList){
                List<Restrain> restrainList=restrainService.getRestrainListByCompetition(competition.getCode());

                for(Restrain restrain:restrainList){

                    CompetitionParamsModel competitionParamsModel=new CompetitionParamsModel();
                    Team teamA=teamService.getTeamByCode(competition.getTeamCodeA());
                    Team teamB=teamService.getTeamByCode(competition.getTeamCodeB());
                    competitionParamsModel.setTeamA(teamA);
                    competitionParamsModel.setTeamB(teamB);
                    competitionParamsModel.setRestrain(restrain);

                    competitionParamsModelList.add(competitionParamsModel);
                }
            }
            try {
                Date date = LolConvert.toDate(dateInfo+" 00:00:00",LolConvert.DATEFORMAT_DATETIME_EN_LONG);
                listMap2.put(LolConvert.dateToString(date,LolConvert.DATEFORMAT_DATA_M_D),competitionParamsModelList);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Map<String,List<CompetitionParamsModel>> listMap3=new HashMap<>();
        for (int i=14;i<=17;i++){
            String dateInfo="";
            List<Competition> competitionList=null;
            List<CompetitionParamsModel> competitionParamsModelList=new ArrayList<>();
            dateInfo="2016-10-"+i;
            competitionList=competitionService.getCompetitionListByDate(dateInfo);
            if (competitionList==null||competitionList.size()<=0)
                continue;
            for (Competition competition:competitionList){
                List<Restrain> restrainList=restrainService.getRestrainListByCompetition(competition.getCode());

                for(Restrain restrain:restrainList){

                    CompetitionParamsModel competitionParamsModel=new CompetitionParamsModel();
                    Team teamA=teamService.getTeamByCode(competition.getTeamCodeA());
                    Team teamB=teamService.getTeamByCode(competition.getTeamCodeB());
                    competitionParamsModel.setTeamA(teamA);
                    competitionParamsModel.setTeamB(teamB);
                    competitionParamsModel.setRestrain(restrain);

                    competitionParamsModelList.add(competitionParamsModel);
                }
            }
            try {
                Date date = LolConvert.toDate(dateInfo+" 00:00:00",LolConvert.DATEFORMAT_DATETIME_EN_LONG);
                listMap3.put(LolConvert.dateToString(date,LolConvert.DATEFORMAT_DATA_M_D),competitionParamsModelList);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Map<String,List<CompetitionParamsModel>> listMap4=new HashMap<>();
        for (int i=22;i<=23;i++){
            String dateInfo="";
            List<Competition> competitionList=null;
            List<CompetitionParamsModel> competitionParamsModelList=new ArrayList<>();
            dateInfo="2016-10-"+i;
            competitionList=competitionService.getCompetitionListByDate(dateInfo);
            if (competitionList==null||competitionList.size()<=0)
                continue;
            for (Competition competition:competitionList){
                List<Restrain> restrainList=restrainService.getRestrainListByCompetition(competition.getCode());

                for(Restrain restrain:restrainList){

                    CompetitionParamsModel competitionParamsModel=new CompetitionParamsModel();
                    Team teamA=teamService.getTeamByCode(competition.getTeamCodeA());
                    Team teamB=teamService.getTeamByCode(competition.getTeamCodeB());
                    competitionParamsModel.setTeamA(teamA);
                    competitionParamsModel.setTeamB(teamB);
                    competitionParamsModel.setRestrain(restrain);

                    competitionParamsModelList.add(competitionParamsModel);
                }
            }
            try {
                Date date = LolConvert.toDate(dateInfo+" 00:00:00",LolConvert.DATEFORMAT_DATETIME_EN_LONG);
                listMap4.put(LolConvert.dateToString(date,LolConvert.DATEFORMAT_DATA_M_D),competitionParamsModelList);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Map<String,List<CompetitionParamsModel>> listMap5=new HashMap<>();
        for (int i=30;i<=30;i++){
            String dateInfo="";
            List<Competition> competitionList=null;
            List<CompetitionParamsModel> competitionParamsModelList=new ArrayList<>();
            dateInfo="2016-10-"+i;
            competitionList=competitionService.getCompetitionListByDate(dateInfo);
            if (competitionList==null||competitionList.size()<=0)
                continue;
            for (Competition competition:competitionList){
                List<Restrain> restrainList=restrainService.getRestrainListByCompetition(competition.getCode());

                for(Restrain restrain:restrainList){

                    CompetitionParamsModel competitionParamsModel=new CompetitionParamsModel();
                    Team teamA=teamService.getTeamByCode(competition.getTeamCodeA());
                    Team teamB=teamService.getTeamByCode(competition.getTeamCodeB());
                    competitionParamsModel.setTeamA(teamA);
                    competitionParamsModel.setTeamB(teamB);
                    competitionParamsModel.setRestrain(restrain);

                    competitionParamsModelList.add(competitionParamsModel);
                }
            }
            try {
                Date date = LolConvert.toDate(dateInfo+" 00:00:00",LolConvert.DATEFORMAT_DATETIME_EN_LONG);
                listMap5.put(LolConvert.dateToString(date,LolConvert.DATEFORMAT_DATA_M_D),competitionParamsModelList);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        modelAndView.addObject("competitionList1",listMap1);
        modelAndView.addObject("competitionList2",listMap2);
        modelAndView.addObject("competitionList3",listMap3);
        modelAndView.addObject("competitionList4",listMap4);
        modelAndView.addObject("competitionList5",listMap5);
        return modelAndView;
    }

    /**
     * 选择比赛队伍
     */
    @RequestMapping(value = "/mainIndexFrameTeam")
    public ModelAndView mainIndexFrameTeam(String teamCode){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/lolluckman/main/play");
        if (LolUtils.isEmptyOrNull(teamCode))
            throw new IllegalArgumentException("请选择队伍");
        CompetitionParamsModel competitionParamsModel=new CompetitionParamsModel();
        Restrain restrain = restrainService.getRestrainByCode(teamCode);
        Competition competition = competitionService.getCompetitionByCode(restrain.getCompetitionCode());
        Team teamA=teamService.getTeamByCode(competition.getTeamCodeA());
        Team teamB=teamService.getTeamByCode(competition.getTeamCodeB());
        competitionParamsModel.setTeamA(teamA);
        competitionParamsModel.setTeamB(teamB);

        modelAndView.addObject("competition",competitionParamsModel);
        modelAndView.addObject("restrainCode",teamCode);
        return modelAndView;

    }
}


