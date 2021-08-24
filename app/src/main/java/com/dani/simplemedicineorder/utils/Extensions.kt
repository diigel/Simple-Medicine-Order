package com.dani.simplemedicineorder.utils

import com.dani.simplemedicineorder.utils.state.ResultState
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun Any.toJson(): String {
    val gson = GsonBuilder()
        .setPrettyPrinting()
        .create()
    return gson.toJson(this)
}

suspend fun <T: Any> fetch(call: suspend () -> T): Flow<ResultState<T>> = flow {
    emit(ResultState.Loading())
    try {
        emit(ResultState.Success(data = call.invoke()))
    } catch (e: Throwable) {
        emit(ResultState.Error<T>(throwable = e))
    }
}

fun <T: Any> ResultState<T>.onSuccess(result: (T) -> Unit) {
    if (this is ResultState.Success) {
        result.invoke(this.data)
    }
}

fun <T: Any> ResultState<T>.getDataOrNull(): T? {
    return if (this is ResultState.Success) {
        return this.data
    } else {
        null
    }
}