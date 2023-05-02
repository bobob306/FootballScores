package com.benshapiro.footballscores.data.viewData

data class MatchViewData(
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
    val homeTeamViewData: TeamViewData,
    val awayTeamViewData: TeamViewData,
    val score: MatchScoreViewData,
    val goals: MatchGoalsViewData? = null,
    val penalties: MatchPenaltiesViewData? = null,
    val bookings: List<String>? = null,
    val substitutions: List<SubstitutionViewData>? = null,
    val referees: List<RefereeViewData>? = null,
    val statistics: StatisticsViewData? = null
)

data class StatisticsViewData(
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

data class RefereeViewData(
    val id: Int,
    val name: String,
    val type: String? = null,
    val nationality: String? = null,
)

data class SubstitutionViewData(
    val minute: Int,
    val teamViewData: TeamViewData,
    val playerOut: PlayerViewData,
    val playerIn: PlayerViewData,
)

data class MatchPenaltiesViewData (
    val scoreViewData: ScoreViewData,
    val teamViewData: TeamViewData,
    val scored: Boolean,
)

data class MatchGoalsViewData (
    val minute: Int,
    val injuryTime: Int? = null,
    val type: String,
    val teamViewData: TeamViewData,
    val scorer: PlayerViewData,
    val assist: PlayerViewData? = null,
    val scoreViewData: ScoreViewData,
        )

data class TeamViewData (
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
    val coach: CoachViewData? = null,
    val leagueRank: Int? = null,
    val formation: String? = null,
    val lineup: List<PlayerViewData>? = null,
    val bench: List<PlayerViewData>? = null,
    val runningCompetitions: List<CompetitionViewData>? = null,
    val marketValue: Int? = null,
    val squad: List<PlayerViewData>? = null,
    val staff: List<String>? = null,
    )

data class PlayerViewData(
    val id: Int,
    val firstName: String?,
    val lastName: String?,
    val name: String,
    val position: String? = null,
    val dateOfBirth: String? = null,
    val nationality: String?,
    val shirtNumber: Int? = null,
    val marketValue: Int?,
    val contract: ContractViewData?,
    val currentTeamViewData: CurrentTeamViewData? = null,
)

data class CurrentTeamViewData(
    val area: AreaViewData? = null,
    val teamViewData: TeamViewData? = null,
)

data class AreaViewData(
    val id: Int,
    val name: String,
    val code: String,
    val flag: String? = null,
)

data class CompetitionViewData(
    val id : Int,
    val area: AreaViewData,
    val name: String,
    val code: String,
    val type: String,
    val emblem: String? = null,
    val currentSeason: SeasonViewData,
    val seasons: SeasonsListViewData,
    val numberOfAvailableSeasons: Int,
    val lastUpdated: String,
)

data class CoachViewData(
    val id: Int,
    val firstName: String? = null,
    val lastName: String? = null,
    val name: String? = null,
    val nationality: String? = null,
    val dateOfBirth: String? = null,
    val contract: ContractViewData? = null,
)

data class ContractViewData(
    val startDate: String,
    val endDate: String,
)

data class SeasonsListViewData (
    val seasons: List<SeasonViewData>
)

data class SeasonViewData(
    val id: Int,
    val startDate: String,
    val endDate: String,
    val currentMatchDay: Int? = null,
    val winner: TeamViewData? = null,
    val stages: String? = null
)

data class MatchScoreViewData(
    val winner: String? = null,
    val duration: String? = null,
    val fullTimeScore: ScoreViewData? = null,
    val halfTimeScore: ScoreViewData? = null,
)

data class ScoreViewData(
    val homeGoals: Int? = null,
    val awayGoals: Int? = null,
)

data class ScoreCardViewData(
    val homeTeamCrest: String? = null,
    val awayTeamCrest: String? = null,
    val homeTeamShortName: String,
    val awayTeamShortName: String,
    val homeTeamScore: Int? = 0,
    val awayTeamScore: Int? = 0,
    val minute: Int? = 0
)

data class HomeCompetitionViewData(
    val leagueName: String,
    val leagueIcon: Int,
    val leagueCode: String,
)
