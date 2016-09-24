package com.lolluckyman.business.competition.service;

import com.lolluckyman.business.competition.entity.Competition;
import com.lolluckyman.business.competition.entity.em.CompetitionStatus;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;

import java.util.List;

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
     *获取赛事信息列表
     * @param wheres    条件
     * @return 对象列表
     */
    public List<Competition> getCompetitionList(QueryParams wheres);

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

    /**
     * 操作指定赛事状态
     * @param code 赛事code
     * @param competitionStatus 赛事状态
     * @return true表示操作成功 false表示操作失败
     */
    public boolean operationCompetitionStatus(String code,CompetitionStatus competitionStatus);

    /**
     * 将指定赛事开启
     * @param code 赛事code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean openBetting(String code);

    /**
     * 将指定赛事禁止投注
     * @param code 赛事code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean prohibitBetting(String code);

    /**
     * 根据战队code判断在比赛战队AB中是否存在
     * @param teamCode 战队code
     * @return true表示操作存在 false表示操作不存在
     */
    public boolean isExistCompetitionByTeam(String teamCode);

}
