package com.example.catalog

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import java.util.Random


fun main(): Unit = runBlocking {
    repeat(100){
        withTimeout(12_000L){
            val result = doWork(it.toString())
            println(result)
        }
    }
}


val scope by lazy {
    CoroutineScope(SupervisorJob() + Dispatchers.Main)
}


suspend fun doWork(name: String): String{
    delay(Random().nextInt(5000).toLong())
    return "Done $name"
}
