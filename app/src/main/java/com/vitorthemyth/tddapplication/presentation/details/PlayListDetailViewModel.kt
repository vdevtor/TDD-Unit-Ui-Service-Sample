package com.vitorthemyth.tddapplication.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitorthemyth.tddapplication.data.network.PlaylistDetailsService
import com.vitorthemyth.tddapplication.domain.models.PlaylistDetails
import kotlinx.coroutines.launch

class PlayListDetailViewModel(
    private val service: PlaylistDetailsService
): ViewModel() {

    val details: MutableLiveData<Result<PlaylistDetails>> = MutableLiveData()

    fun getPlaylistDetails(id:String) = viewModelScope.launch {
        service.fetchDetails().collect(){
            details.postValue(it)
        }
    }
}
