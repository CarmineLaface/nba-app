package it.laface.common.util

import androidx.fragment.app.Fragment
import java.io.Serializable

@Suppress("UNCHECKED_CAST")
fun <T : Serializable> Fragment.requireSerializable(key: String): T =
    requireArguments().getSerializable(key) as? T ?: throw IllegalStateException("Argument is not valid")
