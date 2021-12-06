package com.shammer.udemytdd.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class PlaylistService(
    private val api: PlaylistAPI
) {

    fun fetchPlaylists() : Flow<Result<List<Playlist>>> {
        return flow {
            emit(Result.success(api.fetchAllPlaylists()))
        }.catch {
            emit(Result.failure(RuntimeException("Problem fetching playlists.")))
        }
    }

}
