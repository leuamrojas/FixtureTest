package com.manuelrojas.fixture.presentation.model;

public class CompetitionStageModel {

    private int idCompStage;
    private CompetitionModel competition;
    private String stage;
    private String leg;

    public CompetitionModel getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionModel competition) {
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
