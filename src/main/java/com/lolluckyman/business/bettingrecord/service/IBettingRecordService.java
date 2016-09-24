package com.lolluckyman.business.bettingrecord.service;

import com.lolluckyman.business.bettingrecord.entity.BettingRecord;
import com.lolluckyman.business.bettingrecord.entity.em.BettingRecordStatus;
import com.lolluckyman.business.topupwithdrawal.entity.em.DisposalStatus;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;

import java.util.List;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IBettingRecordService {

    /**
     *获取投注信息分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<BettingRecord> getBettingRecordPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     *获取投注信息列表
     * @param wheres    条件
     * @return 对象列表
     */
    public List<BettingRecord> getBettingRecordList(QueryParams wheres);

    /**
     * 根据投注信息code查找投注信息
     * @param bettingRecordCode 投注信息code
     * @return 投注信息对象
     */
    public BettingRecord getBettingRecordByCode(String bettingRecordCode);

    /**
     * 操作指定投注状态
     * @param code 投注code
     * @param bettingRecordStatus 投注状态
     * @return true表示操作成功 false表示操作失败
     */
    public boolean operationBettingRecordStatus(String code, BettingRecordStatus bettingRecordStatus);

}
