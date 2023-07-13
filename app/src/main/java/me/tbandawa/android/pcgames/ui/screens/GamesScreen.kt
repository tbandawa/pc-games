package me.tbandawa.android.pcgames.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import me.tbandawa.android.pcgames.data.models.Game
import me.tbandawa.android.pcgames.data.models.NetworkResult
import me.tbandawa.android.pcgames.ui.components.GameItem
import me.tbandawa.android.pcgames.ui.components.GamesToolbar
import me.tbandawa.android.pcgames.ui.viewmodels.GamesViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesScreen(
    navController: NavController,
    viewModel: GamesViewModel = koinViewModel()
) {

    val gamesCount = remember { mutableStateOf("Discover the best games!") }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val gamesState by viewModel.games.collectAsState()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            GamesToolbar(
                title = gamesCount.value,
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when (gamesState) {
                is NetworkResult.Idle -> {
                    LoadScreen(
                        button = "Load Games",
                        load = { viewModel.getResources("pc") }
                    )
                }
                is NetworkResult.Loading -> {
                    LoadingScreen()
                }
                is NetworkResult.Success -> {
                    LazyColumn {
                        val gamesList = (gamesState as NetworkResult.Success<List<Game>>).data
                        gamesCount.value = "Total Games: ${gamesList.size}"
                        items(gamesList) { game ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(IntrinsicSize.Min)
                                    .padding(5.dp)
                                    .clickable {
                                        viewModel.setGame(game)
                                        navController.navigate("game")
                                    },
                                shape = RoundedCornerShape(5.dp)
                            ) {
                                GameItem(game = game)
                            }
                        }
                    }
                }
                is NetworkResult.Error -> {
                    LoadScreen(
                        button = "Retry",
                        message = (gamesState as NetworkResult.Error).data?.message.toString(),
                        load = { viewModel.getResources("pc") }
                    )
                }
            }
        }
    }
}