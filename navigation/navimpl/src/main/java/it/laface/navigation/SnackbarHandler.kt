package it.laface.navigation

import android.view.View
import com.google.android.material.snackbar.Snackbar
import it.laface.common.ActivityProvider

class SnackbarHandler(
    private val activityProvider: ActivityProvider,
    private val bottomBarResId: Int,
) : MessageEmitter {

    override fun show(snackbarInfo: SnackbarInfo) {
        val view: View = activityProvider.currentActivity?.getBaseView() ?: return
        val snackbar = make(snackbarInfo, view)
        snackbarInfo.action?.let {
            snackbar.setAction(it)
        }
        snackbar.setAnchorView(bottomBarResId)
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
                setAction(textInfo.value, action.callback.toClickListener())
            is Text.ResourceIdText ->
                setAction(textInfo.value, action.callback.toClickListener())
        }
}
