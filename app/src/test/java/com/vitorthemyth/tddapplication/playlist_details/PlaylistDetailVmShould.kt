package com.vitorthemyth.tddapplication.playlist_details


import com.vitorthemyth.tddapplication.data.network.PlaylistDetailsService
import com.vitorthemyth.tddapplication.domain.models.PlaylistDetails
import com.vitorthemyth.tddapplication.presentation.details.PlayListDetailViewModel
import com.vitorthemyth.tddapplication.utils.BaseUnitTest
import com.vitorthemyth.tddapplication.utils.getValueForTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
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
class PlaylistDetailVmShould : BaseUnitTest() {

    lateinit var viewModel: PlayListDetailViewModel

    @Mock
    private val service: PlaylistDetailsService = mock(PlaylistDetailsService::class.java)
    private val id = "1"

    @Mock
    private val playlistDetails = mock(PlaylistDetails::class.java)

    private val expected = Result.success(playlistDetails)

    private val exception = java.lang.RuntimeException("Something went wrong")
    private val error = Result.failure<PlaylistDetails>(exception)

    @Test
    fun `get details from playlist service`() = runTest {
        viewModel = PlayListDetailViewModel(service)

        viewModel.getPlaylistDetails(id)

        viewModel.details.getValueForTest()

        verify(service, times(1)).fetchDetails()
    }


    @Test
    fun `emit data from service`() = runTest {
        mockSuccessCase()
        assertEquals(expected,viewModel.details.getValueForTest())
    }

    @Test
    fun `emit error when failed`() = runTest {
        mockFailCase()
        assertEquals(error,viewModel.details.getValueForTest())
    }

    private suspend fun mockSuccessCase() {
        whenever(service.fetchDetails()).thenReturn(
            flow {
                emit(expected)
            }
        )

        viewModel = PlayListDetailViewModel(service)
        viewModel.getPlaylistDetails(id)

    }

    private suspend fun mockFailCase(){
        whenever(service.fetchDetails()).thenReturn(
            flow {
                emit(error)
            }
        )

        viewModel = PlayListDetailViewModel(service)
        viewModel.getPlaylistDetails(id)

    }
}