package com.shammer.udemytdd.`coroutine-example`

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class Car(
    var fuelRemaining: Double,
    val engine: Engine
) {
    fun start() {
        CoroutineScope(Dispatchers.Main).launch {
            engine.start().collect { temperature ->
                Log.d("UDEMY", "Collected engine temperature: $temperature")
            }
        }
        fuelRemaining -= 0.5
    }

}
