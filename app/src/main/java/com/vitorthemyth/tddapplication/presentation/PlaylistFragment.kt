package com.vitorthemyth.tddapplication.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vitorthemyth.tddapplication.R
import com.vitorthemyth.tddapplication.databinding.FragmentPlayListBinding
import com.vitorthemyth.tddapplication.domain.models.Playlist
import com.vitorthemyth.tddapplication.utils.MyPlayListRecyclerAdapter
import com.vitorthemyth.tddapplication.utils.PlaylistViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    private lateinit var binding: FragmentPlayListBinding

    private lateinit var viewModel: PlayListViewModel

    @Inject
    lateinit var viewModelFactory: PlaylistViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setupViewModel()
        binding = FragmentPlayListBinding.inflate(inflater, container, false)
        return binding.root
    }


    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[PlayListViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.loader.observe(this as LifecycleOwner) { loading ->
           when(loading){
               true -> binding.loader.visibility = View.VISIBLE
               else ->  binding.loader.visibility = View.GONE
           }
        }

        viewModel.playlists.observe(this as LifecycleOwner) { playlists ->
            if (playlists.getOrNull() != null) {
                setupList(binding.rvPlaylist, playlists.getOrDefault(listOf()))
            }
        }
    }

    private fun setupList(
        view: View,
        playlists: List<Playlist>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyPlayListRecyclerAdapter(playlists){id->
                val action = PlaylistFragmentDirections.actionPlaylistFragmentToPlaylistDetailFragment(id)
                findNavController().navigate(action)
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            PlaylistFragment().apply {

            }
    }
}