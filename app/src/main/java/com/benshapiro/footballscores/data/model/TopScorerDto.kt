package com.benshapiro.footballscores.data.model

data class CompetitionScorerDto(
    val id: Int,
    val name: String,
    val code: String,
    val type: String,
    val emblem: String,
)

data class ScorerDto(
    val player: PlayerDto,
    val team: TeamDto?,
    val playedMatches: Int,
    val goals: Int,
    val assists: Int? = null,
    val penalties: Int? = null
)

data class TopScorerDto(
    val count: Int,
    val competition: CompetitionScorerDto,
    val seasonDto: SeasonDto,
    val scorers: List<ScorerDto>,
    val team: TeamDto?,
)
