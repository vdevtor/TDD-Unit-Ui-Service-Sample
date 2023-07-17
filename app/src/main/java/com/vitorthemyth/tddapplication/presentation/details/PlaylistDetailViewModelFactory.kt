package com.vitorthemyth.tddapplication.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vitorthemyth.tddapplication.data.network.PlaylistDetailsService
import javax.inject.Inject

class PlaylistDetailViewModelFactory @Inject constructor(
    private val service: PlaylistDetailsService
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayListDetailViewModel(service) as T
    }
}
