package com.eicnam.steamfeed.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey


@Fts4
@Entity(tableName = "game")
data class Game(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "subbed") val subbed: Boolean?
)

data class Apps(
    val apps: List<Game>
)

data class Applis(
    val appList: Apps
)