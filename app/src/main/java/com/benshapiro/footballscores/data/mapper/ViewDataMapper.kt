package com.benshapiro.footballscores.data.mapper

import com.benshapiro.footballscores.data.model.AreaDto
import com.benshapiro.footballscores.data.model.CoachDto
import com.benshapiro.footballscores.data.model.CompetitionDto
import com.benshapiro.footballscores.data.model.CompetitionScorerDto
import com.benshapiro.footballscores.data.model.ContractDto
import com.benshapiro.footballscores.data.model.CurrentTeamDto
import com.benshapiro.footballscores.data.model.MatchDto
import com.benshapiro.footballscores.data.model.MatchGoalsDto
import com.benshapiro.footballscores.data.model.MatchPenaltiesDto
import com.benshapiro.footballscores.data.model.MatchScoreDto
import com.benshapiro.footballscores.data.model.PlayerDto
import com.benshapiro.footballscores.data.model.RefereeDto
import com.benshapiro.footballscores.data.model.ScoreDto
import com.benshapiro.footballscores.data.model.ScorerDto
import com.benshapiro.footballscores.data.model.SeasonDto
import com.benshapiro.footballscores.data.model.SeasonsListDto
import com.benshapiro.footballscores.data.model.StatisticsDto
import com.benshapiro.footballscores.data.model.SubstitutionDto
import com.benshapiro.footballscores.data.model.TeamDto
import com.benshapiro.footballscores.data.model.TopScorerDto
import com.benshapiro.footballscores.data.viewData.AreaViewData
import com.benshapiro.footballscores.data.viewData.CoachViewData
import com.benshapiro.footballscores.data.viewData.CompetitionScorerViewData
import com.benshapiro.footballscores.data.viewData.CompetitionViewData
import com.benshapiro.footballscores.data.viewData.ContractViewData
import com.benshapiro.footballscores.data.viewData.CurrentTeamViewData
import com.benshapiro.footballscores.data.viewData.MatchGoalsViewData
import com.benshapiro.footballscores.data.viewData.MatchPenaltiesViewData
import com.benshapiro.footballscores.data.viewData.MatchScoreViewData
import com.benshapiro.footballscores.data.viewData.MatchViewData
import com.benshapiro.footballscores.data.viewData.PlayerViewData
import com.benshapiro.footballscores.data.viewData.RefereeViewData
import com.benshapiro.footballscores.data.viewData.ScoreCardViewData
import com.benshapiro.footballscores.data.viewData.ScoreViewData
import com.benshapiro.footballscores.data.viewData.ScorerViewData
import com.benshapiro.footballscores.data.viewData.SeasonViewData
import com.benshapiro.footballscores.data.viewData.SeasonsListViewData
import com.benshapiro.footballscores.data.viewData.StatisticsViewData
import com.benshapiro.footballscores.data.viewData.SubstitutionViewData
import com.benshapiro.footballscores.data.viewData.TeamViewData
import com.benshapiro.footballscores.data.viewData.TopScorerViewData

class ViewDataMapper {
    fun mapCompetitionDtoToViewData(competitionDto: CompetitionDto): CompetitionViewData {
        competitionDto.apply {
            return CompetitionViewData(
                id = id,
                area = mapAreaDtoToViewData(area),
                name = name,
                code = code,
                type = type,
                emblem = emblem,
                currentSeason = mapSeasonDtoToViewData(currentSeason),
                seasons = mapSeasonsListDtoToViewData(seasons),
                numberOfAvailableSeasons = numberOfAvailableSeasons,
                lastUpdated = lastUpdated,
            )
        }
    }

    fun mapAreaDtoToViewData(areaDto: AreaDto): AreaViewData {
        areaDto.apply {
            return AreaViewData(
                id = id,
                name = name,
                code = code,
                flag = flag,
            )
        }
    }

    fun mapSeasonDtoToViewData(seasonDto: SeasonDto): SeasonViewData {
        seasonDto.apply {
            return SeasonViewData(
                id = id,
                startDate = startDate,
                endDate = endDate,
                currentMatchDay = currentMatchDay,
                winner = winner?.let { mapTeamDtoToViewData(it) },
                stages = stages
            )
        }
    }

