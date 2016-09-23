package com.lolluckyman.business.playrecord.controller;

import com.lolluckyman.business.competition.service.ICompetitionService;
import com.lolluckyman.business.playrecord.entity.PlayRecord;
import com.lolluckyman.business.playrecord.entity.em.Play;
import com.lolluckyman.business.playrecord.service.IPlayRecordService;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseController;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by PlayRecordistrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/back/playRecord")
public class PlayRecordController extends BaseController {

    public Logger log = Logger.getLogger(PlayRecordController.class);//日志

    @Autowired
    private IPlayRecordService playRecordService;
    @Autowired
    private ICompetitionService competitionService;

    @RequestMapping(value = "/getPlayRecordPageList")
    public Object getPlayRecordPageList(int pageIndex,int pageSize){
        log.info("获取联盟玩法信息信息列表");
        QueryParams queryParams = new QueryParams();
        queryParams.addOrderBy("code", true);
        return result(playRecordService.getPlayRecordPageList(queryParams,pageIndex,pageSize,true));
    }

    @RequestMapping(value = "/getPlayRecordByCode")
    public Object getPlayRecordByCode(String playRecordCode){
        log.info("获取主键为"+playRecordCode+"的联盟玩法信息信息");
        if (LolUtils.isEmptyOrNull(playRecordCode))
            return validationResult(1001,"查询联盟玩法信息信息，playRecordCode不能为空");
        PlayRecord playRecord=playRecordService.getPlayRecordByCode(playRecordCode);
        if (playRecord==null)
            return validationResult(1001,"找不到此"+playRecordCode+"的联盟玩法信息信息");
        return result(playRecord);
    }

    /**
     * 删除指定联盟玩法信息
     * @param playRecordCode
     * @return
     */
    @RequestMapping(value = "/deletePlayRecord")
    public Object deletePlayRecord(String playRecordCode){
        if (LolUtils.isEmptyOrNull(playRecordCode))
            return validationResult(1001,"删除指定联盟玩法信息时，code不能为空或null");
        boolean flag=playRecordService.deletePlayRecord(playRecordCode);
        if (flag){
            return result("删除成功");
        }else{
            return validationResult(1001,"删除失败");
        }
    }

    /**
     * 新增联盟玩法信息信息
     * @param playRecord
     * @return
     */
    @RequestMapping(value = "/createPlayRecord")
    public Object createPlayRecord(PlayRecord playRecord){
        if (playRecord==null)
            return validationResult(1001,"新建联盟玩法信息信息时，联盟玩法信息对象不能为空或null");
//        if(LolUtils.isEmptyOrNull(playRecord.getCompetitionCode()))
//            return validationResult(1001,"新增联盟玩法信息时，关联比赛的code不能为空或者null");
//        if (playRecord.getPlayRecordNum()<=0)
//            return validationResult(1001,"新增联盟玩法信息时，联盟玩法顺序必须是大于零的正整数");
        PlayRecord temp=playRecordService.createPlayRecord(playRecord);
        if (temp==null){
            return validationResult(1001,"新建失败");
        }else {
            return result(temp);
        }
    }

    /**
     * 修改联盟玩法信息信息
     * @param playRecord
     * @return
     *
     *
     */
    @RequestMapping(value = "/updatePlayRecord")
    public Object updatePlayRecord(PlayRecord playRecord){
        if (playRecord==null)
            return validationResult(1001,"修改联盟玩法信息信息时，联盟玩法信息对象不能为空或null");
        if (LolUtils.isEmptyOrNull(playRecord.getCode()))
            return validationResult(1001,"修改联盟玩法信息信息时，联盟玩法信息主键不能为空或null");

        PlayRecord updatePlayRecord = playRecordService.getPlayRecordByCode(playRecord.getCode());
        if (updatePlayRecord==null)
            return validationResult(1001,"修改联盟玩法信息信息时，找不到此联盟玩法信息信息code："+playRecord.getCode());
        updatePlayRecord.setPlay(playRecord.getPlay());
        updatePlayRecord.setContent(playRecord.getContent());
        updatePlayRecord.setForeignKey(playRecord.getForeignKey());
        PlayRecord temp=playRecordService.updatePlayRecord(updatePlayRecord);
        if (temp==null){
            return validationResult(1001,"修改失败");
        }else {
            return result(temp);
        }
    }

    /**
     * 获取所有奖品类型
     * @return
     */
    @RequestMapping(value = "/getPlay")
    public Object getPlay(){
        return result(Play.getAllConvertName());
    }

}
