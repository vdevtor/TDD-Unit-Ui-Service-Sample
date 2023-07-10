package com.vitorthemyth.tddapplication.data

import com.vitorthemyth.tddapplication.domain.PlaylistMapper
import com.vitorthemyth.tddapplication.data.network.PlaylistService
import com.vitorthemyth.tddapplication.domain.PlayListRepository
import com.vitorthemyth.tddapplication.domain.models.Playlist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaylistRepositoryImp @Inject constructor(
    private val service: PlaylistService,
    private val mapper: PlaylistMapper
) : PlayListRepository {

    override suspend fun getPlayLists(): Flow<Result<List<Playlist>>> {
        return service.fetchPlaylists().map {
            if (it.isSuccess) {
                Result.success(mapper(it.getOrNull()!!))
            } else {
                Result.failure(it.exceptionOrNull()!!)
            }
        }
    }




}
