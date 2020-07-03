package it.laface.navigation

import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import it.laface.common.ActivityProvider
import it.laface.common.navigation.EventType
import it.laface.common.navigation.MessageEmitter
import it.laface.common.navigation.SnackbarAction
import it.laface.common.navigation.SnackbarInfo
import it.laface.common.navigation.Text

class SnackbarHandler(private val activityProvider: ActivityProvider) : MessageEmitter {

    override fun show(snackbar: EventType.SnackBar) {
        val view: View = getView() ?: return
        val snackbarInfo = snackbar.info
        with(make(snackbarInfo, view)) {
            snackbarInfo.action?.let {
                setAction(it)
            }
            show()
        }
    }

    private fun make(snackbarInfo: SnackbarInfo, view: View): Snackbar =
        when (val textInfo = snackbarInfo.text) {
            is Text.StringText ->
                Snackbar.make(view, textInfo.value, snackbarInfo.duration.durationRef)
            is Text.ResIdText ->
                Snackbar.make(view, textInfo.value, snackbarInfo.duration.durationRef)
        }

    private fun Snackbar.setAction(action: SnackbarAction): Snackbar =
        when (val textInfo = action.text) {
            is Text.StringText ->
                setAction(textInfo.value, action.callback.toClickListener())
            is Text.ResIdText ->
                setAction(textInfo.value, action.callback.toClickListener())
        }

    private fun getView(): View? =
        (activityProvider.currentActivity?.findViewById(android.R.id.content) as? ViewGroup)
            ?.getChildAt(0)
}
