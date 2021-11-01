package com.joshnark.data_layer.repositories
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.AsyncPagingDataDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.joshnark.data_layer.CoroutineTestRule
import com.joshnark.data_layer.MockData
import com.joshnark.data_layer.test_doubles.GifsDataSourceTestDouble
import com.joshnark.domain_layer.getOrAwaitValue
import com.joshnark.domain_layer.models.GenericResult
import com.joshnark.domain_layer.models.Gif
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi()
@RunWith(JUnit4::class)
class GifsRepositoryImplTest {

    private val dispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(dispatcher)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    /** text doubles */

    private val gifsDataSource = GifsDataSourceTestDouble()

    /** system under test */

    private val sut = GifsRepositoryImpl(gifsDataSource)

    /** test methods */

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun addGifTofavorites_gifSaved_changedGifReturned() = testScope.runBlockingTest {
        val exampleGif = MockData.mockGifList.first()
        val gif = sut.addGifToFavorites(exampleGif)
        val gifData = (gif as GenericResult.Success).data

        //first test to verify that the data returned by the method is correct
        assertThat(gifData.id, CoreMatchers.`is`(exampleGif.id))
        assertThat(gifData.isLiked, CoreMatchers.`is`(!exampleGif.isLiked))
    }
}