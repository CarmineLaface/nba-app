package it.laface.fantanba

import android.app.Application

@Suppress("unused")
class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(ActivityRegister)
    }
}
