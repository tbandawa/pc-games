package me.tbandawa.android.pcgames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.pcgames.ui.screens.GameScreen
import me.tbandawa.android.pcgames.ui.screens.GamesScreen
import me.tbandawa.android.pcgames.ui.theme.PCGamesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            PCGamesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController, startDestination = "games") {

                        composable(route = "games") {
                            GamesScreen(navController)
                        }

                        composable(route = "game") {
                            GameScreen(navController)
                        }
                    }
                }
            }
        }
    }
}