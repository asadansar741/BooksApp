package com.test.bookapp.util

import com.test.bookapp.data.model.BooksResponse

sealed class ApiState{
    class Success(val data: BooksResponse) : ApiState()
    class Failure(val message: Throwable) : ApiState()
    object Loading : ApiState()
    object Empty : ApiState()
}
