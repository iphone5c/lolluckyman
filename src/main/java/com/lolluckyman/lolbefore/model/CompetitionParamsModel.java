package com.lolluckyman.lolbefore.model;

import com.lolluckyman.business.competition.entity.Competition;
import com.lolluckyman.business.restrain.entity.Restrain;
import com.lolluckyman.business.team.entity.Team;

/**
 * Created by lenovo on 2016/9/29.
 */
public class CompetitionParamsModel {
    private Restrain restrain;
    private Team teamA;
    private Team teamB;

    public Restrain getRestrain() {
        return restrain;
    }

    public void setRestrain(Restrain restrain) {
        this.restrain = restrain;
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
