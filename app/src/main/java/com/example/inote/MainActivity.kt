package com.example.inote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.inote.pages.FolderPage
import com.example.inote.pages.HomePage
import com.example.inote.pages.NotePage
import com.example.inote.repositories.MemoryNotesRepositoryImpl
import com.example.inote.ui.theme.INoteTheme
import com.example.inote.viewmodel.NotesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            INoteTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    MainAppNavigation()
                }
            }
        }
    }
}

@Composable
fun MainAppNavigation() {
    val navController = rememberNavController()
    val notesViewModel = NotesViewModel(MemoryNotesRepositoryImpl())
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomePage(
                navController,
                notesViewModel = notesViewModel
            )
        }
        composable(
            "folders/{folderId}",
            arguments = listOf(navArgument("folderId") { type = NavType.StringType })
        ) { backStackEntry ->
            FolderPage(
                navController = navController,
                folderId = backStackEntry.arguments?.getString("folderId"),
                notesViewModel = notesViewModel
            )
        }
        composable(
            "notes/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.StringType })
        ) { backStackEntry ->
            NotePage(
                navController = navController,
                noteId = backStackEntry.arguments?.getString("noteId"),
                notesViewModel = notesViewModel
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    INoteTheme {
        MainAppNavigation()
    }
}