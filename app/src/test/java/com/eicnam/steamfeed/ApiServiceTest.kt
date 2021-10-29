package com.eicnam.steamfeed

import com.eicnam.steamfeed.objects.ApiClient
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test


class ApiServiceTest {

    @Test
    fun testApi() {

        CoroutineScope(Dispatchers.IO).launch {
            /*
             * For @Query: You need to replace the following line with val response = service.getEmployees(2)
             * For @Path: You need to replace the following line with val response = service.getEmployee(53)
             */

            // Do the GET request and get response
            val response = ApiClient.apiService.getGames()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()

                    val lang = arrayOf("Java", "Node", "Kotlin", "JavaScript")

                    val prettyJson = gson.toJson(lang)

                    print("Pretty Printed JSON :$prettyJson")

                } else {

                    print("RETROFIT_ERROR" + response.code().toString())

                }
            }
            assertFalse(response.isSuccessful)
        }
    }
}