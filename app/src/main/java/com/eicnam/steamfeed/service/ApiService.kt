package com.eicnam.steamfeed.service

import com.eicnam.steamfeed.model.Applist
import com.eicnam.steamfeed.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("GetAppList/v0002/")
    suspend fun getGames(): Response<Applist>

    @GET("GetNewsForApp/v0002/?appid={id}&count=3")
    suspend fun getNewsPerGames(@Path("id") id: String): Response<MutableList<News>>

}