package com.shammer.udemytdd.playlist

import com.shammer.udemytdd.R

data class Playlist(
    val id: String,
    val name: String,
    val category: String,
    val image: Int = R.mipmap.playlist
)
