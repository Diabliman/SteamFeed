package com.eicnam.steamfeed.model

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

class NewsTest {

    @Test
    fun testDate() {
        val date = LocalDateTime.now()
        val dateStr = date.dayOfMonth.toString() + "-" + date.monthValue + "-" + date.year
        val news = News("id", date = date.toEpochSecond(ZoneOffset.UTC))
        assertEquals(dateStr, news.getDate())
    }

}