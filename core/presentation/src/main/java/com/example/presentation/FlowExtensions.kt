package com.example.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch



/**
 * Listen the [Flow] only in [Lifecycle.State.STARTED] state of the lifecycle.
 */

fun <T> Flow<T>.observeStateOn(lifecycleOwner: LifecycleOwner, bloc: (T) -> Unit) {
    lifecycleOwner.lifecycleScope.launch {
     TODO()
    }

}



