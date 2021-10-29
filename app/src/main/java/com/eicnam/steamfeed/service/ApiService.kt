package com.eicnam.steamfeed.service

import com.eicnam.steamfeed.model.Applis
import com.eicnam.steamfeed.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("GetAppList/v0002/")
    suspend fun getGames(): Applis

    @GET("GetNewsForApp/v0002/?appid={id}&count=3")
    suspend fun getNewsPerGames(@Path("id") id: String): Response<MutableList<News>>

    /*companion object {

        fun parseJson(): List<Game> {
            var games: MutableList<Game>
            val jsonGames = JSONTokener(getAllGamesString()).nextValue() as JSONObject
            val jsonGamesArray = jsonGames.getJSONObject("appList").getJSONArray("apps")
            for (i in 0 until jsonGamesArray.length()) {
                var jsonGame: JSONObject = jsonGamesArray.getJSONObject(i)
                var game: Game.
                jsonGame.getJSONObject("appid")
                jsonGame.getJSONObject("name")
            }


        }

        fun saveAllGamesJson(context: Context) {

        }

        private fun getAllGamesString(): String {
            val apiResponse =
                URL("http://api.steampowered.com/ISteamApps/GetAppList/v0002/").readText()
            return apiResponse
        }
        private fun getDBConnection(context: Context): GameDatabase {
            return Room.databaseBuilder(
                context,
                GameDatabase::class.java, "game-database"
            ).build()
        }
    }*/
}