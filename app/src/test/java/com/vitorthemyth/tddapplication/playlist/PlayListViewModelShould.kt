package com.vitorthemyth.tddapplication.playlist


import com.vitorthemyth.tddapplication.domain.PlayListRepository
import com.vitorthemyth.tddapplication.domain.models.Playlist
import com.vitorthemyth.tddapplication.presentation.PlayListViewModel
import com.vitorthemyth.tddapplication.utils.BaseUnitTest
import com.vitorthemyth.tddapplication.utils.getValueForTest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever


@RunWith(MockitoJUnitRunner::class)
class PlayListViewModelShould : BaseUnitTest() {

    @Mock
    private lateinit var repository: PlayListRepository
    @Mock
    private var playlists: List<Playlist> = mock(List::class.java) as List<Playlist>
    private val expected: Result<List<Playlist>> = Result.success(playlists)
    private val exception = java.lang.RuntimeException("Something went wrong")


    @Test
    fun `is playlist viewModel retrieved from repository`() = runBlocking {

        val viewModel = mockSuccessfulCase()

        val job = launch {
            viewModel.playlists.getValueForTest()
            verify(repository, times(1)).getPlayLists()
        }

        job.cancel()
    }

    @Test
    fun `is emitting playlists from the repository`()  = runBlocking{

        val viewModel = mockSuccessfulCase()

        val job = launch {
            viewModel.playlists.getValueForTest()
        }
        assertEquals(expected, viewModel.playlists.getValueForTest())
        job.cancel()
    }

    @Test
    fun `emit error callback when received an error`() = runBlocking{
        val viewModel = mockFailCase()

        val job  = launch{
            viewModel.playlists.getValueForTest()
        }

        assertEquals(exception,viewModel.playlists.getValueForTest()?.exceptionOrNull())

    }

    private fun mockFailCase(): PlayListViewModel {
        runBlocking {
            whenever(repository.getPlayLists()).thenReturn(
                flow {
                    emit(Result.failure(exception = exception))
                }
            )
        }

        return PlayListViewModel(repository)
    }

    private fun mockSuccessfulCase(): PlayListViewModel {
        runBlocking {
            whenever(repository.getPlayLists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }

        return PlayListViewModel(repository)
    }
}
