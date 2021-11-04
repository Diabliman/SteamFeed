package com.eicnam.steamfeed.service

import com.eicnam.steamfeed.model.Applist
import com.eicnam.steamfeed.model.NewsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("ISteamApps/GetAppList/v0002/")
    suspend fun getGames(): Response<Applist>

    @GET("ISteamNews/GetNewsForApp/v0002")
    suspend fun getNewsPerGames(
        @Query("appid") appid: String,
        @Query("count") count: Int = 3
    ): Response<NewsList>

}