package com.example.turkcell_intro.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    val id: Int,
    val title: String,
    val description: String? = null,

    // Supabase'in otomatik gönderdiği kolonu yakalamak için bunu ekliyoruz
    @SerialName("created_at")
    val createdAt: String? = null
)