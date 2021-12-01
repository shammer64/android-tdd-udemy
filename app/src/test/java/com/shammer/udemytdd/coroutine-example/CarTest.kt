package com.shammer.udemytdd.`coroutine-example`

import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class CarTest {
    private val engine : Engine = mock()
    private val car = Car(5.0, engine)

    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @Test
    fun carShouldUseFuelWhenStarted() = runBlockingTest {
        car.start()
        assertEquals(4.5, car.fuelRemaining, 0.0)
    }

    @Test
    fun carShouldStartEngineWhenStarted() = runBlockingTest {
        car.start()
        verify(engine, times(1)).start()
    }
}