package com.shammer.udemytdd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PlaylistFragment : Fragment() {

    lateinit var viewModel: PlaylistViewModel
    lateinit var viewModelFactory: PlaylistViewModelFactory
    private var repository : PlaylistRepository = PlaylistRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        setupViewModel()
        viewModel.playlists.observe(this as LifecycleOwner, { playlists ->
            if (playlists.getOrNull() != null) {
                setupList(view, playlists.getOrNull()!!)
            } else {
                // TODO: handle error
            }
        })
        return view
    }

    private fun setupList(
        view: View?,
        playlists: List<Playlist>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyPlaylistRecyclerViewAdapter(playlists)
        }
    }

    private fun setupViewModel() {
        viewModelFactory = PlaylistViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PlaylistFragment().apply {}
    }
}