package com.lolluckyman.business.exchangeprize.service;

import com.lolluckyman.business.codebuilder.ICodeBuilder;
import com.lolluckyman.business.competition.service.ICompetitionService;
import com.lolluckyman.business.exchangeprize.dao.IExchangePrizeDao;
import com.lolluckyman.business.exchangeprize.entity.ExchangePrize;
import com.lolluckyman.business.exchangeprize.entity.em.ExchangeStatus;
import com.lolluckyman.business.team.service.ITeamService;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseService;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("exchangePrizeService")
public class ExchangePrizeService extends BaseService implements IExchangePrizeService {

    public Logger log = Logger.getLogger(ExchangePrizeService.class);//日志

    @Autowired
    private IExchangePrizeDao exchangePrizeDao;
    @Autowired
    private ICodeBuilder codeBuilder;
    @Autowired
    private ITeamService teamService;
    @Autowired
    private ICompetitionService competitionService;

    /**
     *获取奖品兑换信息分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<ExchangePrize> getExchangePrizePageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return exchangePrizeDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     *获取奖品兑换信息列表
     * @param wheres    条件
     * @return 对象列表
     */
    @Override
    public List<ExchangePrize> getExchangePrizeList(QueryParams wheres) {
        return exchangePrizeDao.queryList(wheres,0,-1,true);
    }

    /**
     * 根据奖品兑换信息code查找奖品兑换信息
     * @param exchangePrizeCode 奖品兑换信息code
     * @return 奖品兑换信息对象
     */
    @Override
    public ExchangePrize getExchangePrizeByCode(String exchangePrizeCode) {
        if (LolUtils.isEmptyOrNull(exchangePrizeCode))
            throw new IllegalArgumentException("根据code查找奖品兑换信息信息时，Code不能为空");
        return exchangePrizeDao.getObject(exchangePrizeCode,true);
    }

    /**
     * 操作指定奖品兑换状态
     * @param code 奖品兑换code
     * @param exchangeStatus 奖品兑换状态
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean operationExchangePrizeStatus(String code, ExchangeStatus exchangeStatus) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("操作指定奖品兑换的状态时，code不能为空或null");
        if (exchangeStatus==null)
            throw new IllegalArgumentException("操作指定奖品兑换的状态时，修改的奖品兑换状态不能为空");
        ExchangePrize exchangePrize=exchangePrizeDao.getObject(code,true);
        if (exchangePrize==null)
            throw new IllegalArgumentException("操作指定奖品兑换的状态时，找不到此奖品兑换信息，code："+code);
        exchangePrize.setExchangeStatus(exchangeStatus);
        int info=exchangePrizeDao.updateObject(exchangePrize);
        return info>0?true:false;
    }

}
