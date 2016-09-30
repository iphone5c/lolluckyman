package com.lolluckyman.lolbefore;

import com.lolluckyman.utils.core.BaseController;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/lol/main/jsp")
public class LolMainControllerJSP extends BaseController {

    public Logger log = Logger.getLogger(LolMainControllerJSP.class);//日志

    /**
     * 首页
     */
    @RequestMapping(value = "/mainIndex")
    public ModelAndView mainIndex(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/lolluckman/main/index");
        return modelAndView;
    }
}
