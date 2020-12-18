package it.laface.fantanba

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Bundle
import it.laface.common.ActivityProvider

@SuppressLint("StaticFieldLeaks")
object ActivityRegister : Application.ActivityLifecycleCallbacks, ActivityProvider {

    override var currentActivity: Activity? = null
        private set

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        currentActivity = activity
    }

    override fun onActivityStarted(activity: Activity): Unit = Unit

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle): Unit = Unit

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
