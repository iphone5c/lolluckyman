package com.lolluckyman.lolbefore;

import com.lolluckyman.business.topupwithdrawal.service.ITopUpWithdrawalService;
import com.lolluckyman.utils.core.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/lol/topupwithdrawal/jsp")
public class LolTopUpWithdrawalControllerJSP extends BaseController {

    public Logger log = Logger.getLogger(LolTopUpWithdrawalControllerJSP.class);//日志

    @Autowired
    private ITopUpWithdrawalService topUpWithdrawalService;

    /**
     * 充值界面
     */
    @RequestMapping(value = "/topUpWithdrawal")
    public ModelAndView topUpWithdrawal(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/lolluckman/topupwithdrawal/topupwithdrawal");
        return modelAndView;
    }
}
