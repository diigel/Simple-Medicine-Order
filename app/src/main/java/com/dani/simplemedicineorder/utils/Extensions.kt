package com.dani.simplemedicineorder.utils

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.dani.simplemedicineorder.databinding.LoaderLayoutBinding
import com.dani.simplemedicineorder.utils.state.ResultState
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

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

fun <T: Any> ResultState<T>.getDataOrNull(): T? {
    return if (this is ResultState.Success) {
        return this.data
    } else {
        null
    }
}

fun <T: Any> ResultState<T>.onSuccess(result: (T) -> Unit) {
    if (this is ResultState.Success) {
        result.invoke(this.data)
    }
}

fun Int?.currencyFormatWithRp(): String {
    return try {
        var originalString = this?.toString() ?: "0"
        originalString = originalString.replace(",".toRegex(), "").replace(".", "")
        val longValue: Long = originalString.toLong()
        val formatter = NumberFormat.getInstance(Locale("en", "ID")) as DecimalFormat
        formatter.applyPattern("#,###,###,###")
        var finalValue = formatter.format(longValue)
        finalValue = if (finalValue.isNotEmpty()) "Rp $finalValue"
        else "Rp 0"
        finalValue.replace(",", ".")
    } catch (e: NumberFormatException) {
        e.printStackTrace()
        ""
    }
}

fun Context.loaderDialog(): AlertDialog {
    val builderProgress = AlertDialog.Builder(this)
        .setView(LoaderLayoutBinding.inflate(LayoutInflater.from(this)).root)
        .setCancelable(false)

    val dialogProgress = builderProgress.create()
    dialogProgress.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    return dialogProgress
}

fun String.apiToDateTime(): String? {
    val stringDateFormat = SimpleDateFormat("EEEE dd MMMM yyyy, HH:mm", Locale("id"))
    val apiDateFormatItem = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    val rawDate = apiDateFormatItem.parse(this)
    return stringDateFormat.format(rawDate!!)
}




