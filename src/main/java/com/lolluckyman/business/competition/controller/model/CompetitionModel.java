package com.lolluckyman.business.competition.controller.model;

import com.lolluckyman.business.competition.entity.Competition;
import com.lolluckyman.business.team.entity.Team;

/**
 * Created by lenovo on 2016/9/22.
 */
public class CompetitionModel {
    //战队信息
    private Team team;
    //赛事信息
    private Competition competition;

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
