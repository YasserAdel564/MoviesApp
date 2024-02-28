package com.example.movies.presentation.ui.moviedetails

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ItemMovieGenre(
    label: String,
) {
    SuggestionChip(
        modifier = Modifier
            .padding(horizontal = 4.dp),
        onClick = {},
        label = {
            Text(text = label)
        },
        enabled = true,
        icon = null,
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = Color.Transparent
        ),
        border = SuggestionChipDefaults.suggestionChipBorder(true),
    )
}