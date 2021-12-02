package com.shammer.udemytdd.`coroutine-example`

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CarTest {
    private val engine : Engine = mock()
    private val car : Car

    init {
        runBlockingTest {
            whenever(engine.start()).thenReturn(flow {
                delay(2000)
                emit(25)
                delay(2000)
                emit(50)
                delay(2000)
                emit(95)
            })
        }
        car = Car(5.0, engine)
    }

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