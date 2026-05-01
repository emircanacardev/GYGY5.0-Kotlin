package com.example.libraryapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libraryapp.data.model.Book
import com.example.libraryapp.data.model.BorrowRecord
import com.example.libraryapp.data.repository.BookRepository
import com.example.libraryapp.data.repository.BorrowRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {
    private val repository = BookRepository()
    private val borrowRepository = BorrowRepository()

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _myBorrowings = MutableStateFlow<List<BorrowRecord>>(emptyList())
    val myBorrowings: StateFlow<List<BorrowRecord>> = _myBorrowings.asStateFlow()

    init {
        loadBooks()
    }
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val filteredBooks = combine(_books, _searchQuery) { books, query ->
        if (query.isBlank()) {
            books
        } else {
            books.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.author.contains(query, ignoreCase = true)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun loadBooks() {
        viewModelScope.launch {
            _isLoading.value = true
            repository
                .getAllBooks()
                .onSuccess { _books.value = it }
                .onFailure { _error.value = it.message }
            _isLoading.value = false
        }
    }

    fun borrowBook(book: Book) {
        viewModelScope.launch {
            _isLoading.value = true
            borrowRepository.borrowBook(book).onSuccess {
                loadBooks()
                _isLoading.value = false
            }.onFailure {
                _isLoading.value = false
                _error.value = it.message
            }
        }
    }

    fun fetchMyBorrowings() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = borrowRepository.getMyBorrowings()
            result.onSuccess { list ->
                _myBorrowings.value = list
                _isLoading.value = false
            }.onFailure {
                _isLoading.value = false
                // Hata logu bas bro
            }
        }
    }

    fun deleteBook(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            // Repository üzerinden veritabanından silmeyi dene
            repository.deleteBook(id).onSuccess {
                // Veritabanından silindi, şimdi lokal listeyi (UI) güncelle
                _books.value = _books.value.filter { it.id != id }
                _isLoading.value = false
                println("Başarıyla silindi: $id")
            }.onFailure {
                _isLoading.value = false
                _error.value = "Silme işlemi başarısız: ${it.message}"
                println("Silme hatası: ${it.message}")
            }
        }
    }

    fun returnBook(record: BorrowRecord) {
        viewModelScope.launch {
            _isLoading.value = true
            borrowRepository.returnBook(record).onSuccess {
                fetchMyBorrowings()
                loadBooks()

                _isLoading.value = false
                println("Teslim işlemi başarılı, stoklar ve liste güncellendi.")
            }.onFailure {
                _isLoading.value = false
                _error.value = "Hata: ${it.message}"
            }
        }
    }

    fun updateBook(id: String, updatedBook: Book) {
        viewModelScope.launch {
            repository.updateBook(id, updatedBook).onSuccess {
            }
        }
    }
}