package com.test.bookapp.data.model

data class VolumeInfo(
    val allowAnonLogging: Boolean = false,
    val authors: List<String>? = null,
    val averageRating: Float = 0.0f,
    val canonicalVolumeLink: String = "",
    val categories: List<String> = emptyList(),
    val contentVersion: String = "",
    val description: String = "",
    val imageLinks: ImageLinks? = null,
    val industryIdentifiers: List<IndustryIdentifier> = emptyList(),
    val infoLink: String = "",
    val language: String = "",
    val maturityRating: String = "",
    val pageCount: Int = 0,
    val panelizationSummary: PanelizationSummary? = null,
    val previewLink: String = "",
    val printType: String = "",
    val publishedDate: String = "",
    val publisher: String = "",
    val ratingsCount: Int = 0,
    val readingModes: ReadingModes? = null,
    val subtitle: String = "",
    val title: String? = ""
)