package com.vitorthemyth.tddapplication

import com.vitorthemyth.tddapplication.domain.models.Playlist

interface PlaylistAPI {

   suspend fun fetchPlaylists() : List<Playlist>
}
