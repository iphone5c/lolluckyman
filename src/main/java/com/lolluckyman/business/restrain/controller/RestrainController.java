package com.lolluckyman.business.restrain.controller;

import com.lolluckyman.business.competition.entity.Competition;
import com.lolluckyman.business.competition.service.ICompetitionService;
import com.lolluckyman.business.restrain.controller.model.RestrainModel;
import com.lolluckyman.business.restrain.entity.Restrain;
import com.lolluckyman.business.restrain.service.IRestrainService;
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
 * Created by Restrainistrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/back/restrain")
public class RestrainController extends BaseController {

    public Logger log = Logger.getLogger(RestrainController.class);//日志

    @Autowired
    private IRestrainService restrainService;
    @Autowired
    private ICompetitionService competitionService;

    @RequestMapping(value = "/getRestrainPageList")
    public Object getRestrainPageList(int pageIndex,int pageSize){
        log.info("获取局数信息信息列表");
        PageList<RestrainModel> restrainModelPageList=new PageList<>();
        List<RestrainModel> restrainModelList=new ArrayList<>();

        QueryParams queryParams = new QueryParams();
        queryParams.addOrderBy("code",true);
        PageList<Restrain> restrainPageList=restrainService.getRestrainPageList(queryParams,pageIndex,pageSize,true);
        for (Restrain restrain:restrainPageList.getList()){
            RestrainModel restrainModel=new RestrainModel();
            Competition competition=competitionService.getCompetitionByCode(restrain.getCompetitionCode());
            if (competition==null)
                return validationResult(1001,"局数信息信息code："+restrain.getCode()+"找不到关联赛事信息，competitionCode："+restrain.getCompetitionCode());

            restrainModel.setCompetition(competition);
            restrainModel.setRestrain(restrain);
            restrainModelList.add(restrainModel);
        }
        restrainModelPageList.setTotalSize(restrainPageList.getTotalSize());
        restrainModelPageList.setPageCount(restrainPageList.getPageCount());
        restrainModelPageList.setPageIndex(restrainPageList.getPageIndex());
        restrainModelPageList.setPageSize(restrainPageList.getPageSize());
        restrainModelPageList.setList(restrainModelList);
        return result(restrainModelPageList);
    }

    @RequestMapping(value = "/getRestrainByCode")
    public Object getRestrainByCode(String restrainCode){
        log.info("获取主键为"+restrainCode+"的局数信息信息");
        if (LolUtils.isEmptyOrNull(restrainCode))
            return validationResult(1001,"查询局数信息信息，restrainCode不能为空");
        Restrain restrain=restrainService.getRestrainByCode(restrainCode);
        if (restrain==null)
            return validationResult(1001,"找不到此"+restrainCode+"的局数信息信息");
        return result(restrain);
    }

    @RequestMapping(value = "/getRestrainList")
    public Object getRestrainList(){
        log.info("获取的局数列表信息");
        QueryParams queryParams = new QueryParams();
        queryParams.addOrderBy("code",true);
        return result(restrainService.getRestrainList(queryParams));
    }

    @RequestMapping(value = "/getRestrainListToNameValue")
    public Object getRestrainListToNameValue(){
        log.info("获取的局数列表信息");
        QueryParams queryParams = new QueryParams();
        queryParams.addOrderBy("code",true);
        List<Restrain> restrainList=restrainService.getRestrainList(queryParams);
        List<NameValue> nameValueList=new ArrayList<>();
        if (restrainList==null&&restrainList.size()<=0)
            return result(nameValueList);
        for (Restrain restrain:restrainList){
            nameValueList.add(NameValue.create(restrain.getCompetitionCode()+"(第"+restrain.getRestrainNum()+"局)",restrain.getCode()));
        }
        return result(nameValueList);
    }

    /**
     * 删除指定局数信息
     * @param restrainCode
     * @return
     */
    @RequestMapping(value = "/deleteRestrain")
    public Object deleteRestrain(String restrainCode){
        if (LolUtils.isEmptyOrNull(restrainCode))
            return validationResult(1001,"删除指定局数信息时，code不能为空或null");
        boolean flag=restrainService.deleteRestrain(restrainCode);
        if (flag){
            return result("删除成功");
        }else{
            return validationResult(1001,"删除失败");
        }
    }

    /**
     * 新增局数信息信息
     * @param restrain
     * @return
     */
    @RequestMapping(value = "/createRestrain")
    public Object createRestrain(Restrain restrain){
        if (restrain==null)
            return validationResult(1001,"新建局数信息信息时，局数信息对象不能为空或null");
        if(LolUtils.isEmptyOrNull(restrain.getCompetitionCode()))
            return validationResult(1001,"新增局数信息时，关联比赛的code不能为空或者null");
        if (restrain.getRestrainNum()<=0)
            return validationResult(1001,"新增局数信息时，局数顺序必须是大于零的正整数");
        Restrain temp=restrainService.createRestrain(restrain);
        if (temp==null){
            return validationResult(1001,"新建失败");
        }else {
            return result(temp);
        }
    }

    /**
     * 修改局数信息信息
     * @param restrain
     * @return
     *
     *
     */
    @RequestMapping(value = "/updateRestrain")
    public Object updateRestrain(Restrain restrain){
        if (restrain==null)
            return validationResult(1001,"修改局数信息信息时，局数信息对象不能为空或null");
        if (LolUtils.isEmptyOrNull(restrain.getCode()))
            return validationResult(1001,"修改局数信息信息时，局数信息主键不能为空或null");
        if(LolUtils.isEmptyOrNull(restrain.getCompetitionCode()))
            return validationResult(1001,"修改局数信息信息时，关联比赛的code不能为空或者null");
        if (restrain.getRestrainNum()<=0)
            return validationResult(1001,"修改局数信息信息时，局数顺序必须是大于零的正整数");

        Restrain updateRestrain = restrainService.getRestrainByCode(restrain.getCode());
        if (updateRestrain==null)
            return validationResult(1001,"修改局数信息信息时，找不到此局数信息信息code："+restrain.getCode());
        updateRestrain.setCompetitionCode(restrain.getCompetitionCode());
        updateRestrain.setRestrainNum(restrain.getRestrainNum());
        updateRestrain.setDescription(restrain.getDescription());
        Restrain temp=restrainService.updateRestrain(updateRestrain);
        if (temp==null){
            return validationResult(1001,"修改失败");
        }else {
            return result(temp);
        }
    }

    /**
     * 开启投注
     * @param restrainCode
     * @return
     */
    @RequestMapping(value = "/openBetting")
    public Object openBetting(String restrainCode){
        if (LolUtils.isEmptyOrNull(restrainCode))
            return validationResult(1001,"开启局数投注时，code不能为空或null");
        boolean flag=restrainService.openBetting(restrainCode);
        if (flag){
            return result("投注已开启");
        }else{
            return validationResult(1001,"开启投注失败");
        }
    }

    /**
     * 禁止投注
     * @param restrainCode
     * @return
     */
    @RequestMapping(value = "/prohibitBetting")
    public Object prohibitBetting(String restrainCode){
        if (LolUtils.isEmptyOrNull(restrainCode))
            return validationResult(1001,"禁止指定局数投注时，code不能为空或null");
        boolean flag=restrainService.prohibitBetting(restrainCode);
        if (flag){
            return result("禁止投注成功");
        }else{
            return validationResult(1001,"禁止投注失败");
        }
    }

}
