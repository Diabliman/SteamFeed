package com.eicnam.steamfeed.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eicnam.steamfeed.model.Game
import com.eicnam.steamfeed.model.GameDatabase
import com.eicnam.steamfeed.model.News
import com.eicnam.steamfeed.objects.ApiClient
import com.eicnam.steamfeed.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class GameViewModel(context: Context) : ViewModel() {

    private val repository: GameRepository =
        GameRepository(GameDatabase.getDBConnection(context).gameDao())

    val games = MutableLiveData<List<Game>>()

    suspend fun insertAll(games: List<Game>) {
        repository.insertAll(games)
    }

    fun getAll(): Flow<List<Game>> {
        return repository.getAll()
    }

    fun subscribe(id: String) {
        repository.subscribe(id)
    }

    fun unSubscribe(id: String) {
        repository.unSubscribe(id)
    }

    fun getSubbedGames(): List<Game> {
        return repository.getSubbedGames()
    }

    fun findGamesByNameStart(gameName: String): LiveData<List<Game>> {
        return repository.findGamesByNameStart(gameName)
    }

    fun getNews(): List<News> {
        return runBlocking {
            CoroutineScope(Dispatchers.IO).async {
                val subbedGames = getSubbedGames()
                return@async subbedGames
                    .map { ApiClient.apiService.getNewsPerGames(it.appid) }
                    .filter { it.isSuccessful }
                    .map { it.body()!! }
                    .flatMap { it.appnews.newsitems }
            }.await()
        }
    }


}