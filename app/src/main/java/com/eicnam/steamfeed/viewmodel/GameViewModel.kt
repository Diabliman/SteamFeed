package com.eicnam.steamfeed.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import com.eicnam.steamfeed.model.Game
import com.eicnam.steamfeed.model.GameDatabase
import com.eicnam.steamfeed.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class GameViewModel(context: Context) : ViewModel() {

    val searchQuery = MutableStateFlow("")
    private val gameFlow = searchQuery.flatMapLatest {
        findGamesByNameStart(it)
    }
    val games = gameFlow.asLiveData()

    private val repository: GameRepository =
        GameRepository(GameDatabase.getDBConnection(context).gameDao())

    suspend fun insertAll(games: List<Game>) {
        repository.insertAll(games)
    }

    fun getAll(): List<Game> {
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

    fun findGamesByNameStart(gameName : String ): Flow<List<Game>> {
        return repository.findGamesByNameStart(gameName).asLiveData().asFlow()
    }


}