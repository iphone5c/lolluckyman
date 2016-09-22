package com.lolluckyman.business.team.service;

import com.lolluckyman.business.team.entity.Team;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;

import java.util.List;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface ITeamService {

    /**
     *获取战队分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<Team> getTeamPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     *获取战队列表
     * @param wheres    条件
     * @return 对象列表
     */
    public List<Team> getTeamList(QueryParams wheres);

    /**
     * 根据战队code查找战队
     * @param teamCode 战队code
     * @return 战队对象
     */
    public Team getTeamByCode(String teamCode);

    /**
     * 添加战队信息
     * @param team 战队对象
     * @return 战队对象
     */
    public Team createTeam(Team team);

    /**
     * 修改战队信息
     * @param team 战队对象
     * @return 战队对象
     */
    public Team updateTeam(Team team);

    /**
     * 根据code删除指定战队
     * @param code 战队code
     */
    public void deleteTeam(String code);
}
