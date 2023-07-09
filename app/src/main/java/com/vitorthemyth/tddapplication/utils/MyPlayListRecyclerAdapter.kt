package com.vitorthemyth.tddapplication.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.vitorthemyth.tddapplication.R
import com.vitorthemyth.tddapplication.domain.models.Playlist
import com.vitorthemyth.tddapplication.databinding.PlaylistItemBinding

class MyPlayListRecyclerAdapter(
    private val values: List<Playlist>
) : RecyclerView.Adapter<MyPlayListRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = PlaylistItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(private val binding: PlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Playlist) {
            binding.playlistName.text = item.name
            binding.playlistCategory.text = item.category
            binding.playlistImage.setImageResource(R.mipmap.playlist)
        }
    }
}