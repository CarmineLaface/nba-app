package it.laface.navigation

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import it.laface.common.ActivityProvider

class SnackbarHandler(
    private val activityProvider: ActivityProvider,
    private val bottomBarResId: Int,
) : MessageEmitter {

    override fun show(snackbarInfo: SnackbarInfo) {
        val activity = activityProvider.currentActivity
        val anchorView = activity?.findViewById<View>(bottomBarResId) ?: return
        val parentView = activity.getBaseView() ?: return
        val snackbar = make(snackbarInfo, parentView)
        snackbarInfo.action?.let {
            snackbar.setAction(it)
        }
        snackbar.anchorView = anchorView
        snackbar.show()
    }

    private fun make(snackbarInfo: SnackbarInfo, view: View): Snackbar =
        when (val textInfo = snackbarInfo.text) {
            is Text.StringText ->
                Snackbar.make(view, textInfo.value, snackbarInfo.duration.durationRef)
            is Text.ResourceIdText ->
                Snackbar.make(view, textInfo.value, snackbarInfo.duration.durationRef)
        }

    private fun Snackbar.setAction(action: SnackbarAction): Snackbar =
        when (val textInfo = action.text) {
            is Text.StringText ->
                setAction(textInfo.value) { action.callback }
            is Text.ResourceIdText ->
                setAction(textInfo.value) { action.callback }
        }

    private val SnackbarDuration.durationRef: Int
        get() = when (this) {
            SnackbarDuration.Indefinite -> BaseTransientBottomBar.LENGTH_INDEFINITE
            SnackbarDuration.Short -> BaseTransientBottomBar.LENGTH_SHORT
            SnackbarDuration.Long -> BaseTransientBottomBar.LENGTH_LONG
        }

    private fun Activity.getBaseView(): View? =
        findViewById<ViewGroup>(android.R.id.content)
            ?.getChildAt(0)
}
