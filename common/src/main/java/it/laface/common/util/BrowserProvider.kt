package it.laface.common.util

import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import it.laface.common.ActivityProvider

interface BrowserProvider {

    fun openWebPage(url: String)
}

class BrowserProviderImpl(private val activityProvider: ActivityProvider) : BrowserProvider {

    private val customTabsIntent: CustomTabsIntent by lazy(CustomTabsIntent.Builder()::build)

    override fun openWebPage(url: String) {
        activityProvider.currentActivity?.let { activity ->
            customTabsIntent.launchUrl(activity, url.toUri())
        }
    }
}
