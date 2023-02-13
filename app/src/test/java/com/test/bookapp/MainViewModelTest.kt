package com.test.bookapp

import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.whenever
import com.test.bookapp.data.model.BooksResponse
import com.test.bookapp.data.repository.BooksRepository
import com.test.bookapp.ui.MainViewModel
import com.test.bookapp.util.ApiState
import junit.framework.Assert.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

//@RunWith(MockitoJUnitRunner::class)
@RunWith(MockitoJUnitRunner.Silent::class)
class MainViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var mainViewModel: MainViewModel
    @Inject
    private lateinit var booksRepository: BooksRepository

    @Before
    fun setup() {
        booksRepository = mock(BooksRepository::class.java)
        mainViewModel = MainViewModel(booksRepository)
    }

    @Test
    fun getBooksShouldSetBooksResponseToLoadingWhenRepositoryCallStarts() {
        runBlocking {
            whenever(booksRepository.getBooksByNameOrAuthor("", "")).thenReturn(flowOf(BooksResponse(emptyList(),"",0)))
            assertEquals(ApiState.Empty, mainViewModel.booksResponse.value)
        }
    }

    @Test
    fun loadingSuccess() {
        val stream = MainViewModelTest::class.java.getResourceAsStream("/response.json")
        val jsonString = stream?.bufferedReader().use { it?.readText() }
        val expectedResponse = Gson().fromJson(jsonString, BooksResponse::class.java)
        runBlocking {
            whenever(booksRepository.getBooksByNameOrAuthor("harry", "")).thenReturn(flowOf(expectedResponse))

            mainViewModel.getBooks("harry", "")

            assertEquals(true, mainViewModel.cacheList.isNotEmpty())
        }
    }

    @Test
    fun getBooksShouldSetBooksResponseToSuccessAndCacheTheListWhenRepositoryCallIsSuccessful() {
        val stream = MainViewModelTest::class.java.getResourceAsStream("/response.json")
        val jsonString = stream?.bufferedReader().use { it?.readText() }
        val expectedResponse = Gson().fromJson(jsonString, BooksResponse::class.java)
        runBlocking {
            whenever(booksRepository.getBooksByNameOrAuthor("harry", "")).thenReturn(flowOf(BooksResponse(expectedResponse.items,"",10)))

            mainViewModel.getBooks("harry", "")

            assertEquals(expectedResponse.items, mainViewModel.cacheList)
        }
    }

    @Test
    fun getBooksShouldSetBooksResponseToFailureWhenRepositoryCallFails() {
        runBlocking {
            mainViewModel.getBooks("", "")

            assertEquals(true, mainViewModel.booksResponse.value is ApiState.Failure)
        }
    }

    @Test
    fun showCustomDialogShouldSetIsCustomDialogShownToTrueAndMessage() {
        val message = "Book Name Can't be Empty"
        mainViewModel.showCustomDialog(message)

        assertTrue(mainViewModel.isCustomDialogShown)
        assertEquals(message, mainViewModel.message)
    }

    @Test
    fun `dismissCustomDialog should set isCustomDialogShown to false`() {
        mainViewModel.isCustomDialogShown = true

        mainViewModel.dismissCustomDialog()

        assertFalse(mainViewModel.isCustomDialogShown)
    }

    @Test
    fun `showLoadingDialog should set isLoadingDialogShown to true`() {
        mainViewModel.showLoadingDialog()

        assertTrue(mainViewModel.isLoadingDialogShown)
    }

    @Test
    fun `dismissLoadingDialog should set isLoadingDialogShown to false`() {
        mainViewModel.isLoadingDialogShown = true

        mainViewModel.dismissLoadingDialog()

        assertFalse(mainViewModel.isLoadingDialogShown)
    }
}