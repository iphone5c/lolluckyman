package com.lolluckyman.business.competition.controller;

import com.lolluckyman.business.competition.service.ICompetitionService;
import com.lolluckyman.utils.core.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Competitionistrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/back/competition")
public class CompetitionController extends BaseController {

    public Logger log = Logger.getLogger(CompetitionController.class);//日志

    @Autowired
    private ICompetitionService competitionService;

//    @RequestMapping(value = "/getCompetitionPageList")
//    public Object getCompetitionPageList(int pageIndex,int pageSize){
//        log.info("获取赛事信息信息列表");
//        PageList<CompetitionModel> competitionModelPageList=new PageList<>();
//        List<CompetitionModel> competitionModelList=new ArrayList<>();
//
//        QueryParams queryParams = new QueryParams();
//        queryParams.addOrderBy("code",true);
//        PageList<Competition> competitionPageList=competitionService.getCompetitionPageList(queryParams,pageIndex,pageSize,true);
//        for (Competition competition:competitionPageList.getList()){
//            CompetitionModel competitionModel=new CompetitionModel();
//            Team team=teamService.getTeamByCode(competition.getTemaCode());
//            if (team==null)
//                return validationResult(1001,"赛事信息信息code："+competition.getCode()+"找不到关联战队信息，accountCode："+competition.getTemaCode());
//            competitionModel.setTeam(team);
//            competitionModel.setCompetition(competition);
//            competitionModelList.add(competitionModel);
//        }
//        competitionModelPageList.setTotalSize(competitionPageList.getTotalSize());
//        competitionModelPageList.setPageCount(competitionPageList.getPageCount());
//        competitionModelPageList.setPageIndex(competitionPageList.getPageIndex());
//        competitionModelPageList.setPageSize(competitionPageList.getPageSize());
//        competitionModelPageList.setList(competitionModelList);
//        return result(competitionModelPageList);
//    }
//
//    @RequestMapping(value = "/getCompetitionByCode")
//    public Object getCompetitionByCode(String competitionCode){
//        log.info("获取主键为"+competitionCode+"的赛事信息信息");
//        if (LolUtils.isEmptyOrNull(competitionCode))
//            return validationResult(1001,"查询赛事信息信息，competitionCode不能为空");
//        Competition competition=competitionService.getCompetitionByCode(competitionCode);
//        if (competition==null)
//            return validationResult(1001,"找不到此"+competitionCode+"的赛事信息信息");
//        return result(competition);
//    }
//
//    /**
//     * 删除指定赛事信息
//     * @param competitionCode
//     * @return
//     */
//    @RequestMapping(value = "/deleteCompetition")
//    public Object deleteCompetition(String competitionCode){
//        if (LolUtils.isEmptyOrNull(competitionCode))
//            return validationResult(1001,"删除指定赛事信息时，code不能为空或null");
//        boolean flag=competitionService.deleteCompetition(competitionCode);
//        if (flag){
//            return result("删除成功");
//        }else{
//            return validationResult(1001,"删除失败");
//        }
//    }
//
//    /**
//     * 新增赛事信息信息
//     * @param competition
//     * @return
//     */
//    @RequestMapping(value = "/createCompetition")
//    public Object createCompetition(Competition competition){
//        if (competition==null)
//            return validationResult(1001,"新建赛事信息信息时，赛事信息对象不能为空或null");
//        if(LolUtils.isEmptyOrNull(competition.getTeamLocation()))
//            return validationResult(1001,"新增赛事信息时，战队位置不能为空或者null");
//        if (LolUtils.isEmptyOrNull(competition.getTemaCode()))
//            return validationResult(1001,"新增赛事信息时，所属战队编号不能为空或者null");
//        Competition temp=competitionService.createCompetition(competition);
//        if (temp==null){
//            return validationResult(1001,"新建失败");
//        }else {
//            return result(temp);
//        }
//    }
//
//    /**
//     * 修改赛事信息信息
//     * @param competition
//     * @return
//     *
//     *
//     */
//    @RequestMapping(value = "/updateCompetition")
//    public Object updateCompetition(Competition competition){
//        if (competition==null)
//            return validationResult(1001,"修改赛事信息信息时，赛事信息对象不能为空或null");
//        if (LolUtils.isEmptyOrNull(competition.getCode()))
//            return validationResult(1001,"修改赛事信息信息时，赛事信息主键不能为空或null");
//        if(LolUtils.isEmptyOrNull(competition.getTeamLocation()))
//            return validationResult(1001,"修改赛事信息信息时，战队位置不能为空或者null");
//        if (LolUtils.isEmptyOrNull(competition.getTemaCode()))
//            return validationResult(1001,"修改赛事信息信息时，所属战队编号不能为空或者null");
//        Competition updateCompetition = competitionService.getCompetitionByCode(competition.getCode());
//        if (updateCompetition==null)
//            return validationResult(1001,"修改赛事信息信息时，找不到此赛事信息信息code："+competition.getCode());
//        updateCompetition.setChinaName(competition.getChinaName());
//        updateCompetition.setEnglishName(competition.getEnglishName());
//        updateCompetition.setAlias(competition.getAlias());
//        updateCompetition.setTeamLocation(competition.getTeamLocation());
//        updateCompetition.setTemaCode(competition.getTemaCode());
//        updateCompetition.setSkilledLocation(competition.getSkilledLocation());
//        updateCompetition.setSkilledHero(competition.getSkilledHero());
//        updateCompetition.setDescription(competition.getDescription());
//        Competition temp=competitionService.updateCompetition(updateCompetition);
//        if (temp==null){
//            return validationResult(1001,"修改失败");
//        }else {
//            return result(temp);
//        }
//    }

}
