package com.shammer.udemytdd.playlist

import com.shammer.udemytdd.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.lang.RuntimeException

class PlaylistServiceTest : BaseUnitTest() {

    private val api: PlaylistAPI = mock()
    private val playlists = mock<List<Playlist>>()
    private val exception = RuntimeException("Backend issue, do not propagate!")

    @Test
    fun shouldGetPlaylistsFromAPI() = runBlockingTest {
        val service = PlaylistService(api)
        service.fetchPlaylists().first()
        verify(api, times(1)).fetchAllPlaylists()
    }

    @Test
    fun shouldEmitPlaylistsReceivedFromAPI() = runBlockingTest {
        val service = mockSuccessfulCase()
        Assert.assertEquals(playlists, service.fetchPlaylists().first().getOrNull())
    }

    @Test
    fun shouldPropagateErrorsFromAPI() = runBlockingTest {
        val service = mockFailureCase()
        Assert.assertEquals("Problem fetching playlists.",
            service.fetchPlaylists().first().exceptionOrNull()?.message
        )
    }

    private fun mockFailureCase(): PlaylistService {
        whenever(api.fetchAllPlaylists()).thenThrow(exception)
        return PlaylistService(api)
    }

    private fun mockSuccessfulCase(): PlaylistService {
        whenever(api.fetchAllPlaylists()).thenReturn(playlists)
        return PlaylistService(api)
    }

}