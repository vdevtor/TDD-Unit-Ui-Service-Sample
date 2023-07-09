package com.vitorthemyth.tddapplication

import com.vitorthemyth.tddapplication.domain.models.Playlist
import javax.inject.Inject

class PlaylistMapper @Inject constructor() : Function1<List<PlaylistRaw>, List<Playlist>> {
    override fun invoke(playlistRaw: List<PlaylistRaw>): List<Playlist> {
        return playlistRaw.map {
            val image = when(it.category){
                "rock" -> R.mipmap.rock
                else -> R.mipmap.playlist
            }
            Playlist(id = it.id, name = it.name, category = it.category, image = image)
        }
    }


}
