package com.example.inote.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.inote.composables.FolderList
import com.example.inote.composables.Search
import com.example.inote.repositories.MemoryNotesRepositoryImpl
import com.example.inote.ui.theme.INoteTheme
import com.example.inote.viewmodel.NotesViewModel

@Composable
fun HomePage(
    navController: NavController,
    notesViewModel: NotesViewModel = viewModel(),
) {
    val search = remember { mutableStateOf("") }
    val folders = notesViewModel.folders.value ?: emptyList()
    folders.forEach { it.count = notesViewModel.getFolderNotesCount(it.uuid) }
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Folders",
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.primary
        )
        Spacer(modifier = Modifier.height(10.dp))
        Search(search.value) { search.value = it }
        Spacer(modifier = Modifier.height(20.dp))
        FolderList(
            folders,
            onClick = { navController.navigate(route = "folders/${it}") })
    }
}


@Preview
@Composable
fun HomePreview() {
    INoteTheme {
        Surface {
            HomePage(
                rememberNavController(), notesViewModel = NotesViewModel(
                    MemoryNotesRepositoryImpl()
                )
            )
        }
    }
}

@Preview
@Composable
fun HomePreviewDark() {
    INoteTheme(darkTheme = true) {
        Surface {
            HomePage(
                rememberNavController(), notesViewModel = NotesViewModel(
                    MemoryNotesRepositoryImpl()
                )
            )
        }
    }
}