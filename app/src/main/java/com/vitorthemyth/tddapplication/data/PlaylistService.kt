package com.vitorthemyth.tddapplication.data

import com.vitorthemyth.tddapplication.PlaylistRaw
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlaylistService @Inject constructor(
    private val api: PlaylistAPI
) {

    suspend fun fetchPlaylists() : Flow<Result<List<PlaylistRaw>>> {
        return flow {
            emit(Result.success(api.fetchPlaylists()))
        }.catch {
            emit(Result.failure(java.lang.RuntimeException("Something went wrong")))
        }
    }

}
