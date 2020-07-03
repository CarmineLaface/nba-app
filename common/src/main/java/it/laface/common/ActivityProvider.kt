package it.laface.common

import android.app.Activity
import android.app.Application
import android.os.Bundle

interface ActivityProvider {

    val currentActivity: Activity?
}

object ActivityRegister : Application.ActivityLifecycleCallbacks, ActivityProvider {

    override var currentActivity: Activity? = null
        private set

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        currentActivity = activity
    }

    override fun onActivityStarted(activity: Activity): Unit = Unit

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityResumed(activity: Activity) {
        if (currentActivity != activity) {
            currentActivity = activity
        }
    }

    override fun onActivityPaused(activity: Activity): Unit = Unit

    override fun onActivityStopped(activity: Activity): Unit = Unit

    override fun onActivityDestroyed(activity: Activity) {
        if (currentActivity == activity) {
            currentActivity = null
        }
    }
}
