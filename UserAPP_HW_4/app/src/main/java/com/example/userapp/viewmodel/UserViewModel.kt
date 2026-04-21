package com.example.userapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userapp.data.model.User
import com.example.userapp.data.remote.RetrofitInstance
import com.example.userapp.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val repository = UserRepository(RetrofitInstance.api)

    private var allUsers = emptyList<User>()

    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Loading)
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            _uiState.value = UserUiState.Loading
            try {
                allUsers = repository.getUsers()
                filterUsers(_searchQuery.value)
            } catch (e: Exception) {
                _uiState.value = UserUiState.Error(e.message ?: "An unknown error occurred.")
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        filterUsers(query)
    }

    private fun filterUsers(query: String) {
        val filteredList = if (query.isBlank()) {
            allUsers
        } else {
            allUsers.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.email.contains(query, ignoreCase = true)
            }
        }
        _uiState.value = UserUiState.Success(filteredList)
    }

    fun getUserById(id: Int): User? {
        return allUsers.find { it.id == id }
    }
}