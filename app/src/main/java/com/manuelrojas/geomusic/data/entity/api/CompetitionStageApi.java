package com.manuelrojas.geomusic.data.entity.api;

import com.google.gson.annotations.SerializedName;

public class CompetitionStageApi {

    @SerializedName("competition")
    private CompetitionApi competition;

    @SerializedName("stage")
    private String stage;

    @SerializedName("leg")
    private String leg;

    public CompetitionApi getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionApi competition) {
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
}
