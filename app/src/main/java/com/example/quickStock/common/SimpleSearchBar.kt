package com.example.quickStock.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: List<String>,
    searchResults: List<String>,
    modifier: Modifier = Modifier
) {
    // Controla el estado de expansión del SearchBar
    val expanded = rememberSaveable { mutableStateOf(false) }

    Box(
        modifier
            .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = (0).dp, y = (-16).dp)
                .semantics { traversalIndex = 0f },
            query = query,
            onQueryChange = { onQueryChange(it) },
            onSearch = {
                // Filtra los resultados basados en el query
                val filteredResults = onSearch.filter { it.contains(query, ignoreCase = true) }
                // Actualiza los resultados de búsqueda
                onQueryChange(filteredResults.joinToString(", "))
                expanded.value = false
            },
            active = expanded.value,
            onActiveChange = { expanded.value = it },
            placeholder = { Text("Search") }
        ) {
            // Muestra los resultados de búsqueda en una columna desplazable
            Column(Modifier.verticalScroll(rememberScrollState())) {
                searchResults.forEach { result ->
                    ListItem(
                        headlineContent = { Text(result) },
                        modifier = Modifier
                            .clickable {
                                onQueryChange(result)
                                expanded.value = false
                            }
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}