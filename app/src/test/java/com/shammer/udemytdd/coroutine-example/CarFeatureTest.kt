package com.shammer.udemytdd.`coroutine-example`

import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class CarFeatureTest {
    private val engine = Engine(500, 195, 15, false)
    private val car = Car(6.0, engine)

    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @Test
    fun carIsUsingFuelWhenStarted() = runBlockingTest {
        car.start()
        coroutineTestRule.advanceTimeBy(6500)
        assertEquals(5.5, car.fuelRemaining, 0.0)
    }

    @Test
    fun carStartsEngineAndGraduallyIncreasesTemperature() = runBlockingTest {
        car.start()
        coroutineTestRule.advanceTimeBy(2100)
        assertEquals(25, car.engine.temperature)
        coroutineTestRule.advanceTimeBy(2000)
        assertEquals(50, car.engine.temperature)
        coroutineTestRule.advanceTimeBy(2000)
        assertEquals(95, car.engine.temperature)
        assertTrue(car.engine.isRunning)
    }
}