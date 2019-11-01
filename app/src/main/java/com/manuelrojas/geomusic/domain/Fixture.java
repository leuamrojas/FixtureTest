package com.manuelrojas.geomusic.domain;

public class Fixture {

    private int id;
    private String type;
    private Team homeTeam;
    private Team awayTeam;
    private String date;
    private CompetitionStage competitionStage;
    private Venue venue;
    private String state;
    private Score score;
    private Score penaltyScore;
    private Score aggregateScore;

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

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CompetitionStage getCompetitionStage() {
        return competitionStage;
    }

    public void setCompetitionStage(CompetitionStage competitionStage) {
        this.competitionStage = competitionStage;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Score getPenaltyScore() {
        return penaltyScore;
    }

    public void setPenaltyScore(Score penaltyScore) {
        this.penaltyScore = penaltyScore;
    }

    public Score getAggregateScore() {
        return aggregateScore;
    }

    public void setAggregateScore(Score aggregateScore) {
        this.aggregateScore = aggregateScore;
    }
}
