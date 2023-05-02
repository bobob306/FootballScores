package com.benshapiro.footballscores.di

import com.benshapiro.footballscores.BuildConfig
import com.benshapiro.footballscores.data.mapper.ViewDataMapper
import com.benshapiro.footballscores.data.model.TopScorerDto
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private val BASE_URL = "https://api.football-data.org/v4/"
    private val apiKeyValue = BuildConfig.API_KEY_VALUE

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request().newBuilder()
                    .addHeader("X-Auth-Token", apiKeyValue)
                    .build()
                it.proceed(request)
            }
            .addNetworkInterceptor(logger).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): RetrofitApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RetrofitApi::class.java)
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): FootballService {
        return retrofit.create(FootballService::class.java)
    }

    @Provides
    fun provideViewDataMapper(): ViewDataMapper {
        return ViewDataMapper()
    }
}

interface RetrofitApi{
    @GET("competitions/SA/scorers/")
    suspend fun getTopTestScorers(): TopScorerDto

    @GET("competitions/{League-Code}/scorers")
    suspend fun getScorers(
        @Path("League-Code") leagueName: String,
//        @Query("season") season: String,
    ) : Response<TopScorerDto>

}

interface FootballService {
    @GET("competitions/SA/scorers/")
    suspend fun getFootball(): FootballResponse
}

data class FootballResponse(
    val boolean: Any
)