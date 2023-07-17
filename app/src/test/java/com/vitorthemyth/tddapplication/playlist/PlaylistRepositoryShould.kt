package com.vitorthemyth.tddapplication.playlist

import com.vitorthemyth.tddapplication.data.PlaylistRepositoryImp
import com.vitorthemyth.tddapplication.data.model.PlaylistRaw
import com.vitorthemyth.tddapplication.data.network.PlaylistService
import com.vitorthemyth.tddapplication.domain.PlaylistMapper
import com.vitorthemyth.tddapplication.domain.models.Playlist
import com.vitorthemyth.tddapplication.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever


@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class PlaylistRepositoryShould : BaseUnitTest() {

    @Mock
    private val service: PlaylistService = mock(PlaylistService::class.java)

    @Mock
    private var playlists: List<Playlist> = mock(List::class.java) as List<Playlist>

    @Mock
    private var playlistsRaw: List<PlaylistRaw> = mock(List::class.java) as List<PlaylistRaw>
    private val exception = java.lang.RuntimeException("Something went wrong")

    @Mock
    private val mapper: PlaylistMapper = mock(PlaylistMapper::class.java)

    @Test
    fun `get playlists from server`() = runTest {
        val repository = mockSuccessfulCase()
        repository.getPlayLists()
        verify(service, times(1)).fetchPlaylists()
    }

    @Test
    fun `is emitting the playlist from the service then mapping it`() = runTest {
        val repository = mockSuccessfulCase()
        assertEquals(playlists, repository.getPlayLists().first().getOrNull())
    }

    @Test
    fun `is emitting errors callback when failed`() = runTest {
        val repository = mockFailCase()
        repository.getPlayLists()
        Assert.assertEquals(exception, repository.getPlayLists().first().exceptionOrNull())
    }

    @Test
    fun `delegate  business logic to mapper`() = runTest {
        val repository = mockSuccessfulCase()

        repository.getPlayLists().first()
        verify(mapper, times(1)).invoke(playlistsRaw)
    }

    private suspend fun mockSuccessfulCase(): PlaylistRepositoryImp {
        whenever(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.success(playlistsRaw))
            }
        )
        whenever(mapper.invoke(playlistsRaw)).thenReturn(playlists)
        return PlaylistRepositoryImp(service, mapper)
    }


    private suspend fun mockFailCase(): PlaylistRepositoryImp {
        whenever(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.failure(exception = exception))
            }
        )
        return PlaylistRepositoryImp(service, mapper)
    }
}