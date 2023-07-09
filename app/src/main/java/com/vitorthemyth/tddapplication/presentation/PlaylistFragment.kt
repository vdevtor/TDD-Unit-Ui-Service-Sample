package com.vitorthemyth.tddapplication.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vitorthemyth.tddapplication.PlaylistAPI
import com.vitorthemyth.tddapplication.PlaylistService
import com.vitorthemyth.tddapplication.data.PlaylistRepositoryImp
import com.vitorthemyth.tddapplication.data.RetrofitBuilder
import com.vitorthemyth.tddapplication.databinding.FragmentPlayListBinding
import com.vitorthemyth.tddapplication.domain.models.Playlist
import com.vitorthemyth.tddapplication.utils.MyPlayListRecyclerAdapter
import com.vitorthemyth.tddapplication.utils.PlaylistViewModelFactory


class PlaylistFragment : Fragment() {

    private lateinit var binding: FragmentPlayListBinding

    private lateinit var viewModel: PlayListViewModel
    private lateinit var viewModelFactory: PlaylistViewModelFactory

    private val api = RetrofitBuilder.getInstance().create(PlaylistAPI::class.java)


    private val service = PlaylistService(api)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setupViewModel()
        binding = FragmentPlayListBinding.inflate(inflater, container, false)
        return binding.root
    }


    private fun setupViewModel() {
        viewModelFactory = PlaylistViewModelFactory(PlaylistRepositoryImp(service))
        viewModel = ViewModelProvider(this, viewModelFactory)[PlayListViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.playlists.observe(this as LifecycleOwner) { playlists ->
            if (playlists.getOrNull() != null) {
                setupList(view, playlists.getOrDefault(listOf()))
            }
        }
    }

    private fun setupList(
        view: View,
        playlists: List<Playlist>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyPlayListRecyclerAdapter(playlists)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            PlaylistFragment().apply {

            }
    }
}