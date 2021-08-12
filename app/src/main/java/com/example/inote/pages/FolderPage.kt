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
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.NavigateNext
import androidx.compose.material.icons.outlined.NoteAdd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.inote.composables.NoteList
import com.example.inote.composables.Search
import com.example.inote.repositories.MemoryNotesRepositoryImpl
import com.example.inote.repositories.TESTFOLDER
import com.example.inote.ui.theme.INoteTheme
import com.example.inote.viewmodel.NotesViewModel

@Composable
fun FolderPage(
    navController: NavController,
    folderId: String?,
    notesViewModel: NotesViewModel = viewModel()
) {
    val currentFolder = notesViewModel.folders.value?.find { it.uuid.toString() == folderId }
    val showPinned = remember { mutableStateOf(true) }
    val notes =
        notesViewModel.notes.value?.filter { it.folderId.toString() == folderId } ?: emptyList()
    val pinnedNotes = notes.filter { it.pinned }
    val nonPinnedNotes = notes.filter { !it.pinned }
    val search = remember { mutableStateOf("") }
    val folderClick = {noteId: String -> navController.navigate("notes/${noteId}")}

    Scaffold(
        topBar = { FolderTopBar(onBackClick = { navController.popBackStack() }) },
        bottomBar = { FolderBottomBar(notes.size) }
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(bottom = 60.dp)
        ) {
            Text(
                text = currentFolder?.name ?: "Unnamed",
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(10.dp))
            Search(search.value) { search.value = it }
            Spacer(modifier = Modifier.height(40.dp))
            if(notes.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Pinned",
                        color = MaterialTheme.colors.primary,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.weight(1f))
                    Icon(
                        if (showPinned.value) Icons.Outlined.ExpandMore else Icons.Outlined.NavigateNext,
                        contentDescription = "notes menu",
                        tint = MaterialTheme.colors.secondary,
                        modifier = Modifier
                            .clickable { showPinned.value = !showPinned.value }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                if (showPinned.value) {
                    NoteList(pinnedNotes, currentFolder?.name ?: "Unnamed", onClick = folderClick)
                    Spacer(modifier = Modifier.height(20.dp))
                }
                Text(
                    text = "Notes",
                    color = MaterialTheme.colors.primary,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(20.dp))
                NoteList(nonPinnedNotes, currentFolder?.name ?: "Unnamed", onClick = folderClick)
            }
        }
    }

}

@Composable
fun FolderBottomBar(length: Int) {
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
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.weight(0.5f))
                Text(
                    "$length Notes",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.primary
                )
                Spacer(Modifier.weight(0.5f))
                Icon(
                    Icons.Outlined.NoteAdd,
                    contentDescription = "New Note",
                    tint = MaterialTheme.colors.secondary
                )
            }
        }
    }
}

@Composable
fun FolderTopBar(onBackClick: () -> Unit = {}, onMenuClick: () -> Unit = {}) {
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
                Icon(Icons.Outlined.ArrowBackIos, contentDescription = "back")
                Text("Folders")
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
fun FolderPreview() {
    INoteTheme(darkTheme = false) {
        Surface {
            FolderPage(
                navController = rememberNavController(),
                folderId = TESTFOLDER.uuid.toString(),
                notesViewModel = NotesViewModel(
                    MemoryNotesRepositoryImpl()
                )
            )
        }
    }
}

@Preview
@Composable
fun FolderPreviewDark() {
    INoteTheme(darkTheme = true) {
        Surface {
            FolderPage(
                navController = rememberNavController(),
                folderId = TESTFOLDER.uuid.toString(),
                notesViewModel = NotesViewModel(
                    MemoryNotesRepositoryImpl()
                )
            )
        }
    }
}