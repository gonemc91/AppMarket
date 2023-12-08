package com.example.wiring

import android.content.Context
import com.example.common.AppRestarter
import com.example.common.CommonUi
import com.example.common.Core
import com.example.common.CoreProvider
import com.example.common.ErrorHandler
import com.example.common.Logger
import com.example.common.Resources
import com.example.common.ScreenCommunication
import com.example.common.flow.DefaultLazyFlowSubjectFactory
import com.example.common.flow.LazyFlowSubjectFactory
import com.example.common_ipl.ActivityRequired
import com.example.common_ipl.DefaultCoreProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ElementsIntoSet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class CoreProviderModule {

    @Provides
    fun providesCoreProvider(
        @ApplicationContext context: Context,
        appRestarter: AppRestarter
    ): CoreProvider{
        return DefaultCoreProvider(
            appContext = context,
            appRestarter = appRestarter
        )
    }

    @Provides
    @ElementsIntoSet
    fun provideActivityRequestSet(
        commonUi: CommonUi,
        screenCommunication: ScreenCommunication
    ): Set<@JvmSuppressWildcards ActivityRequired>{
        val set = hashSetOf<ActivityRequired>()
        if(commonUi is ActivityRequired) set.add(commonUi)
        if(screenCommunication is ActivityRequired) set.add(screenCommunication)
        return set
    }

    @Provides
    fun provideScreenCommunication(): ScreenCommunication{
        return Core.screenCommunication
    }

    @Provides
    fun provideCoroutineScope() : CoroutineScope{
        return Core.globalScope
    }

    @Provides
    fun provideCommonUi() : CommonUi{
        return Core.commonUi
    }

    @Provides
    fun providerLogger(): Logger{
        return Core.logger
    }

    @Provides
    fun providerResources(): Resources{
        return Core.resources
    }

    @Provides
    fun provideErrorHandler(): ErrorHandler{
        return Core.errorHandler
    }

    @Provides
    @Singleton
    fun provideLazyFlowSubjectFactory(): LazyFlowSubjectFactory{
        return DefaultLazyFlowSubjectFactory(
            dispatcher = Dispatchers.IO
        )
    }

}