package com.example.common.flow

import com.example.common.Core
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

class DefaultLazyFlowSubjectFactory(
    private val dispatcher: CoroutineDispatcher,
    private val globalScope: CoroutineScope = Core.globalScope,
    private val cacheTimeoutMills: Long = 1000
): LazyFlowSubjectFactory {
    override fun <T> create(loader: ValueLoader<T>): LazyFlowSubject<T> {
        return DefaultLazyFlowSubject(loader,dispatcher, globalScope, cacheTimeoutMills)
    }
}