    fun mapTeamDtoToViewData(teamDto: TeamDto): TeamViewData {
        teamDto.apply {
            return TeamViewData(
                id = id,
                name = name,
                shortName = shortName,
                tla = tla,
                crest = crest,
                address = address,
                website = website,
                founded = founded,
                clubColors = clubColors,
                venue = venue,
                lastUpdated = lastUpdated,
                coach = coach?.let { mapCoachDtoToViewData(coach) },
                leagueRank = leagueRank ?: 0,
                formation = formation,
                lineup = lineup?.let { lineupDtoToViewData(lineup) },
                bench = bench?.let { mapBenchDtoToViewData(bench) },
                runningCompetitions = runningCompetitions?.let {
                    mapRunningCompetitionsDtoToViewDataMapper(runningCompetitions)
                },
                marketValue = marketValue,
                squad = squad?.let { mapSquadDtoToViewData(squad) },
                staff = staff
            )
        }
    }

    fun mapMatchDtoToViewData(matchDto: MatchDto): MatchViewData {
        matchDto.apply {
            return MatchViewData(
                id = id,
                utcDate = utcDate,
                status = status,
                minute = minute,
                injuryTime = injuryTime,
                attendance = attendance,
                venue = venue,
                matchDay = matchDay,
                stage = stage,
                group = group,
                lastUpdated = lastUpdated,
                homeTeamViewData = mapTeamDtoToViewData(homeTeamDto),
                awayTeamViewData = mapTeamDtoToViewData(awayTeamDto),
                score = mapMatchScoreDto(score),
                goals = goals?.let { mapGoalsDtoToViewData(goals) },
                penalties = penalties?.let { mapPenaltiesDtoToViewData(penalties) },
                bookings = bookings,
                substitutions = substitutions?.let { mapSubstitutionsDtoToViewData(substitutions) },
                referees = referees?.let { mapRefereeDtoToViewData(referees) },
                statistics = statistics?.let { mapStatisticsDtoToViewData(statistics) },
            )
        }
    }

    private fun mapStatisticsDtoToViewData(statistics: StatisticsDto): StatisticsViewData {
        statistics.apply {
            return StatisticsViewData(
                corner_kicks = corner_kicks,
                free_kicks = free_kicks,
                goal_kicks = goal_kicks,
                offsides = offsides,
                fouls = fouls,
                ball_possession = ball_possession,
                saves = saves,
                throw_ins = throw_ins,
                shots = shots,
                shots_on_goal = shots_on_goal,
                shots_off_goal = shots_off_goal,
                yellow_cards = yellow_cards,
                yellow_red_cards = yellow_red_cards,
                red_cards = red_cards,
            )
        }
    }

    private fun mapRefereeDtoToViewData(refereeDto: List<RefereeDto>): List<RefereeViewData> {
        return refereeDto.map { referee ->
            RefereeViewData(
                id = referee.id,
                name = referee.name,
                type = referee.type,
                nationality = referee.nationality,
            )
        }
    }

    private fun mapSubstitutionsDtoToViewData(
        substitutionsDto: List<SubstitutionDto>
    ): List<SubstitutionViewData> {
        return substitutionsDto.map { substitution ->
            SubstitutionViewData(
                minute = substitution.minute,
                teamViewData = mapTeamDtoToViewData(substitution.teamDto),
                playerIn = mapPlayerDtoToViewData(substitution.playerIn),
                playerOut = mapPlayerDtoToViewData(substitution.playerOut),
            )
        }
    }

    private fun mapPenaltiesDtoToViewData(penalties: MatchPenaltiesDto): MatchPenaltiesViewData {
        penalties.apply {
            return MatchPenaltiesViewData(
                scoreViewData = mapScoreDtoToViewData(scoreDto),
                teamViewData = mapTeamDtoToViewData(teamDto),
                scored = scored,
            )
        }

    }

    private fun mapGoalsDtoToViewData(matchGoalsDto: MatchGoalsDto): MatchGoalsViewData {
        matchGoalsDto.apply {
            return MatchGoalsViewData(
                minute = minute,
                injuryTime = injuryTime,
                type = type,
                teamViewData = mapTeamDtoToViewData(teamDto),
                scorer = mapPlayerDtoToViewData(scorer),
                assist = assist?.let { mapPlayerDtoToViewData(assist) },
                scoreViewData = mapScoreDtoToViewData(scoreDto),
            )
        }
    }

    private fun mapMatchScoreDto(matchScoreDto: MatchScoreDto): MatchScoreViewData {
        matchScoreDto.apply {
            return MatchScoreViewData(
                winner = winner,
                duration = duration,
                fullTimeScore = fullTime?.let { mapScoreDtoToViewData(fullTime) },
                halfTimeScore = halfTime?.let { mapScoreDtoToViewData(halfTime) },
            )
        }
    }

    fun mapScoreDtoToViewData(scoreDto: ScoreDto): ScoreViewData {
        scoreDto.apply {
            return ScoreViewData(
                homeGoals = homeGoals, awayGoals = awayGoals
            )
        }
    }

