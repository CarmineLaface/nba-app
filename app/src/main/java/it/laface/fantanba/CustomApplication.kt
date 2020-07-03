package it.laface.fantanba

import android.app.Application
import it.laface.common.ActivityRegister

@Suppress("unused")
class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(ActivityRegister)
    }
}
