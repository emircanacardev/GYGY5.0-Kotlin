package com.example.turkcell_intro.data

import com.example.turkcell_intro.model.Todo
import retrofit2.http.GET

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): List<Todo>
    //fun getTodos(): List<Todo> // Fonksiyon bitene kadar thread'i kitler.
    // UI 'da kilitlenme yaşamamak için
}