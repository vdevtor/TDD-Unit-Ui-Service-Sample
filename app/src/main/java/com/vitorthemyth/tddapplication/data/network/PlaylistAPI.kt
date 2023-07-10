package com.vitorthemyth.tddapplication.data.network

import com.vitorthemyth.tddapplication.data.model.PlaylistRaw
import com.vitorthemyth.tddapplication.domain.models.PlaylistDetails
import retrofit2.http.GET

interface PlaylistAPI {

   @GET("908c3c4a-b3fe-4060-8609-9f0ce8871c60")
   suspend fun fetchPlaylists() : List<PlaylistRaw>

   @GET("67d835ad-04df-4b06-af2a-c06cc3d96b03")
   suspend fun fetchSinglePlayList() : PlaylistDetails
}
