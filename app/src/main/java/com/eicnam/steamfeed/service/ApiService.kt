package com.eicnam.steamfeed.service

import java.net.URL

class ApiService {

    fun getGamesJson():String{
        val apiResponse = URL("http://api.steampowered.com/ISteamApps/GetAppList/v0002/").readText()
        return apiResponse
    }

}