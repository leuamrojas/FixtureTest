package com.manuelrojas.fixture.data.entity.api;

import com.google.gson.annotations.SerializedName;

public class CompetitionApi {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
