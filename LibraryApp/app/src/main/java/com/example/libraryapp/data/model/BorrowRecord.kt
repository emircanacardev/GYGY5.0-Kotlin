package com.example.libraryapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BorrowRecord(
    val id: String = "",
    @SerialName("user_id") val userId: String,
    @SerialName("book_id") val bookId: String,
    @SerialName("book_title") val bookTitle: String? = "Bilinmeyen Kitap",
    @SerialName("borrow_date") val borrowDate: String,
    @SerialName("due_date") val dueDate: String,
    @SerialName("status") val status: String = "active"
)