package com.manuelrojas.geomusic.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "fixture")
public class FixtureEntity {

    @PrimaryKey
    private int id;
    private String type;
    private int homeTeamId;
    private String homeTeamName;
    private String homeTeamShortName;
    private String homeTeamAbbr;
    private String homeTeamAlias;
    private int awayTeamId;
    private String awayTeamName;
    private String awayTeamShortName;
    private String awayTeamAbbr;
    private String awayTeamAlias;
    private String date;
    private int competitionId;
    private String competitionName;
    private String competitionStage;
    private String competitionLeg;
    private int venueId;
    private String venueName;
    private String state;
    private int scoreHome;
    private int scoreAway;
    private String scoreWinner;
    private int penaltyScoreHome;
    private int penaltyScoreAway;
    private String penaltyScoreWinner;
    private int aggregateScoreHome;
    private int aggregateScoreAway;
    private String aggregateScoreWinner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(int homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getHomeTeamShortName() {
        return homeTeamShortName;
    }

    public void setHomeTeamShortName(String homeTeamShortName) {
        this.homeTeamShortName = homeTeamShortName;
    }

    public String getHomeTeamAbbr() {
        return homeTeamAbbr;
    }

    public void setHomeTeamAbbr(String homeTeamAbbr) {
        this.homeTeamAbbr = homeTeamAbbr;
    }

    public String getHomeTeamAlias() {
        return homeTeamAlias;
    }

    public void setHomeTeamAlias(String homeTeamAlias) {
        this.homeTeamAlias = homeTeamAlias;
    }

    public int getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(int awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public String getAwayTeamShortName() {
        return awayTeamShortName;
    }

    public void setAwayTeamShortName(String awayTeamShortName) {
        this.awayTeamShortName = awayTeamShortName;
    }

    public String getAwayTeamAbbr() {
        return awayTeamAbbr;
    }

    public void setAwayTeamAbbr(String awayTeamAbbr) {
        this.awayTeamAbbr = awayTeamAbbr;
    }

    public String getAwayTeamAlias() {
        return awayTeamAlias;
    }

    public void setAwayTeamAlias(String awayTeamAlias) {
        this.awayTeamAlias = awayTeamAlias;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getCompetitionStage() {
        return competitionStage;
    }

    public void setCompetitionStage(String competitionStage) {
        this.competitionStage = competitionStage;
    }

    public String getCompetitionLeg() {
        return competitionLeg;
    }

    public void setCompetitionLeg(String competitionLeg) {
        this.competitionLeg = competitionLeg;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getScoreHome() {
        return scoreHome;
    }

    public void setScoreHome(int scoreHome) {
        this.scoreHome = scoreHome;
    }

    public int getScoreAway() {
        return scoreAway;
    }

    public void setScoreAway(int scoreAway) {
        this.scoreAway = scoreAway;
    }

    public String getScoreWinner() {
        return scoreWinner;
    }

    public void setScoreWinner(String scoreWinner) {
        this.scoreWinner = scoreWinner;
    }

    public int getPenaltyScoreHome() {
        return penaltyScoreHome;
    }

    public void setPenaltyScoreHome(int penaltyScoreHome) {
        this.penaltyScoreHome = penaltyScoreHome;
    }

    public int getPenaltyScoreAway() {
        return penaltyScoreAway;
    }

    public void setPenaltyScoreAway(int penaltyScoreAway) {
        this.penaltyScoreAway = penaltyScoreAway;
    }

    public String getPenaltyScoreWinner() {
        return penaltyScoreWinner;
    }

    public void setPenaltyScoreWinner(String penaltyScoreWinner) {
        this.penaltyScoreWinner = penaltyScoreWinner;
    }

    public int getAggregateScoreHome() {
        return aggregateScoreHome;
    }

    public void setAggregateScoreHome(int aggregateScoreHome) {
        this.aggregateScoreHome = aggregateScoreHome;
    }

    public int getAggregateScoreAway() {
        return aggregateScoreAway;
    }

    public void setAggregateScoreAway(int aggregateScoreAway) {
        this.aggregateScoreAway = aggregateScoreAway;
    }

    public String getAggregateScoreWinner() {
        return aggregateScoreWinner;
    }

    public void setAggregateScoreWinner(String aggregateScoreWinner) {
        this.aggregateScoreWinner = aggregateScoreWinner;
    }

}
