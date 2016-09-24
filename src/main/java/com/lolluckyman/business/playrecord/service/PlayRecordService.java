package com.lolluckyman.business.playrecord.service;

import com.lolluckyman.business.bettingrecord.service.IBettingRecordService;
import com.lolluckyman.business.codebuilder.ICodeBuilder;
import com.lolluckyman.business.competition.service.ICompetitionService;
import com.lolluckyman.business.playrecord.dao.IPlayRecordDao;
import com.lolluckyman.business.playrecord.entity.PlayRecord;
import com.lolluckyman.business.playrecord.entity.em.PlayRecordStatus;
import com.lolluckyman.business.team.service.ITeamService;
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
@Service("playRecordService")
public class PlayRecordService extends BaseService implements IPlayRecordService {

    public Logger log = Logger.getLogger(PlayRecordService.class);//日志

    @Autowired
    private IPlayRecordDao playRecordDao;
    @Autowired
    private ICodeBuilder codeBuilder;
    @Autowired
    private ITeamService teamService;
    @Autowired
    private ICompetitionService competitionService;
    @Autowired
    private IBettingRecordService bettingRecordService;

    /**
     *获取联盟玩法信息分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<PlayRecord> getPlayRecordPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return playRecordDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     *获取联盟玩法信息列表
     * @param wheres    条件
     * @return 对象列表
     */
    @Override
    public List<PlayRecord> getPlayRecordList(QueryParams wheres) {
        return playRecordDao.queryList(wheres,0,-1,true);
    }

    /**
     * 根据联盟玩法信息code查找联盟玩法信息
     * @param playRecordCode 联盟玩法信息code
     * @return 联盟玩法信息对象
     */
    @Override
    public PlayRecord getPlayRecordByCode(String playRecordCode) {
        if (LolUtils.isEmptyOrNull(playRecordCode))
            throw new IllegalArgumentException("根据code查找联盟玩法信息信息时，Code不能为空");
        return playRecordDao.getObject(playRecordCode,true);
    }

    /**
     * 添加联盟玩法信息信息
     * @param playRecord 联盟玩法信息对象
     * @return 联盟玩法信息对象
     */
    @Override
    public PlayRecord createPlayRecord(PlayRecord playRecord) {
        if (playRecord==null)
            throw new IllegalArgumentException("新增联盟玩法信息时，联盟玩法信息对象不能为空或者null");
        if(LolUtils.isEmptyOrNull(playRecord.getForeignKey()))
            throw new IllegalArgumentException("新增联盟玩法信息时，外键不能为空或者null");
        if(playRecord.getPlay()==null)
            throw new IllegalArgumentException("新增联盟玩法信息时，玩法不能为空或者null");
        if(LolUtils.isEmptyOrNull(playRecord.getContent()))
            throw new IllegalArgumentException("新增联盟玩法信息时，玩法内容不能为空或者null");

        playRecord.setCode(codeBuilder.getPlayRecordCode());
        playRecord.setPlayRecordStatus(PlayRecordStatus.未处理);
        playRecord.setCreateTime(new Date());
        int info=playRecordDao.insertObject(playRecord);
        return info>0?playRecord:null;
    }

    /**
     * 修改联盟玩法信息信息
     * @param playRecord 联盟玩法信息对象
     * @return 联盟玩法信息对象
     */
    @Override
    public PlayRecord updatePlayRecord(PlayRecord playRecord) {
        if (playRecord==null)
            throw new IllegalArgumentException("修改联盟玩法信息时，playRecord对象不能为空");
        if (LolUtils.isEmptyOrNull(playRecord.getCode()))
            throw new IllegalArgumentException("修改联盟玩法信息时，联盟玩法信息主键不能为空");
        if(playRecord.getPlay()==null)
            throw new IllegalArgumentException("修改联盟玩法信息时，玩法不能为空或者null");
        if(LolUtils.isEmptyOrNull(playRecord.getContent()))
            throw new IllegalArgumentException("修改联盟玩法信息时，玩法内容不能为空或者null");

        PlayRecord team=playRecordDao.getObject(playRecord.getCode(),true);
        if (team==null)
            throw new IllegalArgumentException("修改联盟玩法信息时，找不到联盟玩法信息，code："+playRecord.getCode());
        if (team.getPlayRecordStatus()!=PlayRecordStatus.未处理)
            throw new IllegalArgumentException("修改联盟玩法信息时，只有在未处理状态下才能修改信息，code："+playRecord.getCode());
        int info=playRecordDao.updateObject(playRecord);
        return info>0?playRecord:null;
    }

    /**
     * 根据code删除指定联盟玩法信息
     * @param code 联盟玩法信息code
     * @return true表示成功 false表示失败
     */
    @Override
    public Boolean deletePlayRecord(String code) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("删除指定联盟玩法信息时，联盟玩法信息主键不能为空");
        PlayRecord playRecord=playRecordDao.getObject(code,true);
        if (playRecord==null)
            return true;
        if (playRecord.getPlayRecordStatus()!=PlayRecordStatus.未处理)
            throw new IllegalArgumentException("删除联盟玩法信息时，只有在未处理状态下才能删除信息，code："+playRecord.getCode());
        int info = playRecordDao.deleteObject(code);
        //删除玩法对应的投注记录
        bettingRecordService.deleteBettingRecordByPlayRecordCode(playRecord.getCode());
        return info>0?true:false;
    }

    /**
     * 操作指定联盟玩法状态
     * @param code 联盟玩法code
     * @param playRecordStatus 联盟玩法状态
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean operationPlayRecordStatus(String code, PlayRecordStatus playRecordStatus) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("操作指定联盟玩法的状态时，code不能为空或null");
        if (playRecordStatus==null)
            throw new IllegalArgumentException("操作指定联盟玩法的状态时，修改的联盟玩法状态不能为空");
        PlayRecord playRecord=playRecordDao.getObject(code,true);
        if (playRecord==null)
            throw new IllegalArgumentException("操作指定联盟玩法的状态时，找不到此联盟玩法信息，code："+code);
        playRecord.setPlayRecordStatus(playRecordStatus);
        int info=playRecordDao.updateObject(playRecord);
        return info>0?true:false;
    }

}
