package com.test.bookapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.bookapp.ui.MainViewModel
import com.test.bookapp.ui.theme.BooksAppTheme

@Composable
fun SearchBookScreen(viewModel: MainViewModel?){

    Scaffold(topBar = { AppBar("Search A Book") }) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            SearchScreen(viewModel!!)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBookScreenPreview() {
    BooksAppTheme {
        SearchBookScreen(null)
    }
}

@Composable
fun SearchScreen(viewModel: MainViewModel){

    var bookName by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Search Criteria",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        TextField(
            value = bookName,
            onValueChange = { bookName = it },
            label = { Text(
                "(Mandatory) Enter Book Name",
                style = TextStyle(color = Color.Gray)
            ) },
            maxLines = 1,
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold, fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .testTag("BOOK_NAME_TAG")
        )
        Text(
            text = "Author",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        TextField(
            value = author,
            onValueChange = { author = it },
            label = { Text(
                "(Optional) Enter Book Author",
                style = TextStyle(color = Color.Gray)
            ) },
            maxLines = 1,
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold, fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .testTag("AUTHOR_TAG")
        )
    }

    Box (modifier = Modifier
        .padding(10.dp,50.dp),
        contentAlignment = Alignment.BottomCenter
    ){
        Button(
            onClick = {
                if (bookName.isEmpty()){
                    viewModel.showCustomDialog("Book Name Can't be Empty")
                }
                else{
                    viewModel.getBooks(bookName,author)
                }
            },
            modifier = Modifier.fillMaxWidth().testTag("SUBMIT_BUTTON_TAG"),
            content = {
                Text(
                    text = "Submit",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = buttonColors(Color.Red),
        )
    }

}