package com.lolluckyman.lolbefore;

import com.lolluckyman.business.topupwithdrawal.entity.TopUpWithdrawal;
import com.lolluckyman.business.topupwithdrawal.entity.em.TradeType;
import com.lolluckyman.business.topupwithdrawal.service.ITopUpWithdrawalService;
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
@RequestMapping(value = "/lol/topupwithdrawal")
public class LolTopUpWithdrawalController extends BaseController {

    public Logger log = Logger.getLogger(LolTopUpWithdrawalController.class);//日志

    @Autowired
    private ITopUpWithdrawalService topUpWithdrawalService;

    /**
     * 当前用户充值
     * @param request
     * @param response
     * @param applyMoney
     * @param tradeType
     * @param tradeNumber
     */
    @RequestMapping(value = "/applyTopUp")
    public void applyTopUp(HttpServletRequest request,HttpServletResponse response,Double applyMoney ,String tradeType,String tradeNumber){
        if (applyMoney==null||applyMoney<=0)
            validationResultJSONP(request,response,1001,"充值金额必须大于零");
        if (LolUtils.isEmptyOrNull(tradeType))
            validationResultJSONP(request,response,1001,"交易类不能为空，请选择（支付宝、微信）");
        if (LolUtils.isEmptyOrNull(tradeNumber))
            validationResultJSONP(request,response,1001,"交易账号不能为空");
        TradeType result=null;
        for (TradeType temp:TradeType.values()){
            if (temp.name().equals(tradeType)){
                result=temp;
            }
        }
        if (result==null)
            throw new IllegalArgumentException("交易类型超出规定范围值");
        TopUpWithdrawal topUpWithdrawal=topUpWithdrawalService.applyTopUp(applyMoney, LolUtils.getCurrentAccount(request).getCode(),result , tradeNumber);
        if (topUpWithdrawal!=null){
            resultJSONP(request,response,"充值申请已完成，等待处理。");
        }else{
            validationResultJSONP(request,response,1001,"充值申请失败");
        }
    }
}
