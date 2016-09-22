package com.lolluckyman.business.competition.controller.model;

import com.lolluckyman.business.competition.entity.Competition;
import com.lolluckyman.business.team.entity.Team;

/**
 * Created by lenovo on 2016/9/22.
 */
public class CompetitionModel {
    //战队A
    private Team teamA;
    //战队B
    private Team teamB;
    //赛事信息
    private Competition competition;

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Team getTeamA() {
        return teamA;
    }

    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    }
}
