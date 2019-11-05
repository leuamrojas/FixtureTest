package com.manuelrojas.fixture.domain;

public class CompetitionStage {

    private int idCompStage;
    private Competition competition;
    private String stage;
    private String leg;

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getLeg() {
        return leg;
    }

    public void setLeg(String leg) {
        this.leg = leg;
    }

    public int getIdCompStage() {
        return idCompStage;
    }

    public void setIdCompStage(int idCompStage) {
        this.idCompStage = idCompStage;
    }
}
