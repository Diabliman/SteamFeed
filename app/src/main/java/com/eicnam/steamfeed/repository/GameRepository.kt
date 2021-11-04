package com.eicnam.steamfeed.repository

import androidx.annotation.WorkerThread
import com.eicnam.steamfeed.model.Game
import com.eicnam.steamfeed.model.GameDao

class GameRepository(private val gameDao: GameDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAll(games: List<Game>) {
        gameDao.insertAll(games)
    }

    fun getAll(): List<Game> {
        return gameDao.getAll()
    }

    fun subscribe(id: String) {
        gameDao.subGame(id)
    }

    fun unSubscribe(id: String) {
        gameDao.unSubGame(id)
    }

    fun getSubbedGames(): List<Game> {
        return gameDao.getSubbedGames()
    }


}