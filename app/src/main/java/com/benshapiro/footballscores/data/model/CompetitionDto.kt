package com.benshapiro.footballscores.data.model

import com.google.gson.annotations.SerializedName

data class CompetitionDto(
    val id : Int,
    val area: AreaDto,
    val name: String,
    val code: String,
    val type: String,
    val emblem: String? = null,
    val currentSeason: SeasonDto,
    val seasons: SeasonsListDto,
    val numberOfAvailableSeasons: Int,
    val lastUpdated: String,
)

data class AreaDto (
    val id: Int,
    val name: String,
    val code: String,
    val flag: String? = null,
)

data class SeasonDto (
    val id: Int,
    val startDate: String,
    val endDate: String,
    val currentMatchDay: Int? = null,
    val winner: TeamDto? = null,
    val stages: String? = null
)

data class SeasonsListDto (
    val seasons: List<SeasonDto>
)

data class TeamDto(
    val id: Int,
    val name: String,
    val shortName: String? = null,
    val tla: String? = null,
    val crest: String? = null,
    val address: String? = null,
    val website: String? = null,
    val founded: Int? = null,
    val clubColors: String? = null,
    val venue: String? = null,
    val lastUpdated: String,
    val coach: CoachDto? = null,
    val leagueRank: Int? = null,
    val formation: String? = null,
    val lineup: List<PlayerDto>? = null,
    val bench: List<PlayerDto>? = null,
    val runningCompetitions: List<CompetitionDto>? = null,
    val marketValue: Int? = null,
    val squad: List<PlayerDto>? = null,
    val staff: List<String>? = null,
)

data class CoachDto(
    val id: Int,
    val firstName: String? = null,
    val lastName: String? = null,
    val name: String? = null,
    val nationality: String? = null,
    val dateOfBirth: String? = null,
    val contract: ContractDto? = null,
)

data class ContractDto(
    val startDate: String,
    val endDate: String,
)

data class MatchDto(
    val id: Int,
    val utcDate: String,
    val status: String,
    val minute: Int,
    val injuryTime: Int,
    val attendance: Int? = null,
    val venue: String?,
    val matchDay: Int,
    val stage: String,
    val group: String? = null,
    val lastUpdated: String,
    val homeTeamDto: TeamDto,
    val awayTeamDto: TeamDto,
    val score: MatchScoreDto,
    val goals: MatchGoalsDto? = null,
    val penalties: MatchPenaltiesDto? = null,
    val bookings: List<String>? = null,
    val substitutions: List<SubstitutionDto>? = null,
    val referees: List<RefereeDto>? = null,
    val statistics: StatisticsDto? = null
)

data class SubstitutionDto(
    val minute: Int,
    val teamDto: TeamDto,
    val playerOut: PlayerDto,
    val playerIn: PlayerDto,
)

data class StatisticsDto(
    val corner_kicks: Int? = null,
    val free_kicks: Int? = null,
    val goal_kicks: Int? = null,
    val offsides: Int? = null,
    val fouls: Int? = null,
    val ball_possession: Int? = null,
    val saves: Int? = null,
    val throw_ins: Int? = null,
    val shots: Int? = null,
    val shots_on_goal: Int? = null,
    val shots_off_goal: Int? = null,
    val yellow_cards: Int? = null,
    val yellow_red_cards: Int? = null,
    val red_cards: Int? = null,
)

data class MatchScoreDto(
    val winner: String? = null,
    val duration: String,
    val fullTime: ScoreDto? = null,
    val halfTime: ScoreDto? = null,
)

data class ScoreDto(
    val homeGoals : Int? = 0,
    val awayGoals: Int? = 0,
)

data class MatchGoalsDto(
    val minute: Int,
    val injuryTime: Int? = null,
    val type: String,
    val teamDto: TeamDto,
    val scorer: PlayerDto,
    val assist: PlayerDto? = null,
    val scoreDto: ScoreDto,
)

data class PlayerDto(
    val id: Int,
    val firstName: String?,
    val lastName: String?,
    val name: String,
    val position: String? = null,
    val dateOfBirth: String? = null,
    val nationality: String?,
    val shirtNumber: Int? = null,
    val marketValue: Int?,
    val contract: ContractDto?,
    val currentTeamDto: CurrentTeamDto? = null,
)

data class CurrentTeamDto(
    val area: AreaDto? = null,
    val teamDto: TeamDto? = null,
)

data class MatchPenaltiesDto(
    val scoreDto: ScoreDto,
    val teamDto: TeamDto,
    val scored: Boolean,
)

data class RefereeDto(
    val id: Int,
    val name: String,
    val type: String? = null,
    val nationality: String? = null,
)


