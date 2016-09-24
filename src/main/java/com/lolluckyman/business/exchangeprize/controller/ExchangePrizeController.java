package com.lolluckyman.business.exchangeprize.controller;

import com.lolluckyman.business.competition.service.ICompetitionService;
import com.lolluckyman.business.exchangeprize.entity.ExchangePrize;
import com.lolluckyman.business.exchangeprize.entity.em.ExchangeStatus;
import com.lolluckyman.business.exchangeprize.service.IExchangePrizeService;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseController;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ExchangePrizeistrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/back/exchangeprize")
public class ExchangePrizeController extends BaseController {

    public Logger log = Logger.getLogger(ExchangePrizeController.class);//日志

    @Autowired
    private IExchangePrizeService exchangePrizeService;
    @Autowired
    private ICompetitionService competitionService;

    @RequestMapping(value = "/getExchangePrizePageList")
    public Object getExchangePrizePageList(int pageIndex,int pageSize){
        log.info("获取奖品兑换信息信息列表");
        QueryParams queryParams = new QueryParams();
        queryParams.addOrderBy("code", true);
        return result(exchangePrizeService.getExchangePrizePageList(queryParams,pageIndex,pageSize,true));
    }

    @RequestMapping(value = "/getExchangePrizeByCode")
    public Object getExchangePrizeByCode(String exchangePrizeCode){
        log.info("获取主键为"+exchangePrizeCode+"的奖品兑换信息信息");
        if (LolUtils.isEmptyOrNull(exchangePrizeCode))
            return validationResult(1001,"查询奖品兑换信息信息，exchangePrizeCode不能为空");
        ExchangePrize exchangePrize=exchangePrizeService.getExchangePrizeByCode(exchangePrizeCode);
        if (exchangePrize==null)
            return validationResult(1001,"找不到此"+exchangePrizeCode+"的奖品兑换信息信息");
        return result(exchangePrize);
    }

    @RequestMapping(value = "/getExchangePrizePageListForGL")
    public Object getExchangePrizePageListForGL(int pageIndex,int pageSize){
        log.info("获取奖品兑换信息信息列表");
        QueryParams queryParams = new QueryParams();
        queryParams.addOrderBy("code", true);
        queryParams.addMulAttrParameter("exchangeStatus", ExchangeStatus.等待处理.name());
        queryParams.addMulAttrParameter("exchangeStatus", ExchangeStatus.正在处理.name());
        return result(exchangePrizeService.getExchangePrizePageList(queryParams,pageIndex,pageSize,true));
    }

}
