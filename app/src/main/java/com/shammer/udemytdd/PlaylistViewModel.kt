package com.shammer.udemytdd

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaylistViewModel(
    private val repository: PlaylistRepository
) : ViewModel() {

    init {
        repository.getPlaylists()
    }

    val playlists = MutableLiveData<Result<List<Playlist>>>()

}
