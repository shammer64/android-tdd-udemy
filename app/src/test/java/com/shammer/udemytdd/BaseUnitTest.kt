package com.shammer.udemytdd

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shammer.udemytdd.util.MainCoroutineScopeRule
import org.junit.Rule

open class BaseUnitTest {

    @get: Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

}
