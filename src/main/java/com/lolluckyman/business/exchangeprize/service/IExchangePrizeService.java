package com.lolluckyman.business.exchangeprize.service;

import com.lolluckyman.business.exchangeprize.entity.ExchangePrize;
import com.lolluckyman.business.exchangeprize.entity.em.ExchangeStatus;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;

import java.util.List;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IExchangePrizeService {

    /**
     *获取奖品兑换信息分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<ExchangePrize> getExchangePrizePageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     *获取奖品兑换信息列表
     * @param wheres    条件
     * @return 对象列表
     */
    public List<ExchangePrize> getExchangePrizeList(QueryParams wheres);

    /**
     * 根据奖品兑换信息code查找奖品兑换信息
     * @param exchangePrizeCode 奖品兑换信息code
     * @return 奖品兑换信息对象
     */
    public ExchangePrize getExchangePrizeByCode(String exchangePrizeCode);

    /**
     * 操作指定奖品兑换状态
     * @param code 奖品兑换code
     * @param exchangeStatus 奖品兑换状态
     * @return true表示操作成功 false表示操作失败
     */
    public boolean operationExchangePrizeStatus(String code, ExchangeStatus exchangeStatus);

}
