package com.manuelrojas.fixture.data.entity.api;

import com.google.gson.annotations.SerializedName;

public class FixtureApi {

    @SerializedName("id")
    private Integer id;

    @SerializedName("type")
    private String type;

    @SerializedName("homeTeam")
    private TeamApi homeTeam;

    @SerializedName("awayTeam")
    private TeamApi awayTeam;

    @SerializedName("date")
    private String date;

    @SerializedName("competitionStage")
    private CompetitionStageApi competitionStage;

    @SerializedName("venue")
    private VenueApi venue;

    @SerializedName("state")
    private String state;

    @SerializedName("score")
    private ScoreApi score;

    @SerializedName("penaltyScore")
    private ScoreApi penaltyScore;

    @SerializedName("aggregateScore")
    private ScoreApi aggregateScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TeamApi getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(TeamApi homeTeam) {
        this.homeTeam = homeTeam;
    }

    public TeamApi getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(TeamApi awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CompetitionStageApi getCompetitionStage() {
        return competitionStage;
    }

    public void setCompetitionStage(CompetitionStageApi competitionStage) {
        this.competitionStage = competitionStage;
    }

    public VenueApi getVenue() {
        return venue;
    }

    public void setVenue(VenueApi venue) {
        this.venue = venue;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ScoreApi getScore() {
        return score;
    }

    public void setScore(ScoreApi score) {
        this.score = score;
    }

    public ScoreApi getPenaltyScore() {
        return penaltyScore;
    }

    public void setPenaltyScore(ScoreApi penaltyScore) {
        this.penaltyScore = penaltyScore;
    }

    public ScoreApi getAggregateScore() {
        return aggregateScore;
    }

    public void setAggregateScore(ScoreApi aggregateScore) {
        this.aggregateScore = aggregateScore;
    }
}
