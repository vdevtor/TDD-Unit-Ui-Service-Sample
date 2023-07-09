package com.vitorthemyth.tddapplication.playlist

import com.vitorthemyth.tddapplication.PlaylistAPI
import com.vitorthemyth.tddapplication.PlaylistService
import com.vitorthemyth.tddapplication.domain.models.Playlist
import com.vitorthemyth.tddapplication.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException
import org.mockito.Mockito.`when` as whenever


@RunWith(MockitoJUnitRunner::class)
class PlaylistServiceShould : BaseUnitTest() {

    private lateinit var service : PlaylistService
    @Mock
    private var playlists: List<Playlist> = mock(List::class.java) as List<Playlist>

    @Mock
    private val api : PlaylistAPI = mock(PlaylistAPI::class.java)


    @Test
    fun `if playlist is being fetched from api`() = runBlocking {
        service = PlaylistService(api)

        val job = launch {
            service.fetchPlaylists()
            verify(api,times(1)).fetchPlaylists()
        }

        job.cancel()
    }

    @Test
    fun `if result is being converted and emitted by flow`() = runBlocking{
        mockSuccessCase()

        assertEquals(Result.success(playlists),service.fetchPlaylists().first())

    }

    private suspend fun mockSuccessCase() {
        whenever(api.fetchPlaylists()).thenReturn(playlists)

        service = PlaylistService(api)
    }

    @Test
    fun `test if errors are being emitted properly`() = runBlocking{
        mockFailCase()

        assertEquals("Something went wrong",
            service.fetchPlaylists().first().exceptionOrNull()?.message)
    }

    private suspend fun mockFailCase() {
        whenever(api.fetchPlaylists()).thenThrow(RuntimeException("Damn BackEnd Developers"))
        service = PlaylistService(api)
    }

}