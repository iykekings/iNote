package com.example.inote.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inote.models.NotesFolder
import com.example.inote.ui.theme.INoteTheme

@Composable
fun FolderList(folders: List<NotesFolder>, onClick: (folderId: String) -> Unit = {}) {
    Surface(shape = RoundedCornerShape(10.dp), color = Color.Gray.copy(alpha = 0.2f)) {
        Column {
            folders.forEachIndexed { index, folder ->
                FolderItem(
                    folder = folder,
                    isLast = index == folders.size - 1,
                    onClick = onClick
                )
            }
        }
    }
}

@Preview
@Composable
fun FolderListPreview() {
    INoteTheme() {
        FolderList(
            listOf(
                NotesFolder(name = "iCloud", count = 4),
                NotesFolder(name = "Google Drive", count = 5),
                NotesFolder(name = "Personal", count = 7),
                NotesFolder(name = "Business", count = 3),
                NotesFolder(name = "Stand Up", count = 6),
                NotesFolder(name = "Miscellaneous", count = 4),
            )
        )
    }
}

@Preview
@Composable
fun FolderListPreviewDarkMode() {
    INoteTheme(darkTheme = true) {
        FolderList(
            listOf(
                NotesFolder(name = "iCloud", count = 4),
                NotesFolder(name = "Google Drive", count = 5),
                NotesFolder(name = "Personal", count = 7),
                NotesFolder(name = "Business", count = 3),
                NotesFolder(name = "Stand Up", count = 6),
                NotesFolder(name = "Miscellaneous", count = 4),
            )
        )
    }
}