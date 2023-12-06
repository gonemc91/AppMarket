package com.example.common_ipl

import android.content.Context
import com.example.common.Resources

/**
 * Default implementation of [Resources] which fetches strings from application context.
 */

class AndroidResources (
    private val applicationContext: Context
): Resources {

    override fun getString(id: Int): String {
        return applicationContext.getString(id)
    }

    override fun getString(id: Int, vararg placeholder: Any): String {
        return applicationContext.getString(id, placeholder)
    }
}