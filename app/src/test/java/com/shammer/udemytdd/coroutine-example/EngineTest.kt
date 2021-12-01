package com.shammer.udemytdd.`coroutine-example`

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
    fun engineIncreasesTemperatureTo95WhenStarted() = runBlockingTest {
        val engine = Engine(2000, 189)
        engine.start()
        assertEquals(95, engine.temperature)
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