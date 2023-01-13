package com.mohamedmabrouk.estarta_task.utils

import android.content.Context
import com.mohamedmabrouk.estarta_task.R
import com.mohamedmabrouk.estarta_task.utils.Constants.NETWORK_ERROR
import com.mohamedmabrouk.estarta_task.utils.Constants.NO_DATA
import com.mohamedmabrouk.estarta_task.utils.Constants.NO_INTERNET_CONNECTION

class ErrorResolver(val context: Context) {
    fun resolveError(errorCode: Int?): String {
        return when (errorCode) {
            NO_INTERNET_CONNECTION -> context.getString(R.string.error_internet_connection)
            NETWORK_ERROR -> context.getString(R.string.network_error)
            NO_DATA -> context.getString(R.string.no_data)
            else -> context.getString(R.string.network_error)
        }
    }
}