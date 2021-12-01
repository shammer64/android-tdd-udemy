package com.shammer.udemytdd.vehicle

import android.util.Log
import kotlinx.coroutines.delay

class Engine(
    val cc: Int,
    val horsePower: Int,
    var temperature: Int = 15,
    var isRunning: Boolean = false
) {
    suspend fun start() {
        isRunning = true
        delay(6000)
        temperature = 95
        Log.d("UDEMY", "Engine has started")
    }

    fun stop() {
        isRunning = false
        temperature = 15
    }
}