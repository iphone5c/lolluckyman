package com.lolluckyman.business.teamplayer.service;

import com.lolluckyman.business.teamplayer.entity.TeamPlayer;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface ITeamPlayerService {

    /**
     *获取战队成员分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<TeamPlayer> getTeamPlayerPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     * 根据战队成员code查找战队成员
     * @param teamPlayerCode 战队成员code
     * @return 战队成员对象
     */
    public TeamPlayer getTeamPlayerByCode(String teamPlayerCode);

    /**
     * 添加战队成员信息
     * @param teamPlayer 战队成员对象
     * @return 战队成员对象
     */
    public TeamPlayer createTeamPlayer(TeamPlayer teamPlayer);

    /**
     * 修改战队成员信息
     * @param teamPlayer 战队成员对象
     * @return 战队成员对象
     */
    public TeamPlayer updateTeamPlayer(TeamPlayer teamPlayer);

    /**
     * 根据code删除指定战队成员
     * @param code 战队成员code
     * @return true表示成功 false表示失败
     */
    public Boolean deleteTeamPlayer(String code);

    /**
     * 根据战队编号删除战队成员信息
     * @param teamCode 战队编号
     * @return true表示成功 false表示失败
     */
    public Boolean deleteTeamPlayerListByTeamCode(String teamCode);
}
