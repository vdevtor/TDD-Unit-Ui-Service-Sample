package com.vitorthemyth.tddapplication.data.network


import com.vitorthemyth.tddapplication.domain.models.PlaylistDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlaylistDetailsService @Inject constructor(
    private val api: PlaylistAPI
) {

    suspend fun fetchDetails() : Flow<Result<PlaylistDetails>> {

        return flow {

            emit(Result.success(api.fetchSinglePlayList()))
        }.catch {
            emit(Result.failure(java.lang.RuntimeException("Something went wrong")))
        }
    }

}
