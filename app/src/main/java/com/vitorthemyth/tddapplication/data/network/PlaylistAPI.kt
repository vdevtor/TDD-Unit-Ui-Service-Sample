package com.vitorthemyth.tddapplication.data.network

import com.vitorthemyth.tddapplication.data.model.PlaylistRaw
import retrofit2.http.GET

interface PlaylistAPI {

   @GET("908c3c4a-b3fe-4060-8609-9f0ce8871c60")
   suspend fun fetchPlaylists() : List<PlaylistRaw>
}
