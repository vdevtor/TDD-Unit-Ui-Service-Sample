package com.vitorthemyth.tddapplication.playlist


import com.vitorthemyth.tddapplication.domain.PlayListRepository
import com.vitorthemyth.tddapplication.domain.models.Playlist
import com.vitorthemyth.tddapplication.presentation.PlayListViewModel
import com.vitorthemyth.tddapplication.utils.BaseUnitTest
import com.vitorthemyth.tddapplication.utils.captureValues
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
class PlayListViewModelShould : BaseUnitTest() {

    @Mock
    private lateinit var repository: PlayListRepository

    @Mock
    private var playlists: List<Playlist> = mock(List::class.java) as List<Playlist>
    private val expected: Result<List<Playlist>> = Result.success(playlists)
    private val exception = java.lang.RuntimeException("Something went wrong")


    @Test
    fun `is playlist viewModel retrieved from repository`() = runTest() {

        val viewModel = mockSuccessfulCase()
        viewModel.playlists.getValueForTest()
        verify(repository, times(1)).getPlayLists()

    }

    @Test
    fun `is emitting playlists from the repository`() = runTest {

        val viewModel = mockSuccessfulCase()

        viewModel.playlists.getValueForTest()
        assertEquals(expected, viewModel.playlists.getValueForTest())
    }

    @Test
    fun `emit error callback when received an error`() = runTest {
        val viewModel = mockFailCase()

        viewModel.playlists.getValueForTest()
        assertEquals(exception, viewModel.playlists.getValueForTest()?.exceptionOrNull())

    }

    @Test
    fun `show spinner while loading`() = runTest {
        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues {
            viewModel.playlists.getValueForTest()
            assertEquals(true, values[0])
        }

    }

    @Test
    fun `close hide spinner after fetch playlists`() = runTest {
        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues {
            viewModel.playlists.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    @Test
    fun `close hide spinner after some exception`() = runTest {
        val viewModel = mockFailCase()

        viewModel.loader.captureValues {
            viewModel.playlists.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    private fun mockFailCase(): PlayListViewModel {
        runTest {
            whenever(repository.getPlayLists()).thenReturn(
                flow {
                    emit(Result.failure(exception = exception))
                }
            )
        }

        return PlayListViewModel(repository)
    }

    private fun mockSuccessfulCase(): PlayListViewModel {
        runTest {
            whenever(repository.getPlayLists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }

        return PlayListViewModel(repository)
    }
}
