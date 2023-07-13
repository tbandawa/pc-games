package me.tbandawa.android.pcgames.data.models

sealed class NetworkResult<out M> {
    data class Success<out M>(val data: M): NetworkResult<M>()
    data class Error<out M>(val data: ErrorResponse? = null) : NetworkResult<M>()
    object Loading : NetworkResult<Nothing>()
    object Idle : NetworkResult<Nothing>()
}