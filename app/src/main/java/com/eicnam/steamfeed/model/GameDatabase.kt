package com.eicnam.steamfeed.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Game::class], version = 2)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {
        @Volatile
        private var INSTANCE: GameDatabase? = null

        fun getDBConnection(context: Context): GameDatabase {
            return INSTANCE ?: synchronized(this) {
                return Room.databaseBuilder(
                    context,
                    GameDatabase::class.java, "game-database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}