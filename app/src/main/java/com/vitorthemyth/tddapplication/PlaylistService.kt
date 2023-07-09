package com.vitorthemyth.tddapplication

import com.vitorthemyth.tddapplication.domain.models.Playlist
import kotlinx.coroutines.flow.Flow

class PlaylistService {

    suspend fun fetchPlaylists() : Flow<Result<List<Playlist>>> {
        TODO("Not yet implemented")
    }

}
