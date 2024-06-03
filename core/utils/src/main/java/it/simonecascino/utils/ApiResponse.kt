package it.simonecascino.utils

import kotlinx.coroutines.flow.flow

sealed interface ApiResponse<out R> {

    data object Idle: ApiResponse<Nothing>

    data class Success<out R>(val result: R): ApiResponse<R>

    data object Loading: ApiResponse<Nothing>

    data class Failure(val reason: Throwable?): ApiResponse<Nothing>

}

fun <R, D> apiResponseFlow(
    mapper: Mapper<R, D>,
    call: suspend () -> R
) = flow {
    emit(ApiResponse.Loading)
    try {
        val result = call()
        val mappedResult = mapper(result)
        emit(ApiResponse.Success(mappedResult))
    } catch (e: Exception){
        e.printStackTrace()
        emit(ApiResponse.Failure(e))
    }
}

fun apiEmptyResponseFlow(
    call: suspend () -> Unit
) = apiResponseFlow(
    mapper = {},
    call = call
)