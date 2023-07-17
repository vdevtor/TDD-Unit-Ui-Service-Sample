package com.vitorthemyth.tddapplication.playlist_details

import com.vitorthemyth.tddapplication.data.network.PlaylistAPI
import com.vitorthemyth.tddapplication.data.network.PlaylistDetailsService
import com.vitorthemyth.tddapplication.domain.models.PlaylistDetails
import com.vitorthemyth.tddapplication.utils.BaseUnitTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class PlaylistDetailServiceShould : BaseUnitTest() {


    @Mock
    private val api: PlaylistAPI = mock(PlaylistAPI::class.java)

    private lateinit var service : PlaylistDetailsService

    private val exception = RuntimeException("Damn back end developers")

    @Mock
    private var playlistDetails: PlaylistDetails = mock(PlaylistDetails::class.java)



    @Test
    fun `fetch playlist detail from api`() = runTest{
        mockSuccessCase()
        val job = launch {
            service.fetchDetails().single()
            verify(api, times(1)).fetchSinglePlayList()
        }

        job.cancel()
    }

    @Test
    fun `convert and emit data from api`() = runBlocking{
        mockSuccessCase()
        assertEquals(Result.success(playlistDetails), service.fetchDetails().first())
    }

    @Test
    fun `emit error when request failed`() = runBlocking{
        mockFailCase()
       assertEquals(
            "Something went wrong",
            service.fetchDetails().first().exceptionOrNull()?.message
        )
    }


    private fun mockSuccessCase() = runTest{
        whenever(api.fetchSinglePlayList()).thenReturn(playlistDetails)
        service = PlaylistDetailsService(api)
    }

    private fun mockFailCase() = runTest{
        whenever(api.fetchSinglePlayList()).thenThrow(exception)
        service = PlaylistDetailsService(api)
    }
}