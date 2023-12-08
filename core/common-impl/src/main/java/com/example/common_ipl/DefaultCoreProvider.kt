package com.example.common_ipl

import android.content.Context
import com.example.common.AppRestarter
import com.example.common.CommonUi
import com.example.common.CoreProvider
import com.example.common.ErrorHandler
import com.example.common.Logger
import com.example.common.Resources
import com.example.common.ScreenCommunication
import kotlinx.coroutines.CoroutineScope

class DefaultCoreProvider(
    private val appContext: Context,
    override val appRestarter: AppRestarter,
    override var commonUi: CommonUi = AndroidCommonUi(appContext),
    override val resources: Resources = AndroidResources(appContext),
    override val screenCommunication: ScreenCommunication = DefaultScreenCommunication(),
    override val globalScope: CoroutineScope = createDefaultScope(),
    override val logger: Logger = AndroidLogger(),
    override val errorHandler: ErrorHandler = DefaultErrorHandler(
        logger, commonUi, resources, appRestarter, globalScope
    )
) : CoreProvider

