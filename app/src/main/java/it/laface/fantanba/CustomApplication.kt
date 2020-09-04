package it.laface.fantanba

import android.app.Application

@Suppress("unused")
class CustomApplication : Application() {

    var customFragmentFactory: CustomFragmentFactory? = null

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(ActivityRegister)
        customFragmentFactory = CustomFragmentFactory(cacheDir.absolutePath)
    }
}
