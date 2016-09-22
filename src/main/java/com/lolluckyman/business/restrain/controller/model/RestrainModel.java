package com.lolluckyman.business.restrain.controller.model;

import com.lolluckyman.business.competition.entity.Competition;
import com.lolluckyman.business.restrain.entity.Restrain;

/**
 * Created by 魏源 on 2016/9/23.
 */
public class RestrainModel {
    //比赛信息
    private Competition competition;
    //局数信息
    private Restrain restrain;

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Restrain getRestrain() {
        return restrain;
    }

    public void setRestrain(Restrain restrain) {
        this.restrain = restrain;
    }
}
