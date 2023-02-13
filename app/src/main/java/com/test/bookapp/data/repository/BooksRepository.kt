package com.test.bookapp.data.repository

import com.test.bookapp.data.model.BooksResponse
import com.test.bookapp.data.service.BooksApiService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class BooksRepository @Inject constructor(private val apiService: BooksApiService){

    fun getBooksByNameOrAuthor(
        bookName: String,
        author: String
    ): Flow<BooksResponse> = flow {
        emit(apiService.getBooksByNameOrAuthor(bookName,author))
    }.flowOn(IO)
}