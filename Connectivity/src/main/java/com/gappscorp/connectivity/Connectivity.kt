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

    val connectionLiveData by lazy { ConnectivityLiveData(DEFAULT_VALUE) }

    val isConnected: Boolean get() = connectionLiveData.value!!// ?: false

    fun bind(application: Application) {
        application.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallback {
            override fun onActivityResumed(activity: Activity) {
                if (activity is LifecycleOwner)
                    connectionLiveData.bind(activity)
            }

            override fun onActivityPaused(activity: Activity) {
                connectionLiveData.unBind()
            }
        })
    }

    fun addConnectionListener(listener: ConnectionListener) {
        connectionLiveData.addConnectionListener(listener)
    }

    fun removeConnectionListener(listener: ConnectionListener) {
        connectionLiveData.removeConnectionListener(listener)
    }
}