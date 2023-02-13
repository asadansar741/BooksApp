package com.test.bookapp.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.test.bookapp.data.model.Item
import com.test.bookapp.ui.MainViewModel
import com.test.bookapp.util.ApiState

@Composable
fun BookList(viewModel: MainViewModel?){

    Scaffold(topBar = { AppBar("Book List") }) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            GetFilteredData(viewModel!!)
        }
    }
}

@Composable
fun AppBar(title: String) {
    TopAppBar(
        title = { Text(title) }
    )
}

@Composable
fun EachRow(item: Item,viewModel: MainViewModel) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfileCard(item){
                viewModel.navigateToBookDetailScreen(item.id)
            }
        }

    }
}

@Composable
fun ProfileCard(item: Item, clickAction: () -> Unit ) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable { clickAction.invoke() },
        backgroundColor = Color.White
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            item.apply {
                ProfilePicture(
                    if(volumeInfo?.imageLinks==null) "" else volumeInfo.imageLinks?.thumbnail!!
                )
                ProfileContent(
                    title = if (volumeInfo?.title!!.isNullOrEmpty()) "N/A" else volumeInfo.title ,
                    author = if (volumeInfo.authors.isNullOrEmpty()) "" else volumeInfo.authors.joinToString(","),
                    rating = volumeInfo.averageRating
                )
            }
        }
    }
}

@Composable
fun ProfilePicture(imageUrl:String) {
    Card(
        shape = RoundedCornerShape(2.dp),
        modifier = Modifier
            .padding(5.dp)
            .width(100.dp)
            .height(150.dp)
    )
    {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
    }
}

@Composable
fun ProfileContent(title:String, author:String, rating:Float ) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
        CompositionLocalProvider(LocalContentAlpha provides (ContentAlpha.medium)) {
            Text(
                text = author,
                style = MaterialTheme.typography.body2
            )
        }

        RatingBar(
            value = rating,
            config = RatingBarConfig()
                .style(RatingBarStyle.HighLighted),
            onValueChange = {
                //rating = it
            },
            onRatingChanged = {
                Log.d("TAG", "onRatingChanged: $it")
            }
        )
    }

}

@Composable
fun GetFilteredData(viewModel: MainViewModel){
    var filteredBook by remember { mutableStateOf(emptyList<Item>()) }

    when(val result = viewModel.booksResponse.value){
        is ApiState.Success -> {
            filteredBook = result.data.items
            LazyColumn{
                items(filteredBook){ item->
                    EachRow(item,viewModel)
                }
            }
        }
        is ApiState.Loading -> {

        }
        is ApiState.Failure -> {
            viewModel.showCustomDialog("Something went wrong")
        }
        else -> {}
    }
}

