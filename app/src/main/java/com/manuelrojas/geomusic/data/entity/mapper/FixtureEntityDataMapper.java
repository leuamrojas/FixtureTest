package com.manuelrojas.geomusic.data.entity.mapper;

import com.manuelrojas.geomusic.data.entity.FixtureEntity;
import com.manuelrojas.geomusic.domain.Competition;
import com.manuelrojas.geomusic.domain.CompetitionStage;
import com.manuelrojas.geomusic.domain.Fixture;
import com.manuelrojas.geomusic.domain.Score;
import com.manuelrojas.geomusic.domain.Team;
import com.manuelrojas.geomusic.domain.Venue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class FixtureEntityDataMapper {

    @Inject
    FixtureEntityDataMapper() {}

    /**
     * Transform a {@link FixtureEntity} into an {@link Fixture}.
     *
     * @param fixtureEntity Object to be transformed.
     * @return {@link Fixture} if valid {@link FixtureEntity} otherwise null.
     */
    public Fixture transform(FixtureEntity fixtureEntity) {
        Fixture fixture = null;
        if (fixtureEntity != null){
            fixture = new Fixture();
            fixture.setId(fixtureEntity.getId());
            fixture.setType(fixtureEntity.getType());
            fixture.setHomeTeam(getHomeTeam(fixtureEntity));
            fixture.setAwayTeam(getAwayTeam(fixtureEntity));
            fixture.setDate(fixtureEntity.getDate());
            fixture.setCompetitionStage(getCompetitionStage(fixtureEntity));
            fixture.setVenue(getVenue(fixtureEntity));
            fixture.setState(fixtureEntity.getState());
            fixture.setScore(getScore(fixtureEntity));
            fixture.setPenaltyScore(getPenaltyScore(fixtureEntity));
            fixture.setAggregateScore(getAggregateScore(fixtureEntity));
        }
        return fixture;
    }

    private Team getHomeTeam(FixtureEntity fixtureEntity) {
        Team team = null;
        if (fixtureEntity != null) {
            team = new Team();
            team.setId(fixtureEntity.getHomeTeamId());
            team.setName(fixtureEntity.getHomeTeamName());
            team.setShortName(fixtureEntity.getHomeTeamShortName());
            team.setAlias(fixtureEntity.getHomeTeamAlias());
            team.setAbbr(fixtureEntity.getHomeTeamAbbr());
        }
        return team;
    }

    private Team getAwayTeam(FixtureEntity fixtureEntity) {
        Team team = null;
        if (fixtureEntity != null) {
            team = new Team();
            team.setId(fixtureEntity.getAwayTeamId());
            team.setName(fixtureEntity.getAwayTeamName());
            team.setShortName(fixtureEntity.getAwayTeamShortName());
            team.setAlias(fixtureEntity.getAwayTeamAlias());
            team.setAbbr(fixtureEntity.getAwayTeamAbbr());
        }
        return team;
    }

    private CompetitionStage getCompetitionStage(FixtureEntity fixtureEntity) {
        CompetitionStage competitionStage = null;
        if (fixtureEntity != null) {
            competitionStage = new CompetitionStage();
            competitionStage.setStage(fixtureEntity.getCompetitionStage());
            competitionStage.setLeg(fixtureEntity.getCompetitionLeg());
            competitionStage.setCompetition(getCompetition(fixtureEntity));
        }
        return competitionStage;
    }

    private Competition getCompetition(FixtureEntity fixtureEntity) {
        Competition competition = null;
        if (fixtureEntity != null) {
            competition = new Competition();
            competition.setId(fixtureEntity.getCompetitionId());
            competition.setName(fixtureEntity.getCompetitionName());
        }
        return competition;
    }

    private Venue getVenue(FixtureEntity fixtureEntity) {
        Venue venue = null;
        if (fixtureEntity != null) {
            venue = new Venue();
            venue.setId(fixtureEntity.getVenueId());
            venue.setName(fixtureEntity.getVenueName());
        }
        return venue;
    }

    private Score getScore(FixtureEntity fixtureEntity) {
        Score score = null;
        if (fixtureEntity != null) {
            score = new Score();
            score.setAway(fixtureEntity.getScoreAway());
            score.setHome(fixtureEntity.getScoreHome());
            score.setWinner(fixtureEntity.getScoreWinner());
        }
        return score;
    }

    private Score getPenaltyScore(FixtureEntity fixtureEntity) {
        Score score = null;
        if (fixtureEntity != null) {
            score = new Score();
            score.setAway(fixtureEntity.getPenaltyScoreAway());
            score.setHome(fixtureEntity.getPenaltyScoreHome());
            score.setWinner(fixtureEntity.getPenaltyScoreWinner());
        }
        return score;
    }

    private Score getAggregateScore(FixtureEntity fixtureEntity) {
        Score score = null;
        if (fixtureEntity != null) {
            score = new Score();
            score.setAway(fixtureEntity.getAggregateScoreAway());
            score.setHome(fixtureEntity.getAggregateScoreHome());
            score.setWinner(fixtureEntity.getAggregateScoreWinner());
        }
        return score;
    }

    /**
     * Transform a List of {@link FixtureEntity} into a Collection of {@link Fixture}.
     *
     * @param fixtureEntityCollection Object Collection to be transformed.
     * @return {@link Fixture} if valid {@link FixtureEntity} otherwise null.
     */
    public List<Fixture> transform(Collection<FixtureEntity> fixtureEntityCollection) {
        final List<Fixture> fixtures = new ArrayList<>(20);
        for (FixtureEntity fixtureEntity : fixtureEntityCollection) {
            Fixture fixture = transform(fixtureEntity);
            if (fixture != null) {
                fixtures.add(fixture);
            }
        }
        return fixtures;
    }
}
