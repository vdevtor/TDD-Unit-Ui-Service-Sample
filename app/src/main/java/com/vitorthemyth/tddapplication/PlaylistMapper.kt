package com.vitorthemyth.tddapplication

import com.vitorthemyth.tddapplication.domain.models.Playlist
import javax.inject.Inject

class PlaylistMapper @Inject constructor() : Function1<List<PlaylistRaw>,List<Playlist>>{
    override fun invoke(p1: List<PlaylistRaw>): List<Playlist> {
        TODO("Not yet implemented")
    }


}