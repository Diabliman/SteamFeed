package com.eicnam.steamfeed.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "game")
data class Game(
    @PrimaryKey val appid: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "subbed", defaultValue = "false") val subbed: Boolean?
)

data class Apps(
    val apps: List<Game>
)

data class Applist(
    val applist: Apps
)