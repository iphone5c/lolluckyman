package com.lolluckyman.business.topupwithdrawal.controller;

import com.lolluckyman.business.competition.service.ICompetitionService;
import com.lolluckyman.business.playrecord.entity.em.Play;
import com.lolluckyman.business.topupwithdrawal.entity.TopUpWithdrawal;
import com.lolluckyman.business.topupwithdrawal.entity.em.BusinessType;
import com.lolluckyman.business.topupwithdrawal.entity.em.DisposalStatus;
import com.lolluckyman.business.topupwithdrawal.service.ITopUpWithdrawalService;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseController;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TopUpWithdrawalistrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/back/TopUpWithdrawal")
public class TopUpWithdrawalController extends BaseController {

    public Logger log = Logger.getLogger(TopUpWithdrawalController.class);//日志

    @Autowired
    private ITopUpWithdrawalService topUpWithdrawalService;
    @Autowired
    private ICompetitionService competitionService;

    @RequestMapping(value = "/getTopUpWithdrawalPageList")
    public Object getTopUpWithdrawalPageList(int pageIndex,int pageSize,String businessType){
        log.info("获取充值提现信息信息列表");
        QueryParams queryParams = new QueryParams();
        queryParams.addOrderBy("code", true);
        if (!LolUtils.isEmptyOrNull(businessType)){
            if (!BusinessType.isExit(businessType))
                return validationResult(1001,"获取充值提现信息信息列表，不存在此业务类型。");
//            queryParams.addParameter("businessType", BusinessType.充值.name());
//            queryParams.addMulAttrParameter("disposalStatus", DisposalStatus.处理完成.name());
//            queryParams.addMulAttrParameter("disposalStatus", DisposalStatus.正在处理.name());
//            queryParams.addMulAttrParameter("disposalStatus", DisposalStatus.等待处理.name());
        }
        return result(topUpWithdrawalService.getTopUpWithdrawalPageList(queryParams,pageIndex,pageSize,true));
    }

    @RequestMapping(value = "/getTopUpWithdrawalByCode")
    public Object getTopUpWithdrawalByCode(String topUpWithdrawalCode){
        log.info("获取主键为"+topUpWithdrawalCode+"的充值提现信息信息");
        if (LolUtils.isEmptyOrNull(topUpWithdrawalCode))
            return validationResult(1001,"查询充值提现信息信息，topUpWithdrawalCode不能为空");
        TopUpWithdrawal topUpWithdrawal=topUpWithdrawalService.getTopUpWithdrawalByCode(topUpWithdrawalCode);
        if (topUpWithdrawal==null)
            return validationResult(1001,"找不到此"+topUpWithdrawalCode+"的充值提现信息信息");
        return result(topUpWithdrawal);
    }

    @RequestMapping(value = "/locking")
    public Object locking(String topUpwithdrawalCode){
        if(LolUtils.isEmptyOrNull(topUpwithdrawalCode))
            return validationResult(1001,"锁定充值提现code主键不能为空");

        boolean flag=topUpWithdrawalService.locking(topUpwithdrawalCode);
        if (!flag)
            return validationResult(1001,"锁定失败");
        return result("锁定成功");
    }

    @RequestMapping(value = "/examine")
    public Object examine(String topUpwithdrawalCode,Double money){
        if(LolUtils.isEmptyOrNull(topUpwithdrawalCode))
            return validationResult(1001,"审核充值提现code主键不能为空");
        boolean flag=topUpWithdrawalService.examine(topUpwithdrawalCode,money);
        if (!flag)
            return validationResult(1001,"审核失败");
        return result("审核成功");
    }


    @RequestMapping(value = "/revoke")
    public Object revoke(String topUpwithdrawalCode){
        if(LolUtils.isEmptyOrNull(topUpwithdrawalCode))
            return validationResult(1001,"撤销充值提现code主键不能为空");
        boolean flag=topUpWithdrawalService.revoke(topUpwithdrawalCode);
        if (!flag)
            return validationResult(1001,"撤销失败");
        return result("撤销成功");
    }
}
