package com.lolluckyman.lolbefore;

import com.lolluckyman.business.bettingrecord.entity.BettingRecord;
import com.lolluckyman.business.bettingrecord.entity.em.BettingRecordStatus;
import com.lolluckyman.business.bettingrecord.service.IBettingRecordService;
import com.lolluckyman.business.competition.entity.Competition;
import com.lolluckyman.business.competition.service.ICompetitionService;
import com.lolluckyman.business.playrecord.entity.PlayRecord;
import com.lolluckyman.business.playrecord.entity.em.Play;
import com.lolluckyman.business.playrecord.service.IPlayRecordService;
import com.lolluckyman.business.restrain.entity.Restrain;
import com.lolluckyman.business.restrain.service.IRestrainService;
import com.lolluckyman.business.team.entity.Team;
import com.lolluckyman.business.team.service.ITeamService;
import com.lolluckyman.lolbefore.model.BettingRecordParamsModel;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseController;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/lol/bettingrecord/jsp")
public class LolBettingRecordControllerJSP extends BaseController {

    public Logger log = Logger.getLogger(LolBettingRecordControllerJSP.class);//日志

    @Autowired
    private IBettingRecordService bettingRecordService;
    @Autowired
    private IPlayRecordService playRecordService;
    @Autowired
    private ICompetitionService competitionService;
    @Autowired
    private IRestrainService restrainService;
    @Autowired
    private ITeamService teamService;

    /**
     * 获取当前用户竞猜记录
     */
    @RequestMapping(value = "/getBettingRecordList")
    public ModelAndView getBettingRecordList(HttpServletRequest request,@RequestParam(required=false,defaultValue="0")int status){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/lolluckman/bettingrecord/bettingRecordList");

        List<BettingRecordParamsModel> bettingRecordParamsModelList=new ArrayList<>();

        QueryParams queryParams=new QueryParams();
        queryParams.addOrderBy("bettingRecordTime",true);
        queryParams.addParameter("accountCode", LolUtils.getCurrentAccount(request).getCode());
        if (status==1){
            queryParams.addParameter("bettingRecordStatus", BettingRecordStatus.已结算);
        }else if (status==2){
            queryParams.addParameter("bettingRecordStatus", BettingRecordStatus.未结算);
        }else if (status==3){
            queryParams.addParameter("bettingRecordStatus", BettingRecordStatus.用户撤注);
        }
        List<BettingRecord> bettingRecordList=bettingRecordService.getBettingRecordList(queryParams);

        for (BettingRecord bettingRecord:bettingRecordList){
            PlayRecord playRecord=playRecordService.getPlayRecordByCode(bettingRecord.getPlayRecordCode());
            if (playRecord==null)
                throw new IllegalArgumentException("没有找到此玩法信息");
            Competition competition=null;
            if (playRecord.getPlay()== Play.总局输赢||playRecord.getPlay()== Play.比分){
                competition=competitionService.getCompetitionByCode(playRecord.getForeignKey());
            }else {
                Restrain restrain=restrainService.getRestrainByCode(playRecord.getForeignKey());
                if (restrain==null)
                    throw new IllegalArgumentException("没有找到此局比赛信息");
                competition=competitionService.getCompetitionByCode(restrain.getCompetitionCode());
            }
            if (competition==null)
                throw new IllegalArgumentException("没有找到此比赛信息");
            Team teamA=teamService.getTeamByCode(competition.getTeamCodeA());
            Team teamB=teamService.getTeamByCode(competition.getTeamCodeB());
            if (teamA==null||teamB==null)
                throw new IllegalArgumentException("找不到比赛对战双方的信息");
            String playRecordResult=this.getPlayResultInfo(playRecord,bettingRecord.getBetting());
            if (LolUtils.isEmptyOrNull(playRecordResult))
                throw new IllegalArgumentException("此投注玩法有误");
            BettingRecordParamsModel bettingRecordParamsModel=new BettingRecordParamsModel();
            bettingRecordParamsModel.setCompetition(competition);
            bettingRecordParamsModel.setTeamA(teamA);
            bettingRecordParamsModel.setTeamB(teamB);
            bettingRecordParamsModel.setPlayRecord(playRecord);
            bettingRecordParamsModel.setPlayRecordResult(playRecordResult);
            bettingRecordParamsModel.setBettingRecord(bettingRecord);
            bettingRecordParamsModelList.add(bettingRecordParamsModel);
        }


        modelAndView.addObject("bettingRecordList",bettingRecordParamsModelList);
        modelAndView.addObject("status",status);
        return modelAndView;
    }

