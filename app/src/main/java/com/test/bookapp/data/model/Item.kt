package com.test.bookapp.data.model

data class Item(
    val accessInfo: AccessInfo? = null,
    val etag: String = "",
    val id: String = "",
    val kind: String = "",
    val saleInfo: SaleInfo? = null,
    val searchInfo: SearchInfo? = null,
    val selfLink: String = "",
    val volumeInfo: VolumeInfo? = null
)