package me.tbandawa.android.pcgames.data.repo

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.tbandawa.android.pcgames.data.api.BaseApiCall
import me.tbandawa.android.pcgames.data.api.GamesApi
import me.tbandawa.android.pcgames.data.models.Game
import me.tbandawa.android.pcgames.data.models.NetworkResult

class GamesRepo(
    private val gamesApi: GamesApi,
    private val ioDispatcher: CoroutineDispatcher
): BaseApiCall() {

    suspend fun getGames(platform: String): Flow<NetworkResult<List<Game>>> = flow {
        emit(NetworkResult.Loading)
        emit(handleApiCall {
            gamesApi.getGames(platform)
        })
    }.flowOn(ioDispatcher)
}