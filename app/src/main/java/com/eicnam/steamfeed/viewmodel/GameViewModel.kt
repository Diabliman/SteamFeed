package com.eicnam.steamfeed.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.eicnam.steamfeed.model.Applist
import com.eicnam.steamfeed.model.Game
import com.eicnam.steamfeed.model.GameDatabase
import com.eicnam.steamfeed.objects.ApiClient
import com.eicnam.steamfeed.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class GameViewModel(context: Context) : ViewModel() {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiClient.apiService.getGames()
            if (response.isSuccessful) {
                val body: Applist = response.body() ?: throw IllegalStateException()
                repository.insertAll(body.applist.apps)
            } else {
                println(response.errorBody())
            }
        }
    }


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

    fun findGamesByNameStart(gameName : String ): LiveData<List<Game>> {
        return repository.findGamesByNameStart(gameName)
    }


}