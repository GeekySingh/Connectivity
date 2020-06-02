package com.gappscorp.connectivity

import android.app.Activity
import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

private const val DEFAULT_VALUE = false

interface ConnectionListener {
    fun onConnectivityChanged(connected: Boolean)
}

object Connectivity : LifecycleObserver {

    private val connectivityLiveData by lazy { ConnectivityLiveData(DEFAULT_VALUE) }

    val isConnected: Boolean get() = connectivityLiveData.value!!// ?: false

    fun bind(application: Application) {
        application.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallback {
            override fun onActivityResumed(activity: Activity) {
                if (activity is LifecycleOwner)
                    connectivityLiveData.bind(activity)
            }

            override fun onActivityPaused(activity: Activity) {
                connectivityLiveData.unBind()
            }
        })
    }

    fun addConnectionListener(listener: ConnectionListener) {
        connectivityLiveData.addConnectionListener(listener)
    }

    fun removeConnectionListener(listener: ConnectionListener) {
        connectivityLiveData.removeConnectionListener(listener)
    }
}