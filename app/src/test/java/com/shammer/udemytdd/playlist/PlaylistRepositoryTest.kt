package com.shammer.udemytdd.playlist

import com.shammer.udemytdd.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.lang.RuntimeException

class PlaylistRepositoryTest : BaseUnitTest() {

    private val exception = RuntimeException("There is a problem!")
    private val service: PlaylistService = mock()
    private val playlists = mock<List<Playlist>>()

    @Test
    fun shouldGetPlaylistsFromService() = runBlockingTest {
        val repository = PlaylistRepository(service)
        repository.getPlaylists()
        verify(service, times(1)).fetchPlaylists()
    }

    @Test
    fun shouldEmitPlaylistsReceivedFromService() = runBlockingTest {
        val repository = mockSuccessfulCase()
        assertEquals(playlists, repository.getPlaylists().first().getOrNull())
    }

    @Test
    fun shouldPropagateErrorsFromService() = runBlockingTest {
        val repository = mockFailureCase()
        assertEquals(exception, repository.getPlaylists().first().exceptionOrNull())
    }

    private fun mockFailureCase(): PlaylistRepository {
        whenever(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )
        val repository = PlaylistRepository(service)
        return repository
    }

    private fun mockSuccessfulCase(): PlaylistRepository {
        whenever(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.success(playlists))
            }
        )
        return PlaylistRepository(service)
    }
}