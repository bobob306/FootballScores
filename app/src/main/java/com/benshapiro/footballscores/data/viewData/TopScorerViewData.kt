package com.benshapiro.footballscores.data.viewData

import com.benshapiro.footballscores.data.model.SeasonDto

data class CompetitionScorerViewData(
    val id: Int,
    val name: String,
    val code: String,
    val type: String,
    val emblem: String,
)

data class ScorerViewData(
    val player: PlayerViewData,
    val team: TeamViewData?,
    val playedMatches: Int,
    val goals: Int,
    val assists: Int? = null,
    val penalties: Int? = null
)

data class TopScorerViewData(
    val count: Int,
    val competition: CompetitionScorerViewData,
    val seasonDto: SeasonDto? = null,
    val scorers: List<ScorerViewData>,
    val team: TeamViewData?,
)
