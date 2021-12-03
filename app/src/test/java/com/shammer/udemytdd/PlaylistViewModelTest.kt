package com.shammer.udemytdd

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shammer.udemytdd.util.MainCoroutineScopeRule
import com.shammer.udemytdd.util.getValueForTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class PlaylistViewModelTest {

    @get: Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewModel: PlaylistViewModel
    private val repository: PlaylistRepository = mock()

    init {
        viewModel = PlaylistViewModel(repository)
    }

    @Test
    fun shouldGetPlaylistsFromRepository() {
        viewModel.playlists.getValueForTest()
        verify(repository, times(1)).getPlaylists()
    }
}