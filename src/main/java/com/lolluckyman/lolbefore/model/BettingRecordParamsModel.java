package com.lolluckyman.lolbefore.model;

import com.lolluckyman.business.bettingrecord.entity.BettingRecord;
import com.lolluckyman.business.competition.entity.Competition;
import com.lolluckyman.business.playrecord.entity.PlayRecord;
import com.lolluckyman.business.team.entity.Team;

/**
 * Created by 魏源 on 2016/10/7.
 */
public class BettingRecordParamsModel {
    //比赛信息
    private Competition competition;
    //队伍A
    private Team teamA;
    //队伍B
    private Team teamB;
    //玩法
    private PlayRecord playRecord;
    //选择的结果转换
    private String playRecordResult;
    //投注信息
    private BettingRecord bettingRecord;

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

    public PlayRecord getPlayRecord() {
        return playRecord;
    }

    public void setPlayRecord(PlayRecord playRecord) {
        this.playRecord = playRecord;
    }

    public String getPlayRecordResult() {
        return playRecordResult;
    }

    public void setPlayRecordResult(String playRecordResult) {
        this.playRecordResult = playRecordResult;
    }

    public BettingRecord getBettingRecord() {
        return bettingRecord;
    }

    public void setBettingRecord(BettingRecord bettingRecord) {
        this.bettingRecord = bettingRecord;
    }
}
