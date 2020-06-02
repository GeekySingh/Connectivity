package com.gappscorp.connectivity

import android.app.Activity
import android.app.Application
import android.os.Bundle

internal interface ActivityLifecycleCallback : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity) {
        // override if required
    }

    override fun onActivityStarted(activity: Activity) {
        // override if required
    }

    override fun onActivityDestroyed(activity: Activity) {
        // override if required
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // override if required
    }

    override fun onActivityStopped(activity: Activity) {
        // override if required
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        // override if required
    }

    override fun onActivityResumed(activity: Activity) {
        // override if required
    }
}