package com.vitorthemyth.tddapplication

import com.vitorthemyth.tddapplication.domain.models.Playlist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PlaylistService(
    private val api: PlaylistAPI
) {

    suspend fun fetchPlaylists() : Flow<Result<List<Playlist>>> {
        return flow {
            emit(Result.success(api.fetchPlaylists()))
        }.catch {
            emit(Result.failure(java.lang.RuntimeException("Something went wrong")))
        }
    }

}
