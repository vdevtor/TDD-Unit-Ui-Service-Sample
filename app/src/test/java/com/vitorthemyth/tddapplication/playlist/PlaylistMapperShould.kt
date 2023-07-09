package com.vitorthemyth.tddapplication.playlist

import com.vitorthemyth.tddapplication.domain.PlaylistMapper
import com.vitorthemyth.tddapplication.data.model.PlaylistRaw
import com.vitorthemyth.tddapplication.utils.BaseUnitTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import com.vitorthemyth.tddapplication.R


@RunWith(MockitoJUnitRunner::class)
class PlaylistMapperShould : BaseUnitTest() {

    private val playlistsRaw = PlaylistRaw("1", "name", "category")
    private val playlistsRawRock = PlaylistRaw("1", "name", "rock")
    private val mapper = PlaylistMapper()

    private val playlists = mapper(listOf(playlistsRaw))
    private val playlist = playlists[0]
    private val playlistRock = mapper(listOf(playlistsRawRock))[0]


    @Test
    fun `is keeping the same id`() {
        assertEquals(playlistsRaw.id, playlist.id)
    }

    @Test
    fun `is keeping the same name`() {
        assertEquals(playlistsRaw.name, playlist.name)
    }

    @Test
    fun `is keeping the same category`() {
        assertEquals(playlistsRaw.category, playlist.category)
    }

    @Test
    fun `map Default image when category is not rock`(){
        assertEquals(R.mipmap.playlist, playlist.image)
    }

    @Test
    fun `map rock image when category is  rock`(){
        assertEquals(R.mipmap.rock, playlistRock.image)
    }
}