package com.lolluckyman.lolbefore;

import com.lolluckyman.business.competition.entity.Competition;
import com.lolluckyman.business.competition.service.ICompetitionService;
import com.lolluckyman.business.playrecord.entity.PlayRecord;
import com.lolluckyman.business.playrecord.service.IPlayRecordService;
import com.lolluckyman.business.restrain.entity.Restrain;
import com.lolluckyman.business.restrain.service.IRestrainService;
import com.lolluckyman.business.team.entity.Team;
import com.lolluckyman.business.team.service.ITeamService;
import com.lolluckyman.lolbefore.model.CompetitionParamsModel;
import com.lolluckyman.utils.LolConvert;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseController;
import com.lolluckyman.utils.core.QueryParams;
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
@RequestMapping(value = "/lol/main/json")
public class LolMainControllerJSON extends BaseController {

    public Logger log = Logger.getLogger(LolMainControllerJSON.class);//日志

    @Autowired
    private ICompetitionService competitionService;
    @Autowired
    private ITeamService teamService;
    @Autowired
    private IRestrainService restrainService;
    @Autowired
    private IPlayRecordService playRecordService;

    /**
     * 选择比赛队伍玩法
     */
    @RequestMapping(value = "/mainIndexFrameTeam")
    public Object mainIndexFrameTeam(String restrainCode){
        if (LolUtils.isEmptyOrNull(restrainCode))
            throw new IllegalArgumentException("请选择队伍");
        Restrain restrain = restrainService.getRestrainByCode(restrainCode);
        Competition competition = competitionService.getCompetitionByCode(restrain.getCompetitionCode());
        QueryParams queryParams = new QueryParams();
        queryParams.addMulAttrParameter("foreignKey",restrain.getCode());
        queryParams.addMulAttrParameter("foreignKey",competition.getCode());
        List<PlayRecord> playRecordList=playRecordService.getPlayRecordList(queryParams);

        return result(playRecordList);

    }
}


