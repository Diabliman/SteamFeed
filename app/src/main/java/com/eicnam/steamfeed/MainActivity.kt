package com.eicnam.steamfeed

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.eicnam.steamfeed.databinding.ActivityMainBinding
import com.eicnam.steamfeed.model.Applist
import com.eicnam.steamfeed.objects.ApiClient
import com.eicnam.steamfeed.viewmodel.GameViewModel
import com.eicnam.steamfeed.viewmodel.GameViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initGames()
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

    fun initGames() {
        val searchViewModel: GameViewModel by viewModels { GameViewModelFactory(applicationContext) }

        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiClient.apiService.getGames()
            if (response.isSuccessful) {
                val body: Applist = response.body() ?: throw IllegalStateException()
                searchViewModel.insertAll(body.applist.apps)
            } else {
                println(response.errorBody())
            }
        }
    }
}