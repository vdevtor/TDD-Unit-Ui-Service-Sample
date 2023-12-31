package com.vitorthemyth.tddapplication.playlist

import com.vitorthemyth.tddapplication.data.model.PlaylistRaw
import com.vitorthemyth.tddapplication.data.network.PlaylistAPI
import com.vitorthemyth.tddapplication.data.network.PlaylistService
import com.vitorthemyth.tddapplication.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever


@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class PlaylistServiceShould : BaseUnitTest() {

    private lateinit var service : PlaylistService
    @Mock
    private var playlists: List<PlaylistRaw> = mock(List::class.java) as List<PlaylistRaw>

    @Mock
    private val api : PlaylistAPI = mock(PlaylistAPI::class.java)


    @Test
    fun `if playlist is being fetched from api`() = runTest {
        mockSuccessCase()
       val job = launch {
            service.fetchPlaylists()
            verify(api,times(1)).fetchPlaylists()
        }

        job.cancel()
    }

    @Test
    fun `if result is being converted and emitted by flow`() = runTest{
        mockSuccessCase()

        assertEquals(Result.success(playlists),service.fetchPlaylists().first())

    }



    @Test
    fun `test if errors are being emitted properly`() = runTest{
        mockFailCase()

        assertEquals("Something went wrong",
            service.fetchPlaylists().first().exceptionOrNull()?.message)
    }


    private suspend fun mockSuccessCase() {
        whenever(api.fetchPlaylists()).thenReturn(playlists)

        service = PlaylistService(api)
    }
    private suspend fun mockFailCase() {
        whenever(api.fetchPlaylists()).thenThrow(RuntimeException("Damn BackEnd Developers"))
        service = PlaylistService(api)
    }

}