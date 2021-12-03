package com.shammer.udemytdd.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlaylistRepository(
    private val service: PlaylistService
) {
    fun getPlaylists() : Flow<Result<List<Playlist>>> =
        service.fetchPlaylists()

}
