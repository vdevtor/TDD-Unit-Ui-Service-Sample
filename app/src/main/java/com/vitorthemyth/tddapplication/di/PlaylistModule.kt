package com.vitorthemyth.tddapplication.di

import com.vitorthemyth.tddapplication.data.PlaylistAPI
import com.vitorthemyth.tddapplication.data.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit

@Module
@InstallIn(FragmentComponent::class)
class PlaylistModule {

    @Provides
    fun playlistAPI(retrofit: Retrofit)  = retrofit.create(PlaylistAPI::class.java)

    @Provides
    fun retrofit() : Retrofit{
        return RetrofitBuilder.getInstance()
    }
}