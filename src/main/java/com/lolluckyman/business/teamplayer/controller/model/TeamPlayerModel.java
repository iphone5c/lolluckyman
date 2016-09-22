package com.lolluckyman.business.teamplayer.controller.model;

import com.lolluckyman.business.team.entity.Team;
import com.lolluckyman.business.teamplayer.entity.TeamPlayer;

/**
 * 战队成员列表视图model
 * Created by 魏源 on 2016/9/21.
 */
public class TeamPlayerModel {
    //所属战队信息
    private Team team;
    //战队成员信息
    private TeamPlayer teamPlayer;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public TeamPlayer getTeamPlayer() {
        return teamPlayer;
    }

    public void setTeamPlayer(TeamPlayer teamPlayer) {
        this.teamPlayer = teamPlayer;
    }
}
