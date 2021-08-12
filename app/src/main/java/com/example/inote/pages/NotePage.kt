package com.example.inote.pages

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.inote.composables.Note
import com.example.inote.repositories.MemoryNotesRepositoryImpl
import com.example.inote.repositories.TESTNOTE
import com.example.inote.ui.theme.INoteTheme
import com.example.inote.viewmodel.NotesViewModel
import com.example.inote.models.Note as NoteModel

@Composable
fun NotePage(
    navController: NavController,
    noteId: String?,
    isNew: Boolean = false,
    notesViewModel: NotesViewModel = viewModel()
) {
    val notes = notesViewModel.notes.observeAsState()
    val folders = notesViewModel.folders.observeAsState()
    val note = notes.value?.find { it.uuid.toString() == noteId }
    val folder = folders.value?.find { it.uuid == note?.folderId }
    Scaffold(
        topBar = {
            NoteTopBar(
                folderName = folder?.name ?: "Unnamed",
                onBackClick = { navController.popBackStack() })
        },
        bottomBar = { NoteBottomBar() }
    ) {
        Column(
            Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (!isNew) {
                note?.let {
                    Note(it)
                }
            } else {
                folder?.let {
                    val newNote = NoteModel(content = "", folderId = folder.uuid)
                    notesViewModel.createNote(newNote)
                    Note(newNote)
                }
            }
        }
    }
}

@Composable
fun NoteBottomBar() {
    BottomAppBar(
        backgroundColor = Color.Transparent,
        contentColor = MaterialTheme.colors.secondary,
        elevation = 0.dp,
    ) {
        Surface {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Outlined.CheckCircle,
                    contentDescription = "Add Bullet Points",
                    tint = MaterialTheme.colors.secondary
                )
                Icon(
                    Icons.Outlined.LinkedCamera,
                    contentDescription = "Add Images",
                    tint = MaterialTheme.colors.secondary
                )
                Icon(
                    Icons.Outlined.FormatPaint,
                    contentDescription = "Format",
                    tint = MaterialTheme.colors.secondary
                )
                Icon(
                    Icons.Outlined.Edit,
                    contentDescription = "Edit",
                    tint = MaterialTheme.colors.secondary
                )
            }
        }
    }
}

@Composable
fun NoteTopBar(folderName: String, onBackClick: () -> Unit = {}, onMenuClick: () -> Unit = {}) {
    val color = MaterialTheme.colors.secondary
    TopAppBar(
        backgroundColor = Color.Transparent,
        contentColor = color,
        elevation = 0.dp,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
        ) {
            Row(Modifier.clickable { onBackClick() }) {
                Icon(Icons.Outlined.NavigateBefore, contentDescription = "back")
                Text(folderName)
            }
            Icon(
                Icons.Default.MoreHoriz,
                contentDescription = "notes menu",
                modifier = Modifier
                    .border(width = 1.dp, color = color, shape = CircleShape)
                    .clickable { onMenuClick() }
            )
        }
    }
}


@Preview
@Composable
fun NotePagePreview() {
    INoteTheme(darkTheme = false) {
        Surface {
            NotePage(
                navController = rememberNavController(),
                noteId = TESTNOTE.uuid.toString(),
                notesViewModel = NotesViewModel(
                    MemoryNotesRepositoryImpl()
                )
            )
        }
    }

}

@Preview
@Composable
fun NotePagePreviewDark() {
    INoteTheme(darkTheme = true) {
        Surface {
            NotePage(
                navController = rememberNavController(),
                noteId = TESTNOTE.uuid.toString(),
                notesViewModel = NotesViewModel(
                    MemoryNotesRepositoryImpl()
                )
            )
        }
    }

}