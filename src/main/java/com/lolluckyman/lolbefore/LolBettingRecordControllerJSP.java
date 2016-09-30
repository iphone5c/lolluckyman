package com.lolluckyman.lolbefore;

import com.lolluckyman.business.bettingrecord.entity.BettingRecord;
import com.lolluckyman.business.bettingrecord.service.IBettingRecordService;
import com.lolluckyman.utils.core.BaseController;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/lol/bettingrecord/jsp")
public class LolBettingRecordControllerJSP extends BaseController {

    public Logger log = Logger.getLogger(LolBettingRecordControllerJSP.class);//日志

    @Autowired
    private IBettingRecordService bettingRecordService;

    /**
     * 获取当前用户竞猜记录
     * @param pageIndex
     * @param pageSize
     */
    @RequestMapping(value = "/getBettingRecordPageList")
    public ModelAndView getBettingRecordPageList(int pageIndex, int pageSize){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/lolluckman/bettingrecord/bettingRecordList");

        QueryParams queryParams=new QueryParams();
        queryParams.addOrderBy("bettingRecordTime",true);
        PageList<BettingRecord> bettingRecordPageList=bettingRecordService.getBettingRecordPageList(queryParams,pageIndex,pageSize,true);
        modelAndView.addObject("bettingRecordPageList",bettingRecordPageList);
        return modelAndView;
    }
}
