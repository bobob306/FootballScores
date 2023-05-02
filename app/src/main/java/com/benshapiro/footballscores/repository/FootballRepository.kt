package com.benshapiro.footballscores.repository

import com.benshapiro.footballscores.data.model.TopScorerDto
import com.benshapiro.footballscores.di.RetrofitApi
import retrofit2.Response
import javax.inject.Inject

class FootballRepository @Inject constructor(
    private val retrofitApi: RetrofitApi
) {
    suspend fun getTest(): TopScorerDto {
        return retrofitApi.getTopTestScorers()
    }

    suspend fun getTopScorers(leagueName: String): Response<TopScorerDto> {
        return retrofitApi.getScorers(leagueName)
    }
}