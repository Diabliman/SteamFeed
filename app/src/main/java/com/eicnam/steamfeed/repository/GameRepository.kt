package com.eicnam.steamfeed.repository

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.room.Room
import com.eicnam.steamfeed.MainActivity
import com.eicnam.steamfeed.model.Game
import com.eicnam.steamfeed.model.GameDao
import com.eicnam.steamfeed.model.GameDatabase

class GameRepository(private val gameDao: GameDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun insertAll(games:List<Game>){
        gameDao.insertAll(games)
    }

    fun getAll(): List<Game> {
        return gameDao.getAll()
    }




}