package com.shammer.udemytdd.`coroutine-example`

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class EngineTest {

    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @Test
    fun engineDefaultsToNotRunning() {
        val engine = Engine(2000, 189)
        assertEquals(false, engine.isRunning)
    }

    @Test
    fun engineDefaultsTo15Degrees() {
        val engine = Engine(2000, 189)
        assertEquals(15, engine.temperature)
    }

    @Test
    fun engineStartsWhenNotRunning() = runBlockingTest {
        val engine = Engine(2000, 189)
        engine.start()
        assertEquals(true, engine.isRunning)
    }

    @Test
    fun engineRemainsStartedWhenRunning() = runBlockingTest {
        val engine = Engine(2000, 189, 95, true)
        engine.start()
        assertEquals(true, engine.isRunning)
    }

    @Test
    fun engineIncreasesTemperatureGraduallyTo95WhenStarted() = runBlockingTest {
        val engine = Engine(2000, 189)
        val flow: Flow<Int> = engine.start()
        val actual = flow.toList()
        assertEquals(listOf(25, 50 , 95), actual)
    }

    @Test
    fun engineStopsWhenRunning() {
        val engine = Engine(2000, 189, 95, true)
        engine.stop()
        assertEquals(false, engine.isRunning)
    }

    @Test
    fun engineRemainsStoppedWhenNotRunning() {
        val engine = Engine(2000, 189)
        engine.stop()
        assertEquals(false, engine.isRunning)
    }

    @Test
    fun engineReducesTemperatureTo15WhenStopped() {
        val engine = Engine(2000, 189, 95, true)
        engine.stop()
        assertEquals(15, engine.temperature)
    }
}