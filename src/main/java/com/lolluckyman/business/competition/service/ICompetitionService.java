package com.lolluckyman.business.competition.service;

import com.lolluckyman.business.competition.entity.Competition;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface ICompetitionService {

    /**
     *获取赛事信息分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<Competition> getCompetitionPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     * 根据赛事信息code查找赛事信息
     * @param competitionCode 赛事信息code
     * @return 赛事信息对象
     */
    public Competition getCompetitionByCode(String competitionCode);

    /**
     * 添加赛事信息信息
     * @param competition 赛事信息对象
     * @return 赛事信息对象
     */
    public Competition createCompetition(Competition competition);

    /**
     * 修改赛事信息信息
     * @param competition 赛事信息对象
     * @return 赛事信息对象
     */
    public Competition updateCompetition(Competition competition);

    /**
     * 根据code删除指定赛事信息
     * @param code 赛事信息code
     * @return true表示成功 false表示失败
     */
    public Boolean deleteCompetition(String code);

}
