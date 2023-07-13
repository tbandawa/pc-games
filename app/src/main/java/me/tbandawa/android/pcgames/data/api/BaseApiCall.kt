package me.tbandawa.android.pcgames.data.api

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ResponseException
import io.ktor.utils.io.errors.IOException
import me.tbandawa.android.pcgames.data.models.ErrorResponse
import me.tbandawa.android.pcgames.data.models.NetworkResult

abstract class BaseApiCall {

    suspend fun <T> handleApiCall(
        apiCall: suspend () -> T
    ): NetworkResult<T> {
        return try {
            val response = apiCall()
            NetworkResult.Success(response)
        } catch (exception: ResponseException) {
            when(exception) {
                is ClientRequestException -> {
                    NetworkResult.Error(exception.response.body<ErrorResponse>())
                } else -> {
                NetworkResult.Error(exception.localizedMessage?.let { ErrorResponse(it) })
            }
            }
        } catch (e: IOException) {
            NetworkResult.Error(ErrorResponse("Unable to connect to host"))
        } catch (e: Exception) {
            NetworkResult.Error(ErrorResponse("Unknown Error - ${e.localizedMessage}"))
        }
    }

}