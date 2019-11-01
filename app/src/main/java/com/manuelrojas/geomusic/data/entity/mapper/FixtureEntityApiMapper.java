package com.manuelrojas.geomusic.data.entity.mapper;

import com.manuelrojas.geomusic.data.entity.FixtureEntity;
import com.manuelrojas.geomusic.data.entity.api.FixtureApi;

import javax.inject.Inject;

public class FixtureEntityApiMapper {

    @Inject
    public FixtureEntityApiMapper() {
    }

    public FixtureEntity transformFixtureApi(FixtureApi fixtureApi) {
        FixtureEntity fixtureEntity = null;
        if (fixtureApi != null) {
            fixtureEntity = new FixtureEntity();
            fixtureEntity.setId(fixtureApi.getId());
            fixtureEntity.setType(fixtureApi.getType());

            fixtureEntity.setHomeTeamId(fixtureApi.getHomeTeam().getId());
            fixtureEntity.setHomeTeamName(fixtureApi.getHomeTeam().getName());
            fixtureEntity.setHomeTeamShortName(fixtureApi.getHomeTeam().getShortName());
            fixtureEntity.setHomeTeamAlias(fixtureApi.getHomeTeam().getAlias());
            fixtureEntity.setHomeTeamAbbr(fixtureApi.getHomeTeam().getAbbr());

            fixtureEntity.setAwayTeamId(fixtureApi.getAwayTeam().getId());
            fixtureEntity.setAwayTeamName(fixtureApi.getAwayTeam().getName());
            fixtureEntity.setAwayTeamShortName(fixtureApi.getAwayTeam().getShortName());
            fixtureEntity.setAwayTeamAlias(fixtureApi.getAwayTeam().getAlias());
            fixtureEntity.setAwayTeamAbbr(fixtureApi.getAwayTeam().getAbbr());

            fixtureEntity.setDate(fixtureApi.getDate());

            fixtureEntity.setCompetitionId(fixtureApi.getCompetitionStage().getCompetition().getId());
            fixtureEntity.setCompetitionName(fixtureApi.getCompetitionStage().getCompetition().getName());
            fixtureEntity.setCompetitionStage(fixtureApi.getCompetitionStage().getStage());
            fixtureEntity.setCompetitionLeg(fixtureApi.getCompetitionStage().getLeg());

            fixtureEntity.setVenueId(fixtureApi.getVenue().getId());
            fixtureEntity.setVenueName(fixtureApi.getVenue().getName());

            fixtureEntity.setState(fixtureApi.getState());

            if (fixtureApi.getScore() != null) {
                fixtureEntity.setScoreHome(fixtureApi.getScore().getHome());
                fixtureEntity.setScoreAway(fixtureApi.getScore().getAway());
                fixtureEntity.setScoreWinner(fixtureApi.getScore().getWinner());
            }
            if (fixtureApi.getPenaltyScore()!= null) {
                fixtureEntity.setPenaltyScoreHome(fixtureApi.getPenaltyScore().getHome());
                fixtureEntity.setPenaltyScoreAway(fixtureApi.getPenaltyScore().getAway());
                fixtureEntity.setPenaltyScoreWinner(fixtureApi.getPenaltyScore().getWinner());
            }
            if (fixtureApi.getAggregateScore() != null) {
                fixtureEntity.setAggregateScoreHome(fixtureApi.getAggregateScore().getHome());
                fixtureEntity.setAggregateScoreAway(fixtureApi.getAggregateScore().getAway());
                fixtureEntity.setAggregateScoreWinner(fixtureApi.getAggregateScore().getWinner());
            }
        }
        return fixtureEntity;
    }

}
