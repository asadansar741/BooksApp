package com.test.bookapp.navigation


import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.bookapp.ui.MainViewModel
import com.test.bookapp.ui.screens.*
import com.test.bookapp.util.Destination
import com.test.bookapp.util.SEARCH_BOOK_SCREEN


@Composable
fun ScreenNavigation(viewModel: MainViewModel) {
    val context = LocalContext.current
    val navHostController = rememberNavController()
    viewModel.naveHostController = navHostController
    NavHost(navController = navHostController, startDestination = SEARCH_BOOK_SCREEN ){
        composable(Destination.SearchBook.rout){
            SearchBookScreen(viewModel)
        }
        composable(Destination.BookList.rout){
            BookList(viewModel)
        }
        composable(
            route =  Destination.BookDetails.rout
        ){ navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("id")
            if (id.isNullOrEmpty()){
                Toast.makeText(context, "Id required",Toast.LENGTH_SHORT).show()
            }
            else{
                BookDetailScreen(viewModel, id)
            }
        }
    }

    if (viewModel.isCustomDialogShown){
        CustomDialog(message = viewModel.message) {
            viewModel.dismissCustomDialog()
        }
    }
    if (viewModel.isLoadingDialogShown){
        LoadingDialog {
            viewModel.dismissLoadingDialog()
        }
    }
}