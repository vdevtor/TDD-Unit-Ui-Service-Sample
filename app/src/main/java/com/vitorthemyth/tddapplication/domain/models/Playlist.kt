package com.vitorthemyth.tddapplication.domain.models

import com.vitorthemyth.tddapplication.R

data class Playlist(
 val id : String,
 val name : String,
 val category : String,
 val image : Int = R.mipmap.playlist
)