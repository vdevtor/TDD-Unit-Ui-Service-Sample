package com.vitorthemyth.tddapplication.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vitorthemyth.tddapplication.data.PlaylistRepositoryImp
import com.vitorthemyth.tddapplication.presentation.PlayListViewModel
import javax.inject.Inject

class PlaylistViewModelFactory @Inject constructor(
    private val repository: PlaylistRepositoryImp
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayListViewModel(repository) as T
    }
}
