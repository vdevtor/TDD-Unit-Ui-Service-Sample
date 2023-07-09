package com.vitorthemyth.tddapplication.domain

import com.vitorthemyth.tddapplication.domain.models.Playlist
import kotlinx.coroutines.flow.Flow

interface PlayListRepository {

    suspend fun getPlayLists() : Flow<Result<List<Playlist>>>
}