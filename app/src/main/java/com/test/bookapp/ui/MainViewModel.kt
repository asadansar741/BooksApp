package com.test.bookapp.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.test.bookapp.data.model.Item
import com.test.bookapp.data.repository.BooksRepository
import com.test.bookapp.util.ApiState
import com.test.bookapp.util.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(private val repository: BooksRepository): ViewModel() {

    val booksResponse : MutableState<ApiState> = mutableStateOf(ApiState.Empty)

    var cacheList = emptyList<Item>()

    var naveHostController: NavHostController? = null

    var isCustomDialogShown by mutableStateOf(false)
    var isLoadingDialogShown by mutableStateOf(false)
    var message by mutableStateOf("")
    private set

    fun getBooks(
        name:String,
        author:String
    ) = viewModelScope.launch {
        repository.getBooksByNameOrAuthor(name,author)
            .onStart {
                booksResponse.value = ApiState.Loading
                showLoadingDialog()
            }.catch {
                booksResponse.value = ApiState.Failure(it)
                dismissLoadingDialog()
            }.collect{
                booksResponse.value = ApiState.Success(it)
                cacheList = it.items
                dismissLoadingDialog()
                navigateToBookListScreen()
            }
    }

    private fun navigateToBookListScreen() {
        naveHostController?.navigate(Destination.BookList.rout)
    }
    fun navigateToBookDetailScreen(id:String) {
        naveHostController?.navigate(Destination.BookDetails.createRoute(id))
    }


    fun showCustomDialog(message:String){
        this.message = message
        isCustomDialogShown = true
    }
    fun dismissCustomDialog(){
        isCustomDialogShown = false
    }

    fun showLoadingDialog(){
        isLoadingDialogShown = true
    }
    fun dismissLoadingDialog(){
        isLoadingDialogShown = false
    }
}