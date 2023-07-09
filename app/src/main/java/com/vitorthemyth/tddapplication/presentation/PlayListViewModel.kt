package com.vitorthemyth.tddapplication.presentation

import androidx.lifecycle.*
import com.vitorthemyth.tddapplication.domain.PlayListRepository
import com.vitorthemyth.tddapplication.domain.models.Playlist
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlayListViewModel(
    private val repository: PlayListRepository
) : ViewModel(){

    val playlists = liveData<Result<List<Playlist>>> {
        emitSource(repository.getPlayLists().asLiveData())
    }


}
