package com.lolluckyman.business.restrain.service;

import com.lolluckyman.business.codebuilder.ICodeBuilder;
import com.lolluckyman.business.competition.service.ICompetitionService;
import com.lolluckyman.business.restrain.dao.IRestrainDao;
import com.lolluckyman.business.restrain.entity.Restrain;
import com.lolluckyman.business.restrain.entity.em.RestrainStatus;
import com.lolluckyman.business.restrain.service.IRestrainService;
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
@Service("restrainService")
public class RestrainService extends BaseService implements IRestrainService {

    public Logger log = Logger.getLogger(RestrainService.class);//日志

    @Autowired
    private IRestrainDao restrainDao;
    @Autowired
    private ICodeBuilder codeBuilder;
    @Autowired
    private ITeamService teamService;
    @Autowired
    private ICompetitionService competitionService;

    /**
     *获取局数信息分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<Restrain> getRestrainPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return restrainDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     *获取局数信息列表
     * @param wheres    条件
     * @return 对象列表
     */
    @Override
    public List<Restrain> getRestrainList(QueryParams wheres) {
        return restrainDao.queryList(wheres,0,-1,true);
    }

    /**
     * 根据局数信息code查找局数信息
     * @param restrainCode 局数信息code
     * @return 局数信息对象
     */
    @Override
    public Restrain getRestrainByCode(String restrainCode) {
        if (LolUtils.isEmptyOrNull(restrainCode))
            throw new IllegalArgumentException("根据code查找局数信息信息时，Code不能为空");
        return restrainDao.getObject(restrainCode,true);
    }

    /**
     * 添加局数信息信息
     * @param restrain 局数信息对象
     * @return 局数信息对象
     */
    @Override
    public Restrain createRestrain(Restrain restrain) {
        if (restrain==null)
            throw new IllegalArgumentException("新增局数信息时，局数信息对象不能为空或者null");
        if(LolUtils.isEmptyOrNull(restrain.getCompetitionCode()))
            throw new IllegalArgumentException("新增局数信息时，关联比赛的code不能为空或者null");
        if (competitionService.getCompetitionByCode(restrain.getCompetitionCode())==null)
            throw new IllegalArgumentException("新增局数信息时，找不到关联比赛的信息competitionCode："+restrain.getCompetitionCode());
        if (restrain.getRestrainNum()<=0)
            throw new IllegalArgumentException("新增局数信息时，局数顺序必须是大于零的正整数");
        if (this.isExisRestrain(restrain.getRestrainNum(),restrain.getCompetitionCode()))
            throw new IllegalArgumentException("新增局数信息时，这场比赛的这一局已经存在，请输入下一局");
        restrain.setCode(codeBuilder.getRestrainCode());
        restrain.setRestrainStatus(RestrainStatus.未启用);
        restrain.setCreateTime(new Date());
        int info=restrainDao.insertObject(restrain);
        return info>0?restrain:null;
    }

    /**
     * 修改局数信息信息
     * @param restrain 局数信息对象
     * @return 局数信息对象
     */
    @Override
    public Restrain updateRestrain(Restrain restrain) {
        if (restrain==null)
            throw new IllegalArgumentException("修改局数信息时，restrain对象不能为空");
        if (LolUtils.isEmptyOrNull(restrain.getCode()))
            throw new IllegalArgumentException("修改局数信息时，局数信息主键不能为空");
        if(LolUtils.isEmptyOrNull(restrain.getCompetitionCode()))
            throw new IllegalArgumentException("修改局数信息时，关联比赛的code不能为空或者null");
        if (competitionService.getCompetitionByCode(restrain.getCompetitionCode())==null)
            throw new IllegalArgumentException("修改局数信息时，找不到关联比赛的信息competitionCode："+restrain.getCompetitionCode());
        if (restrain.getRestrainNum()<=0)
            throw new IllegalArgumentException("修改局数信息时，局数顺序必须是大于零的正整数");
        if (this.isExisRestrain(restrain.getRestrainNum(),restrain.getCompetitionCode()))
            throw new IllegalArgumentException("修改局数信息时，这场比赛的这一局已经存在，请输入下一局");

        Restrain team=restrainDao.getObject(restrain.getCode(),true);
        if (team==null)
            throw new IllegalArgumentException("修改局数信息时，找不到局数信息，code："+restrain.getCode());
        if (team.getRestrainStatus()!=RestrainStatus.未启用)
            throw new IllegalArgumentException("修改局数信息时，只有在未启用状态下才能修改信息，code："+restrain.getCode());
        int info=restrainDao.updateObject(restrain);
        return info>0?restrain:null;
    }

    /**
     * 根据code删除指定局数信息
     * @param code 局数信息code
     * @return true表示成功 false表示失败
     */
    @Override
    public Boolean deleteRestrain(String code) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("删除指定局数信息时，局数信息主键不能为空");
        Restrain restrain=restrainDao.getObject(code,true);
        if (restrain==null)
            return true;
        if (restrain.getRestrainStatus()!=RestrainStatus.未启用)
            throw new IllegalArgumentException("删除局数信息时，只有在未启用状态下才能删除信息，code："+restrain.getCode());
        int info = restrainDao.deleteObject(code);
        //TODO 删除对应的所有比赛局数信息和玩法信息
        return info>0?true:false;
    }

    /**
     * 操作指定局数状态
     * @param code 局数code
     * @param restrainStatus 局数状态
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean operationRestrainStatus(String code, RestrainStatus restrainStatus) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("操作指定局数的状态时，code不能为空或null");
        if (restrainStatus==null)
            throw new IllegalArgumentException("操作指定局数的状态时，修改的局数状态不能为空");
        Restrain restrain=restrainDao.getObject(code,true);
        if (restrain==null)
            throw new IllegalArgumentException("操作指定局数的状态时，找不到此局数信息，code："+code);
        restrain.setRestrainStatus(restrainStatus);
        int info=restrainDao.updateObject(restrain);
        return info>0?true:false;
    }

    /**
     * 将指定局数开启
     * @param code 局数code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean openBetting(String code) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("禁用指定局数时，code不能为空或null");
        return this.operationRestrainStatus(code, RestrainStatus.投注中);
    }

    /**
     * 将指定局数禁止投注
     * @param code 局数code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean prohibitBetting(String code) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("解除指定局数的禁用状态时，code不能为空或null");
        return this.operationRestrainStatus(code, RestrainStatus.禁止投注);
    }

    /**
     * 根据局数顺序和赛事code判断是否存在局数
     * @param restrainNum 局数顺序
     * @param competitionCode 赛事code
     * @return true表示操作存在 false表示操作不存在
     */
    @Override
    public boolean isExisRestrain(int restrainNum, String competitionCode) {
        if (restrainNum<=0)
            throw new IllegalArgumentException("判断局数是否存在时，局数顺序必须是大于零的正整数");
        if(LolUtils.isEmptyOrNull(competitionCode))
            throw new IllegalArgumentException("判断局数是否存在时，赛事competitionCode不能为空或者null");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("restrainNum",restrainNum);
        queryParams.addParameter("competitionCode",competitionCode);
        List<Restrain> restrainList=restrainDao.queryList(queryParams, 0, -1, true);
        return (restrainList==null||restrainList.size()<=0)?false:true;
    }

}
