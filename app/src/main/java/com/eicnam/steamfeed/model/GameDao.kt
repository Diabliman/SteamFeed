package com.eicnam.steamfeed.model

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(games: List<Game>)

    @Delete
    fun delete(game: Game)

    @Query("SELECT * FROM game")
    fun getAll(): Flow<List<Game>>

    @Query("SELECT * FROM game WHERE name LIKE :search LIMIT 20")
    fun findGamesByNameStart(search: String): LiveData<List<Game>>
    
    @Query("UPDATE game SET subbed=1 where appid=:id")
    fun subGame(id: String)

    @Query("UPDATE game SET subbed=0 where appid=:id")
    fun unSubGame(id: String)

    @Query("SELECT * FROM game WHERE subbed=1")
    fun getSubbedGames(): List<Game>
}