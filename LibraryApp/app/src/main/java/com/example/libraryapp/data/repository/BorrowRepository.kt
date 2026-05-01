package com.example.libraryapp.data.repository

import com.example.libraryapp.data.model.Book
import com.example.libraryapp.data.model.BorrowRecord
import com.example.libraryapp.data.supabase.supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order
import java.time.LocalDate

class BorrowRepository {
    suspend fun borrowBook(book: Book): Result<Unit> = runCatching {
        val currentUser = supabase.auth.currentUserOrNull()?.id
            ?: throw Exception("Giriş yapılmadı!")

        val record = BorrowRecord(
            userId = currentUser,
            bookId = book.id,
            bookTitle = book.title, // Burası eksik olan kısımdı bro
            borrowDate = java.time.LocalDate.now().toString(),
            dueDate = java.time.LocalDate.now().plusDays(5).toString(),
            status = "active"
        )

        supabase.postgrest["borrow_records"].insert(record)

        supabase.postgrest["books"].update(
            mapOf("available_copies" to (book.availableCopies - 1))
        ) {
            filter { eq("id", book.id) }
        }
    }

    suspend fun returnBook(record: BorrowRecord): Result<Unit> = runCatching {
        supabase.postgrest["borrow_records"].update(
            mapOf("status" to "returned")
        ) {
            filter { eq("id", record.id) }
        }

        val book = supabase.postgrest["books"]
            .select { filter { eq("id", record.bookId) } }
            .decodeSingle<Book>()

        supabase.postgrest["books"].update(
            mapOf("available_copies" to (book.availableCopies + 1))
        ) {
            filter { eq("id", record.bookId) }
        }
    }

    private suspend fun getLatestAvailableCount(bookId: String): Int {
        val book = supabase.postgrest["books"]
            .select { filter { eq("id", bookId) } }
            .decodeSingle<Book>()

        return book.availableCopies
    }

    suspend fun getMyBorrowings(): Result<List<BorrowRecord>> = runCatching {
        val userId = supabase.auth.currentUserOrNull()?.id ?: ""
        supabase.postgrest["borrow_records"]
            .select {
                filter {
                    eq("user_id", userId)
                }
                order("borrow_date", order = io.github.jan.supabase.postgrest.query.Order.DESCENDING)
            }
            .decodeList<BorrowRecord>()
    }
}