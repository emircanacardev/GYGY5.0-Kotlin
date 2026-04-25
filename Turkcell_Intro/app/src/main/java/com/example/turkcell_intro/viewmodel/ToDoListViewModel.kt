package com.example.turkcell_intro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turkcell_intro.data.TodoRepository
import com.example.turkcell_intro.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ToDoListViewModel : ViewModel() {
    private val repository = TodoRepository()

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos.asStateFlow() // Noktalı virgülleri temizledik, Kotlin'de gerek yok :)

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        fetchTodos()
    }

    fun fetchTodos() {
        // İŞTE BÜYÜK SIR BURADA: Dispatchers.IO ile işlemi arka plana aldık!
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _error.value = null

            try {
                val result = repository.getTodos()
                _todos.value = result
            } catch (e: Exception){
                _error.value = e.message ?: "Bir hata oluştu."
                println("SUPABASE HATASI: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun delete(id: Int){
        // Silme işlemi de bir ağ isteği olduğu için onu da arka plana atıyoruz
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.delete(id)
            }catch (e: Exception) {
                println(e.message)
            }
        }
    }
}