    fun mapSquadDtoToViewData(squad: List<PlayerDto>): List<PlayerViewData> {
        return squad.map { playerDto ->
            mapPlayerDtoToViewData(playerDto = playerDto)
        }
    }

    fun mapRunningCompetitionsDtoToViewDataMapper(runningCompetitionDto: List<CompetitionDto>)
            : List<CompetitionViewData> {
        return runningCompetitionDto.map { runningCompetition ->
            mapCompetitionDtoToViewData(runningCompetition)
        }
    }

    fun lineupDtoToViewData(lineup: List<PlayerDto>): List<PlayerViewData> {
        return lineup.map { playerDto ->
            mapPlayerDtoToViewData(playerDto = playerDto)
        }
    }

    fun mapBenchDtoToViewData(bench: List<PlayerDto>): List<PlayerViewData> {
        return bench.map { playerDto ->
            mapPlayerDtoToViewData(playerDto = playerDto)
        }
    }

    fun mapPlayerDtoToViewData(playerDto: PlayerDto): PlayerViewData {
        playerDto.apply {
            return PlayerViewData(
                id = id,
                firstName = firstName,
                lastName = lastName,
                name = name,
                position = position,
                dateOfBirth = dateOfBirth,
                nationality = nationality,
                shirtNumber = shirtNumber,
                marketValue = marketValue,
                contract = contract?.let { mapContractDtoToViewData(contract) },
                currentTeamViewData = currentTeamDto?.let {
                    mapCurrentTeamDtoToViewData(
                        currentTeamDto
                    )
                },
            )
        }
    }

    private fun mapCurrentTeamDtoToViewData(currentTeamDto: CurrentTeamDto): CurrentTeamViewData {
        currentTeamDto.apply {
            return CurrentTeamViewData(
                area = area?.let { mapAreaDtoToViewData(area) },
                teamViewData = teamDto?.let { mapTeamDtoToViewData(teamDto) }
            )
        }
    }

    fun mapCoachDtoToViewData(coachDto: CoachDto): CoachViewData {
        coachDto.apply {
            return CoachViewData(
                id = id,
                firstName = firstName,
                lastName = lastName,
                name = name,
                nationality = nationality,
                dateOfBirth = dateOfBirth,
                contract = contract?.let { mapContractDtoToViewData(contract) }
            )
        }
    }

    private fun mapContractDtoToViewData(contractDto: ContractDto): ContractViewData {
        contractDto.apply {
            return ContractViewData(
                startDate = startDate,
                endDate = endDate,
            )
        }
    }

    fun mapSeasonsListDtoToViewData(seasonsListDto: SeasonsListDto): SeasonsListViewData {
        return SeasonsListViewData(seasonsListDto.seasons.map { seasonDto ->
            mapSeasonDtoToViewData(seasonDto = seasonDto)
        })
    }

    fun mapScoreCardViewData(matchDto: MatchDto): ScoreCardViewData {
        matchDto.apply {
            return ScoreCardViewData(
                homeTeamCrest = homeTeamDto.crest,
                awayTeamCrest = awayTeamDto.crest,
                homeTeamShortName = homeTeamDto.shortName ?: homeTeamDto.name,
                awayTeamShortName = awayTeamDto.shortName ?: awayTeamDto.name,
                homeTeamScore = goals?.scoreDto?.homeGoals ?: 0,
                awayTeamScore = goals?.scoreDto?.awayGoals ?: 0,
                minute = minute,
            )
        }
    }

    fun mapTopScorerDtoToViewData(
        topScorerDto: TopScorerDto
    ): TopScorerViewData {
        topScorerDto.apply {
            return TopScorerViewData(
                count = count,
                competition = mapCompetionScorerDtoToViewData(competition),
                seasonDto = seasonDto,
                scorers = mapScorersDtoToViewData(scorers),
                team = team?.let { mapTeamDtoToViewData(team) }
            )
        }
    }

    private fun mapScorersDtoToViewData(
        scorers: List<ScorerDto>
    ): List<ScorerViewData> {
        return scorers.map { scorer ->
            ScorerViewData(
                player = mapPlayerDtoToViewData(scorer.player),
                team = scorer.team?.let { mapTeamDtoToViewData(scorer.team) },
                playedMatches = scorer.playedMatches,
                goals = scorer.goals,
                assists = scorer.assists,
                penalties = scorer.penalties,
            )
        }
    }

    private fun mapCompetionScorerDtoToViewData(
        competition: CompetitionScorerDto
    ): CompetitionScorerViewData {
        competition.apply {
            return CompetitionScorerViewData(
                id = id,
                name = name,
                code = code,
                type = type,
                emblem = emblem
            )
        }
    }
}

