package com.manuelrojas.fixture.presentation.mapper;

import com.manuelrojas.fixture.presentation.model.CompetitionModel;
import com.manuelrojas.fixture.presentation.model.CompetitionStageModel;
import com.manuelrojas.fixture.presentation.model.FixtureModel;
import com.manuelrojas.fixture.presentation.model.ScoreModel;
import com.manuelrojas.fixture.presentation.model.TeamModel;
import com.manuelrojas.fixture.presentation.model.VenueModel;
import com.manuelrojas.fixture.domain.Competition;
import com.manuelrojas.fixture.domain.Fixture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class FixtureModelDataMapper {

    @Inject
    FixtureModelDataMapper() {}

    /**
     * Transform a {@link Fixture} into an {@link Fixture}.
     *
     * @param fixture Object to be transformed.
     * @return {@link FixtureModel} if valid {@link Fixture} otherwise null.
     */
    public FixtureModel transform(Fixture fixture) {
        FixtureModel fixtureModel = null;
        if (fixture != null){
            fixtureModel = new FixtureModel();
            fixtureModel.setId(fixture.getId());
            fixtureModel.setType(fixture.getType());
            fixtureModel.setHomeTeam(getHomeTeam(fixture));
            fixtureModel.setAwayTeam(getAwayTeam(fixture));
            fixtureModel.setDate(fixture.getDate());
            fixtureModel.setCompetitionStage(getCompetitionStage(fixture));
            fixtureModel.setVenue(getVenue(fixture));
            fixtureModel.setState(fixture.getState());
            fixtureModel.setScore(getScore(fixture));
            fixtureModel.setPenaltyScore(getPenaltyScore(fixture));
            fixtureModel.setAggregateScore(getAggregateScore(fixture));
        }
        return fixtureModel;
    }

    private TeamModel getHomeTeam(Fixture fixture) {
        TeamModel teamModel = null;
        if (fixture != null) {
            teamModel = new TeamModel();
            teamModel.setId(fixture.getHomeTeam().getId());
            teamModel.setName(fixture.getHomeTeam().getName());
            teamModel.setShortName(fixture.getHomeTeam().getShortName());
            teamModel.setAlias(fixture.getHomeTeam().getAlias());
            teamModel.setAbbr(fixture.getHomeTeam().getAbbr());
        }
        return teamModel;
    }

    private TeamModel getAwayTeam(Fixture fixture) {
        TeamModel teamModel = null;
        if (fixture != null) {
            teamModel = new TeamModel();
            teamModel.setId(fixture.getAwayTeam().getId());
            teamModel.setName(fixture.getAwayTeam().getName());
            teamModel.setShortName(fixture.getAwayTeam().getShortName());
            teamModel.setAlias(fixture.getAwayTeam().getAlias());
            teamModel.setAbbr(fixture.getAwayTeam().getAbbr());
        }
        return teamModel;
    }

    private CompetitionStageModel getCompetitionStage(Fixture fixture) {
        CompetitionStageModel competitionStageModel = null;
        if (fixture != null) {
            competitionStageModel = new CompetitionStageModel();
            competitionStageModel.setStage(fixture.getCompetitionStage().getStage());
            competitionStageModel.setLeg(fixture.getCompetitionStage().getLeg());
            competitionStageModel.setCompetition(getCompetition(fixture.getCompetitionStage().getCompetition()));
        }
        return competitionStageModel;
    }

    private CompetitionModel getCompetition(Competition competition) {
        CompetitionModel competitionModel = null;
        if (competition != null) {
            competitionModel = new CompetitionModel();
            competitionModel.setId(competition.getId());
            competitionModel.setName(competition.getName());
        }
        return competitionModel;
    }

    private VenueModel getVenue(Fixture fixture) {
        VenueModel venueModel = null;
        if (fixture != null) {
            venueModel = new VenueModel();
            venueModel.setId(fixture.getVenue().getId());
            venueModel.setName(fixture.getVenue().getName());
        }
        return venueModel;
    }

    private ScoreModel getScore(Fixture fixture) {
        ScoreModel scoreModel = null;
        if (fixture != null) {
            scoreModel = new ScoreModel();
            scoreModel.setAway(fixture.getScore().getAway());
            scoreModel.setHome(fixture.getScore().getHome());
            scoreModel.setWinner(fixture.getScore().getWinner());
        }
        return scoreModel;
    }

    private ScoreModel getPenaltyScore(Fixture fixture) {
        ScoreModel scoreModel = null;
        if (fixture != null) {
            scoreModel = new ScoreModel();
            scoreModel.setAway(fixture.getPenaltyScore().getAway());
            scoreModel.setHome(fixture.getPenaltyScore().getHome());
            scoreModel.setWinner(fixture.getPenaltyScore().getWinner());
        }
        return scoreModel;
    }

    private ScoreModel getAggregateScore(Fixture fixture) {
        ScoreModel scoreModel = null;
        if (fixture != null) {
            scoreModel = new ScoreModel();
            scoreModel.setAway(fixture.getAggregateScore().getAway());
            scoreModel.setHome(fixture.getAggregateScore().getHome());
            scoreModel.setWinner(fixture.getAggregateScore().getWinner());
        }
        return scoreModel;
    }

    /**
     * Transform a List of {@link Fixture} into a Collection of {@link FixtureModel}.
     *
     * @param fixtureCollection Object Collection to be transformed.
     * @return {@link FixtureModel} if valid {@link Fixture} otherwise null.
     */
    public List<FixtureModel> transform(Collection<Fixture> fixtureCollection) {
        List<FixtureModel> fixtures = new ArrayList<>(20);
        for (Fixture fixture : fixtureCollection) {
            FixtureModel fixtureModel = transform(fixture);
            if (fixtureModel != null) {
                fixtures.add(fixtureModel);
            }
        }
        return fixtures;
    }


}
