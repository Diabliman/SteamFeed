package com.eicnam.steamfeed.model

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class News(
    val gid: String,
    val title: String? = null,
    val url: String? = null,
    val is_external_url: Boolean? = null,
    val author: String? = null,
    val contents: String? = null,
    val feedlabel: String? = null,
    val date: Long,
    val feedname: String? = null,
    val feed_type: String? = null,
    val appid: String? = null
) {
    fun getGameBanner(): String {
        return "https://cdn.akamai.steamstatic.com/steam/apps/" + this.appid + "/header.jpg"
    }

    fun getDate(): String {
        val localDate = Instant.ofEpochSecond(date)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()

        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        return dateTimeFormatter.format(localDate)
    }
}

data class AppNews(
    val appid: String,
    val newsitems: List<News>,
    val count: Int
)

data class NewsList(
    val appnews: AppNews
)
