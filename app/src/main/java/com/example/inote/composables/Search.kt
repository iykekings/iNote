package com.example.inote.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inote.ui.theme.INoteTheme

@Composable
fun Search(value: String,  onValueChange: (String) -> Unit) {
        OutlinedTextField(
            value = value,
            placeholder = { Text("search") },
            onValueChange = onValueChange,
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "search") },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(color = Color.Gray.copy(alpha = 0.2f)),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Gray,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
            )
        )
}

@Preview
@Composable
fun SearchPreview() {
    INoteTheme {
        Search("", {})
    }
}

@Preview
@Composable
fun SearchPreviewDark() {
    INoteTheme(darkTheme = true) {
        Search("", {})
    }
}