package com.lolluckyman.lolbefore;

import com.lolluckyman.business.bettingrecord.entity.BettingRecord;
import com.lolluckyman.business.bettingrecord.service.IBettingRecordService;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/lol/bettingrecord/json")
public class LolBettingRecordControllerJSON extends BaseController {

    public Logger log = Logger.getLogger(LolBettingRecordControllerJSON.class);//日志

    @Autowired
    private IBettingRecordService bettingRecordService;

    /**
     * 当前用户投注
     * @param request
     * @param response
     * @param playRecordCode 投注的玩法
     * @param quizMoney 投注的竞猜币数量
     * @param betting 投注结果
     */
    @RequestMapping(value = "/applyTopUp")
    public Object applyTopUp(HttpServletRequest request,HttpServletResponse response,String playRecordCode, Double quizMoney, String betting){
        if (quizMoney==null||quizMoney<10)
            return validationResult(1001,"投注最小竞猜币为10个");
        if (LolUtils.isEmptyOrNull(playRecordCode))
            return validationResult(1001,"投注的玩法不能为空");
        if (LolUtils.isEmptyOrNull(betting))
            return validationResult(1001,"投注的结果不能为空");
        BettingRecord bettingRecord=bettingRecordService.accountBetting(LolUtils.getCurrentAccount(request).getCode(), playRecordCode, quizMoney, betting);
        if (bettingRecord!=null){
            return result("投注成功");
        }else{
            return validationResult(1001,"投注失败");
        }
    }
}
