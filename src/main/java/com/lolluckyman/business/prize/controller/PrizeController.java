package com.lolluckyman.business.prize.controller;

import com.lolluckyman.business.prize.entity.Prize;
import com.lolluckyman.business.prize.entity.em.PrizeType;
import com.lolluckyman.business.prize.service.IPrizeService;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseController;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Prizeistrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/back/prize")
public class PrizeController extends BaseController {

    public Logger log = Logger.getLogger(PrizeController.class);//日志

    @Autowired
    private IPrizeService prizeService;

    @RequestMapping(value = "/getPrizePageList")
    public Object getPrizePageList(int pageIndex,int pageSize){
        log.info("获取奖品信息列表");
        QueryParams queryParams = new QueryParams();
        queryParams.addOrderBy("code",true);
        return result(prizeService.getPrizePageList(queryParams,pageIndex,pageSize,true));
    }

    @RequestMapping(value = "/getPrizeByCode")
    public Object getPrizeByCode(String prizeCode){
        log.info("获取主键为"+prizeCode+"的奖品信息");
        if (LolUtils.isEmptyOrNull(prizeCode))
            return validationResult(1001,"查询奖品信息，prizeCode不能为空");
        Prize prize=prizeService.getPrizeByCode(prizeCode);
        if (prize==null)
            return validationResult(1001,"找不到此"+prizeCode+"的奖品信息");
        return result(prize);
    }

    /**
     * 删除指定奖品
     * @param prizeCode
     * @return
     */
    @RequestMapping(value = "/deletePrize")
    public Object deletePrize(String prizeCode){
        if (LolUtils.isEmptyOrNull(prizeCode))
            return validationResult(1001,"删除指定奖品时，code不能为空或null");
        boolean flag=prizeService.deletePrize(prizeCode);
        if (flag){
            return result("删除成功");
        }else{
            return validationResult(1001,"删除失败");
        }
    }

    /**
     * 新增奖品信息
     * @param prize
     * @return
     */
    @RequestMapping(value = "/createPrize")
    public Object createPrize(Prize prize){
        if (prize==null)
            return validationResult(1001,"新建奖品信息时，奖品对象不能为空或null");
        if (LolUtils.isEmptyOrNull(prize.getPrizeName()))
            return validationResult(1001,"新建奖品信息时，奖品名不能为空或null");
        if (prize.getPrizeType()==null)
            return validationResult(1001,"新建奖品信息时，奖品类型不能为空或null");
        Prize temp=prizeService.createPrize(prize);
        if (temp==null){
            return validationResult(1001,"新建失败");
        }else {
            return result(temp);
        }
    }

    /**
     * 修改奖品信息
     * @param prize
     * @return
     */
    @RequestMapping(value = "/updatePrize")
    public Object updatePrize(Prize prize){
        if (prize==null)
            return validationResult(1001,"修改奖品信息时，奖品对象不能为空或null");
        if (LolUtils.isEmptyOrNull(prize.getCode()))
            return validationResult(1001,"修改奖品信息时，奖品主键不能为空或null");
        Prize updatePrize = prizeService.getPrizeByCode(prize.getCode());
        if (updatePrize==null)
            return validationResult(1001,"修改奖品信息时，找不到此奖品的信息，code："+prize.getCode());
        if (LolUtils.isEmptyOrNull(prize.getPrizeName()))
            return validationResult(1001,"修改奖品信息时，奖品名不能为空或null");
        if (prize.getPrizeType()==null)
            return validationResult(1001,"修改奖品信息时，奖品类型不能为空或null");
        updatePrize.setPrizeName(prize.getPrizeName());
        updatePrize.setPrizeType(prize.getPrizeType());
        updatePrize.setDescription(prize.getDescription());
        Prize temp=prizeService.updatePrize(updatePrize);
        if (temp==null){
            return validationResult(1001,"修改失败");
        }else {
            return result(temp);
        }
    }

    /**
     * 获取所有奖品类型
     * @return
     */
    @RequestMapping(value = "/getPrizeType")
    public Object getPrizeType(){
        return result(PrizeType.getAllConvertName());
    }

}
