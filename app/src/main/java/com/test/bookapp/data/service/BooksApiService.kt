package com.test.bookapp.data.service

import com.test.bookapp.data.model.BooksResponse
import javax.inject.Inject

class BooksApiService @Inject constructor(private val api: BooksApi){

    suspend fun getBooksByNameOrAuthor(
        bookName: String,
        author: String
    ): BooksResponse = api.getBooksByNameOrAuthor(bookName,author)

}