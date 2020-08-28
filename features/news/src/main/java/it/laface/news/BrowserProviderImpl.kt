package it.laface.news

import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsIntent.Builder
import androidx.core.net.toUri
import it.laface.common.ActivityProvider
import it.laface.news.domain.BrowserProvider

class BrowserProviderImpl(private val activityProvider: ActivityProvider) : BrowserProvider {

    private val customTabsIntent: CustomTabsIntent by lazy(
        Builder()
            .addDefaultShareMenuItem()
            .setShowTitle(true)
        ::build
    )

    override fun openWebPage(url: String) {
        activityProvider.currentActivity?.let { activity ->
            customTabsIntent.launchUrl(activity, url.toUri())
        }
    }
}
