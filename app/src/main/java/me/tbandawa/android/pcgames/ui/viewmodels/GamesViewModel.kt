package me.tbandawa.android.pcgames.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.tbandawa.android.pcgames.data.models.Game
import me.tbandawa.android.pcgames.data.models.NetworkResult
import me.tbandawa.android.pcgames.data.repo.GamesRepo

class GamesViewModel(
    private val gamesRepo: GamesRepo,
    ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private var job: Job? = null
    private val coroutineScope = CoroutineScope(ioDispatcher + SupervisorJob())

    private val _game = MutableStateFlow<Game?>(null)
    val game: StateFlow<Game?> = _game

    private val _games = MutableStateFlow<NetworkResult<List<Game>>>(NetworkResult.Idle)
    val games: StateFlow<NetworkResult<List<Game>>> = _games

    fun getResources(platform: String) {
        job = coroutineScope.launch {
            gamesRepo.getGames(platform).collect { results ->
                _games.value = results
            }
        }
    }

    fun setGame(game: Game) {
        _game.value = game
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}