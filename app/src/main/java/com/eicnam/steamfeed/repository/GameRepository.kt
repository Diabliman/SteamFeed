package com.eicnam.steamfeed.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.eicnam.steamfeed.model.Game
import com.eicnam.steamfeed.model.GameDao
import kotlinx.coroutines.flow.Flow

class GameRepository(private val gameDao: GameDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAll(games: List<Game>) {
        gameDao.insertAll(games)
    }

    fun getAll(): Flow<List<Game>> {
        return gameDao.getAll()
    }

    fun findGamesByNameStart(gameName: String): LiveData<List<Game>> {
        return gameDao.findGamesByNameStart("%$gameName%")
    }

    fun subscribe(id: String) {
        gameDao.subGame(id)
    }

    fun unSubscribe(id: String) {
        gameDao.unSubGame(id)
    }


    suspend fun getSubbedGames(): List<Game> {
        return gameDao.getSubbedGames()
    }



}