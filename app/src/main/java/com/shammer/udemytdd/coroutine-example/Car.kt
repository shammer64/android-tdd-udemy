package com.shammer.udemytdd.`coroutine-example`

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Car(
    var fuelRemaining: Double,
    val engine: Engine
) {
    fun start() {
        CoroutineScope(Dispatchers.Main).launch {
            engine.start()
        }
        fuelRemaining -= 0.5
    }

}
