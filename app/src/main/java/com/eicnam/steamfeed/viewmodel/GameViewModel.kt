package com.eicnam.steamfeed.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.eicnam.steamfeed.model.Game
import com.eicnam.steamfeed.model.GameDatabase
import com.eicnam.steamfeed.repository.GameRepository

class GameViewModel(context: Context) : ViewModel() {

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


}