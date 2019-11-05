package com.manuelrojas.fixture.data.entity.api;

import com.google.gson.annotations.SerializedName;

public class ScoreApi {

    @SerializedName("home")
    private Integer home;

    @SerializedName("away")
    private Integer away;

    @SerializedName("winner")
    private String winner;

    public Integer getHome() {
        return home;
    }

    public void setHome(Integer home) {
        this.home = home;
    }

    public Integer getAway() {
        return away;
    }

    public void setAway(Integer away) {
        this.away = away;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

}
