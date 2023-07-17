package com.vitorthemyth.tddapplication.presentation

import androidx.lifecycle.*
import com.vitorthemyth.tddapplication.domain.PlayListRepository
import com.vitorthemyth.tddapplication.domain.models.Playlist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PlayListViewModel(
    private val repository: PlayListRepository
) : ViewModel(){

    val loader = MutableLiveData<Boolean>()

    val playlists = liveData {
        loader.postValue(true)
        emitSource(repository.getPlayLists()
            .onEach {
                loader.postValue(false)
            }
            .asLiveData())
    }


}
