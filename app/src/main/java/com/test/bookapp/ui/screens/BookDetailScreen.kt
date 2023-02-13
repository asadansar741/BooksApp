package com.test.bookapp.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.test.bookapp.data.model.Item
import com.test.bookapp.ui.MainViewModel
import com.test.bookapp.ui.theme.BooksAppTheme


@Composable
fun BookDetailScreen(viewModel: MainViewModel?, id: String?){

    Scaffold(topBar = { AppBar("Book Details") }) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            BookDetail(viewModel!!, id!!)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookDetailScreenPreview() {
    BooksAppTheme {
        BookDetailScreen(null, null)
    }
}

@Composable
fun BookDetail(
    viewModel: MainViewModel,
    id: String
){

    var selectedItem by remember { mutableStateOf<Item?>(null) }
    val item = viewModel.cacheList.firstOrNull { item -> item.id == id }
    selectedItem = item

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 30.dp, 10.dp, 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = rememberAsyncImagePainter(selectedItem?.volumeInfo?.imageLinks?.thumbnail),
            contentDescription = null,
            modifier = Modifier.size(150.dp,200.dp)
        )
        Text(
            text = if (selectedItem?.volumeInfo?.title.isNullOrEmpty()) "" else selectedItem?.volumeInfo?.title!!,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Text(
            text = if (selectedItem?.volumeInfo?.subtitle.isNullOrEmpty()) "" else selectedItem?.volumeInfo?.subtitle!!,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp,10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = if (selectedItem?.volumeInfo?.authors.isNullOrEmpty()) "" else "By ${selectedItem?.volumeInfo?.authors?.joinToString(",")!!}",
                style = MaterialTheme.typography.subtitle1
            )
            Row (
                modifier = Modifier.padding(0.dp,10.dp,0.dp,20.dp)
            ){
                RatingBar(
                    value = selectedItem?.volumeInfo?.averageRating!!,
                    config = RatingBarConfig()
                        .style(RatingBarStyle.HighLighted),
                    onValueChange = {
                        //rating = it
                    },
                    onRatingChanged = {
                        Log.d("TAG", "onRatingChanged: $it")
                    }
                )

                Text(
                    text = "(${selectedItem?.volumeInfo?.ratingsCount})",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(10.dp,0.dp)
                )
            }
            Text(
                text = if (selectedItem?.volumeInfo?.description.isNullOrEmpty()) "" else selectedItem?.volumeInfo?.description!!,
                style = MaterialTheme.typography.body1,
            )
        }
    }


}