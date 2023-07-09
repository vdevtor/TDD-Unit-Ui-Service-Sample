package com.vitorthemyth.tddapplication.data

import com.vitorthemyth.tddapplication.PlaylistService
import com.vitorthemyth.tddapplication.domain.PlayListRepository
import com.vitorthemyth.tddapplication.domain.models.Playlist
import kotlinx.coroutines.flow.Flow

class PlaylistRepositoryImp(
    private val service: PlaylistService
) : PlayListRepository {

    override suspend fun getPlayLists(): Flow<Result<List<Playlist>>> =
        service.fetchPlaylists()

}
