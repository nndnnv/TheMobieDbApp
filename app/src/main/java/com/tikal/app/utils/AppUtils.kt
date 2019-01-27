package com.tikal.app.utils

import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkInfo



object AppUtils
{
    fun hasInternetConnection(): Boolean {
        val connectivityManager = App.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }

    fun isTablet(): Boolean {
        return App.context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
    }

    fun isLandscape(): Boolean {
        return App.context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }
}