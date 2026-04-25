package com.example.turkcell_intro.data

import com.example.turkcell_intro.di.SupabaseClient
import com.example.turkcell_intro.model.Todo
import io.github.jan.supabase.postgrest.postgrest

class TodoRepository {
    private val db = SupabaseClient.supabaseClient.postgrest
    suspend fun getTodos(): List<Todo> {
        return db.from("todos").select().decodeList()
    }

    suspend fun addToDo(toDo: Todo) {
        db.from("todos").insert(toDo);
    }

    suspend fun delete(id: Int)
    {
        db.from("todos").delete{
            filter { eq("id",id) }
        }
    }
}