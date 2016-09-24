package com.lolluckyman.business.bettingrecord.controller;

import com.lolluckyman.business.bettingrecord.entity.BettingRecord;
import com.lolluckyman.business.bettingrecord.entity.em.BettingRecordStatus;
import com.lolluckyman.business.bettingrecord.service.IBettingRecordService;
import com.lolluckyman.business.competition.service.ICompetitionService;
import com.lolluckyman.business.topupwithdrawal.entity.em.BusinessType;
import com.lolluckyman.business.topupwithdrawal.entity.em.DisposalStatus;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseController;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by BettingRecordistrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/back/bettingrecord")
public class BettingRecordController extends BaseController {

    public Logger log = Logger.getLogger(BettingRecordController.class);//日志

    @Autowired
    private IBettingRecordService bettingRecordService;
    @Autowired
    private ICompetitionService competitionService;

    @RequestMapping(value = "/getBettingRecordPageList")
    public Object getBettingRecordPageList(int pageIndex,int pageSize,String bettingRecordStatus){
        log.info("获取投注信息信息列表");
        QueryParams queryParams = new QueryParams();
        queryParams.addOrderBy("code", true);
        if (!LolUtils.isEmptyOrNull(bettingRecordStatus)){
            if (BettingRecordStatus.isExit(bettingRecordStatus))
                return validationResult(1001,"获取投注信息信息列表，不存在此业务类型。");
            queryParams.addParameter("bettingRecordStatus", bettingRecordStatus);
        }
        return result(bettingRecordService.getBettingRecordPageList(queryParams,pageIndex,pageSize,true));
    }

    @RequestMapping(value = "/getBettingRecordByCode")
    public Object getBettingRecordByCode(String bettingRecordCode){
        log.info("获取主键为"+bettingRecordCode+"的投注信息信息");
        if (LolUtils.isEmptyOrNull(bettingRecordCode))
            return validationResult(1001,"查询投注信息信息，bettingRecordCode不能为空");
        BettingRecord bettingRecord=bettingRecordService.getBettingRecordByCode(bettingRecordCode);
        if (bettingRecord==null)
            return validationResult(1001,"找不到此"+bettingRecordCode+"的投注信息信息");
        return result(bettingRecord);
    }

}
