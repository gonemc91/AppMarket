package com.example.presentation.assignable

import com.example.presentation.live.LiveValue
import com.example.presentation.live.MutableLiveValue

internal class LiveValueAssignable<T>(
    private val liveValue: LiveValue<T>
): Assignable<T> {
    override fun setValue(value: T) {
        (liveValue as? MutableLiveValue<T>)?.setValue(value)
    }
}