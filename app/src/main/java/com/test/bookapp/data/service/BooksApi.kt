package com.test.bookapp.data.service


import com.test.bookapp.data.model.BooksResponse
import com.test.bookapp.util.AppConstant.Companion.GET_BOOK_LIST_BY_AUTHOR_NAME
import retrofit2.http.*


interface BooksApi {

    @GET(GET_BOOK_LIST_BY_AUTHOR_NAME)
    suspend fun getBooksByNameOrAuthor(
        @Query("q") q: String,
        @Query("inauthor") inauthor: String,
    ): BooksResponse





}