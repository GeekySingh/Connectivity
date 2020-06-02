package com.gappscorp.connectivity.demo

import android.app.Application
import com.gappscorp.connectivity.Connectivity

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // initialize connectivity library
        Connectivity.bind(this)
    }
}