    private String getPlayResultInfo(PlayRecord playRecord,String betting){
        if (playRecord==null)
            throw new IllegalArgumentException("玩法不能为空");
        Play play=playRecord.getPlay();
        if (play==null||LolUtils.isEmptyOrNull(betting))
            throw new IllegalArgumentException("玩法和投注不能为空");
        String info="";
        if (play==Play.总局输赢){
            String[] temp=betting.split(":");
            info=temp[1]+"("+temp[2]+")";
        }else if (play==Play.单局输赢){
            String[] temp=betting.split(":");
            info=temp[1]+"("+temp[2]+")";
        }else if (play==Play.比分){
            String[] temp=betting.split(";")[1].split(":");
            info=temp[0]+"("+temp[1]+")";
        }else if (play==Play.一血){
            String[] temp=betting.split(":");
            if (temp[0].equals("UP")){
                info="上路("+temp[1]+")";
            }else if (temp[0].equals("CENTER")){
                info="中路("+temp[1]+")";
            }else if (temp[0].equals("DOWN")){
                info="下路组合("+temp[1]+")";
            }else if (temp[0].equals("QT")){
                info="野区("+temp[1]+")";
            }
        }else if (play==Play.一塔){
            String[] temp=betting.split(":");
            if (temp[0].equals("UP")){
                info="上路("+temp[1]+")";
            }else if (temp[0].equals("CENTER")){
                info="中路("+temp[1]+")";
            }else if (temp[0].equals("DOWN")){
                info="下路("+temp[1]+")";
            }
        }else if (play==Play.第一亚龙属性){
            String[] temp=betting.split(":");
            if (temp[0].equals("WIND")){
                info="风属性("+temp[1]+")";
            }else if (temp[0].equals("FIRE")){
                info="火属性("+temp[1]+")";
            }else if (temp[0].equals("SOIL")){
                info="土属性("+temp[1]+")";
            }else if (temp[0].equals("WATER")){
                info="水属性("+temp[1]+")";
            }
        }else if (play==Play.一小龙){
            String[] temp=betting.split(":");
            if (temp.length==3){
                info=temp[1]+"("+temp[2]+")";
            }else {
                info="均无("+temp[1]+")";
            }
        }else if (play==Play.小龙数量){
            String[] temp=betting.split(":");
            if (temp.length==3){
                info=temp[1]+"("+temp[2]+")";
            }else {
                info="相同("+temp[1]+")";
            }
        }else if (play==Play.一大龙){
            String[] temp=betting.split(":");
            if (temp.length==3){
                info=temp[1]+"("+temp[2]+")";
            }else {
                info="均无("+temp[1]+")";
            }
        }else if (play==Play.一峡谷先锋){
            String[] temp=betting.split(":");
            if (temp.length==3){
                info=temp[1]+"("+temp[2]+")";
            }else {
                info="均无("+temp[1]+")";
            }
        }else if (play==Play.单局四杀){
            String[] temp=betting.split(":");
            if (temp.length==3){
                info=temp[1]+"("+temp[2]+")";
            }else {
                info="均无("+temp[1]+")";
            }
        }else if (play==Play.单局五杀){
            String[] temp=betting.split(":");
            if (temp.length==3){
                info=temp[1]+"("+temp[2]+")";
            }else {
                info="均无("+temp[1]+")";
            }
        }else if (play==Play.率先十杀){
            String[] temp=betting.split(":");
            if (temp.length==3){
                info=temp[1]+"("+temp[2]+")";
            }else {
                info="均无("+temp[1]+")";
            }
        }else if (play==Play.单局超神){
            String[] temp=betting.split(":");
            if (temp.length==3){
                info=temp[1]+"("+temp[2]+")";
            }else {
                info="均无("+temp[1]+")";
            }
        }else if (play==Play.一水晶){
            String[] temp=betting.split(":");
            if (temp[0].equals("UP")){
                info="上路("+temp[1]+")";
            }else if (temp[0].equals("CENTER")){
                info="中路("+temp[1]+")";
            }else if (temp[0].equals("DOWN")){
                info="下路("+temp[1]+")";
            }
        }else if (play==Play.单方人头数单双){
            String[] temp=betting.split(":");
            info=temp[1]+temp[2]+"("+temp[3]+")";
        }else if (play==Play.总人头数单双){
            String[] temp=betting.split(":");
            if (temp[0].equals("SINGULAR")){
                info="单数("+temp[1]+")";
            }else if (temp[0].equals("DUAL")){
                info="双数("+temp[1]+")";
            }
        }
        return info;
    }
}
