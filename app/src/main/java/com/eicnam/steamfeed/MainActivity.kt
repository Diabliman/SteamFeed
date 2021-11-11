package com.eicnam.steamfeed

import android.os.Bundle
import android.webkit.WebView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.eicnam.steamfeed.databinding.ActivityMainBinding
import com.eicnam.steamfeed.model.Applist
import com.eicnam.steamfeed.model.News
import com.eicnam.steamfeed.objects.ApiClient
import com.eicnam.steamfeed.viewmodel.GameViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initGameList()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun initGameList() {
        CoroutineScope(Dispatchers.IO).launch {
            val gameViewModel = GameViewModel(applicationContext)
            val response = ApiClient.apiService.getGames()
            if (response.isSuccessful) {
                val body: Applist = response.body() ?: throw IllegalStateException()
                gameViewModel.insertAll(body.applist.apps)
            } else {
                println(response.errorBody())
            }
        }
    }

    private fun getNews() {
        CoroutineScope(Dispatchers.IO).launch {
            val gameViewModel = GameViewModel(applicationContext)
            val subbedGames = gameViewModel.getSubbedGames()

            val newsList: List<News> = subbedGames
                .map { ApiClient.apiService.getNewsPerGames(it.appid) }
                .filter { it.isSuccessful }
                .map { it.body()!! }
                .flatMap { it.appnews.newsitems }

            println(newsList)
        }

    }
}