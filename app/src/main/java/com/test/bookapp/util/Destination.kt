package com.test.bookapp.util

const val  SEARCH_BOOK_SCREEN  = "SEARCH_BOOK_SCREEN"
const val BOOKS_LIST_SCREEN = "BOOKS_LIST_SCREEN"
const val BOOK_DETAIL_SCREEN = "BOOK_DETAIL_SCREEN"

sealed class Destination(val rout:String){
    object SearchBook : Destination(SEARCH_BOOK_SCREEN)
    object BookList : Destination(BOOKS_LIST_SCREEN)
    object BookDetails : Destination("$BOOK_DETAIL_SCREEN/{id}"){
        fun createRoute(id:String) = "$BOOK_DETAIL_SCREEN/$id"
    }
}

