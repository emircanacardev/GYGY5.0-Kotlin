package com.example.libraryapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History // Geçmiş/Kiralama ikonu için
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.libraryapp.ui.components.BookCard
import com.example.libraryapp.ui.viewmodel.AuthViewModel
import com.example.libraryapp.ui.viewmodel.BookViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    authViewModel: AuthViewModel,
    bookViewModel: BookViewModel,
    onNavigateToBorrowings: () -> Unit // Navigasyon için yeni parametre
) {
    val books by bookViewModel.filteredBooks.collectAsState()
    val isLoading by bookViewModel.isLoading.collectAsState()
    val searchQuery by bookViewModel.searchQuery.collectAsState()
    val profile by authViewModel.profile.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        // --- ÜST BAŞLIK, SELAMLAMA VE BUTON ---
        // ... diğer importlar aynı kalacak

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Hoş Geldin,",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = profile?.fullName ?: "Öğrenci",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // İkon yerine metinli şık bir buton (Ödünç Alınanlar)
            Button(
                onClick = onNavigateToBorrowings,
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Icon(
                    imageVector = Icons.Default.History,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Ödünç Alınanlar",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { bookViewModel.onSearchQueryChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp),
            placeholder = { Text("Kitap veya yazar ara...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.LightGray,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )

        Box(modifier = Modifier.fillMaxSize()) {
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                books.isEmpty() -> {
                    Text(
                        text = if (searchQuery.isEmpty()) "Henüz kitap yok." else "Aramanızla eşleşen kitap bulunamadı.",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Gray
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(24.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(books, key = { it.id }) { book ->
                            BookCard(
                                book = book,
                                onClick = { /* Detay sayfası ilerde */ },
                                onDeleteClick = { bookViewModel.deleteBook(book.id) },
                                onBorrowClick = {
                                    bookViewModel.borrowBook(book)
                                })
                        }
                    }
                }
            }
        }
    }
}