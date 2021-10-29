package com.eicnam.steamfeed.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.eicnam.steamfeed.model.Game
import com.eicnam.steamfeed.model.GameDatabase
import com.eicnam.steamfeed.repository.GameRepository

class GameViewModel(context: Context) : ViewModel() {

    private val repository: GameRepository =
        GameRepository(GameDatabase.getDBConnection(context).gameDao())

    fun insertAll(games: List<Game>) {
        repository.insertAll(games)
    }

    fun getAll(): List<Game> {
        return repository.getAll()
    }


}