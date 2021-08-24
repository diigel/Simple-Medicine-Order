package com.dani.simplemedicineorder.utils.state

import android.app.AlertDialog

sealed class ResultState<T >{
    class Loading<T : Any> : ResultState<T>()
    class Idle<T : Any> : ResultState<T>()
    data class Success<T : Any>(val data: T) : ResultState<T>()
    data class Error<T : Any>(val throwable: Throwable) : ResultState<T>()
}

fun <T>ResultState<T>.bindToAlertDialog(alertDialog: AlertDialog?){
    if (this is ResultState.Loading){
        if (alertDialog?.isShowing == true) {
            alertDialog.dismiss()
            alertDialog.show()
        }else{
            alertDialog?.show()
        }
    }else {
        alertDialog?.dismiss()
    }
}
