package com.example.libraryapp.data.repository

import com.example.libraryapp.data.model.Book
import com.example.libraryapp.data.supabase.supabase
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order // Bunu eklemeyi unutma bro

class BookRepository {
    // Tüm kitapları getirirken isme göre (A-Z) sabitledik
    suspend fun getAllBooks(): Result<List<Book>> = runCatching {
        supabase.postgrest["books"]
            .select {
                order("title", order = Order.ASCENDING)
            }
            .decodeList<Book>()
    }

    suspend fun getBookById(id: String): Result<Book> = runCatching {
        supabase.postgrest["books"]
            .select { filter { eq("id", id) } }
            .decodeSingle<Book>()
    }

    suspend fun addBook(book: Book): Result<Unit> = runCatching {
        supabase.postgrest["books"].insert(book)
    }

    suspend fun updateBook(id: String, book: Book): Result<Unit> = runCatching {
        supabase.postgrest["books"].update(book) {
            filter { eq("id", id) }
        }
    }

    suspend fun deleteBook(id: String): Result<Unit> = runCatching {
        supabase.postgrest["books"].delete {
            filter { eq("id", id) }
        }
    }

    // Arama sonuçlarını da sabit bir sırada getiriyoruz
    suspend fun searchBooks(query: String): Result<List<Book>> = runCatching {
        supabase.postgrest["books"].select {
            filter {
                ilike("title", "%$query%")
            }
            order("title", order = Order.ASCENDING)
        }.decodeList<Book>()
    }
}