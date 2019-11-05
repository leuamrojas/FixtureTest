package com.manuelrojas.fixture.presentation.model;

public class FixtureModel {

    private int id;
    private String type;
    private TeamModel homeTeam;
    private TeamModel awayTeam;
    private String date;
    private CompetitionStageModel competitionStage;
    private VenueModel venue;
    private String state;
    private ScoreModel score;
    private ScoreModel penaltyScore;
    private ScoreModel aggregateScore;
    private boolean header;
    private String monthYear;

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

    public TeamModel getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(TeamModel homeTeam) {
        this.homeTeam = homeTeam;
    }

    public TeamModel getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(TeamModel awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CompetitionStageModel getCompetitionStage() {
        return competitionStage;
    }

    public void setCompetitionStage(CompetitionStageModel competitionStage) {
        this.competitionStage = competitionStage;
    }

    public VenueModel getVenue() {
        return venue;
    }

    public void setVenue(VenueModel venue) {
        this.venue = venue;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ScoreModel getScore() {
        return score;
    }

    public void setScore(ScoreModel score) {
        this.score = score;
    }

    public ScoreModel getPenaltyScore() {
        return penaltyScore;
    }

    public void setPenaltyScore(ScoreModel penaltyScore) {
        this.penaltyScore = penaltyScore;
    }

    public ScoreModel getAggregateScore() {
        return aggregateScore;
    }

    public void setAggregateScore(ScoreModel aggregateScore) {
        this.aggregateScore = aggregateScore;
    }

    public boolean isHeader() {
        return header;
    }

    public void setHeader(boolean header) {
        this.header = header;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }
}
