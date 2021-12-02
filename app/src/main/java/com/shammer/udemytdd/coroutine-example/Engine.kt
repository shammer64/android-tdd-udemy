package com.shammer.udemytdd.`coroutine-example`

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Engine(
    val cc: Int,
    val horsePower: Int,
    var temperature: Int = 15,
    var isRunning: Boolean = false
) {
    suspend fun start(): Flow<Int> {
        isRunning = true

        return flow {
            delay(2000)
            temperature = 25
            emit(temperature)
            delay(2000)
            temperature = 50
            emit(temperature)
            delay(2000)
            temperature = 95
            emit(temperature)
            Log.d("UDEMY", "Engine has started")
        }
    }

    fun stop() {
        isRunning = false
        temperature = 15
    }
}