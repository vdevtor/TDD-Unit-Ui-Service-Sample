package com.vitorthemyth.tddapplication.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

abstract class BaseUnitTest {
    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

     @Before()
     fun setup(){
         MockitoAnnotations.openMocks(this)
     }
}