package com.vitorthemyth.tddapplication.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.vitorthemyth.tddapplication.databinding.FragmentPlaylistDetailBinding
import com.vitorthemyth.tddapplication.domain.models.PlaylistDetails
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class PlaylistDetailFragment : Fragment() {

    val args: PlaylistDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentPlaylistDetailBinding

    private lateinit var viewModel: PlayListDetailViewModel

    @Inject
    lateinit var viewModelFactory: PlaylistDetailViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setupViewModel()
        binding = FragmentPlaylistDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupViewModel() {
        val id = args.playlistId
        viewModel = ViewModelProvider(this, viewModelFactory)[PlayListDetailViewModel::class.java]
        viewModel.getPlaylistDetails(id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.details.observe(this as LifecycleOwner) { details ->
            if (details.getOrNull() != null){
                bindDetailsOnScreen(details.getOrNull())
            }else{
                //todo
            }

        }
    }

    private fun bindDetailsOnScreen(details: PlaylistDetails?) {
        binding.playlistTitle.text = details?.name
        binding.playlistDetails.text = details?.details
    }

}