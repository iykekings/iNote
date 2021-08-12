package com.example.inote.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inote.models.Note
import com.example.inote.ui.theme.INoteTheme
import java.util.*

@Composable
fun NoteList(notes: List<Note>, folderName: String = "Notes", onClick: (noteId: String) -> Unit = {}) {
    Surface(shape = RoundedCornerShape(10.dp), color = Color.Gray.copy(alpha = 0.2f)) {
        Column(Modifier.padding(start = 25.dp)) {
            notes.forEachIndexed { index, note ->
                NoteItem(
                    note,
                    folderName,
                    isLast = index == notes.size - 1,
                    onClick = onClick
                )
            }
        }
    }
}

@Preview
@Composable
fun NoteListPreview() {
    INoteTheme(darkTheme = true) {
        NoteList(
            notes = listOf(
                Note(
                    content = "Aleksandra❤️\nTo the Queen of the console:\n" +
                            "\n" +
                            "Thank you for being an amazing person.\n" +
                            "Thank you for always making sure that we are fine.\n" +
                            "Thank you for your openness and carrying everyone along. \n" +
                            "You made this work a lot easier and fun. For always looking and finding ways to help us improve, thank you.  \n" +
                            "I wish you all the best my friend ❤️", folderId = UUID.randomUUID()
                ),
                Note(
                    content = "Aleksandra❤️\nTo the Queen of the console:\n" +
                            "\n" +
                            "Thank you for being an amazing person.\n" +
                            "Thank you for always making sure that we are fine.\n" +
                            "Thank you for your openness and carrying everyone along. \n" +
                            "You made this work a lot easier and fun. For always looking and finding ways to help us improve, thank you.  \n" +
                            "I wish you all the best my friend ❤️", folderId = UUID.randomUUID()
                ),

                Note(
                    content = "Aleksandra❤️\nTo the Queen of the console:\n" +
                            "\n" +
                            "Thank you for being an amazing person.\n" +
                            "Thank you for always making sure that we are fine.\n" +
                            "Thank you for your openness and carrying everyone along. \n" +
                            "You made this work a lot easier and fun. For always looking and finding ways to help us improve, thank you.  \n" +
                            "I wish you all the best my friend ❤️", folderId = UUID.randomUUID()
                ),
                Note(
                    content = "Aleksandra❤️\nTo the Queen of the console:\n" +
                            "\n" +
                            "Thank you for being an amazing person.\n" +
                            "Thank you for always making sure that we are fine.\n" +
                            "Thank you for your openness and carrying everyone along. \n" +
                            "You made this work a lot easier and fun. For always looking and finding ways to help us improve, thank you.  \n" +
                            "I wish you all the best my friend ❤️", folderId = UUID.randomUUID()
                ),
                Note(
                    content = "Aleksandra❤️\nTo the Queen of the console:\n" +
                            "\n" +
                            "Thank you for being an amazing person.\n" +
                            "Thank you for always making sure that we are fine.\n" +
                            "Thank you for your openness and carrying everyone along. \n" +
                            "You made this work a lot easier and fun. For always looking and finding ways to help us improve, thank you.  \n" +
                            "I wish you all the best my friend ❤️", folderId = UUID.randomUUID()
                ),
                Note(
                    content = "Aleksandra❤️\nTo the Queen of the console:\n" +
                            "\n" +
                            "Thank you for being an amazing person.\n" +
                            "Thank you for always making sure that we are fine.\n" +
                            "Thank you for your openness and carrying everyone along. \n" +
                            "You made this work a lot easier and fun. For always looking and finding ways to help us improve, thank you.  \n" +
                            "I wish you all the best my friend ❤️", folderId = UUID.randomUUID()
                ),
                Note(
                    content = "Aleksandra❤️\nTo the Queen of the console:\n" +
                            "\n" +
                            "Thank you for being an amazing person.\n" +
                            "Thank you for always making sure that we are fine.\n" +
                            "Thank you for your openness and carrying everyone along. \n" +
                            "You made this work a lot easier and fun. For always looking and finding ways to help us improve, thank you.  \n" +
                            "I wish you all the best my friend ❤️", folderId = UUID.randomUUID()
                ),
                Note(
                    content = "Aleksandra❤️\nTo the Queen of the console:\n" +
                            "\n" +
                            "Thank you for being an amazing person.\n" +
                            "Thank you for always making sure that we are fine.\n" +
                            "Thank you for your openness and carrying everyone along. \n" +
                            "You made this work a lot easier and fun. For always looking and finding ways to help us improve, thank you.  \n" +
                            "I wish you all the best my friend ❤️", folderId = UUID.randomUUID()
                ),
                Note(
                    content = "Aleksandra❤️\nTo the Queen of the console:\n" +
                            "\n" +
                            "Thank you for being an amazing person.\n" +
                            "Thank you for always making sure that we are fine.\n" +
                            "Thank you for your openness and carrying everyone along. \n" +
                            "You made this work a lot easier and fun. For always looking and finding ways to help us improve, thank you.  \n" +
                            "I wish you all the best my friend ❤️", folderId = UUID.randomUUID()
                ),
                Note(
                    content = "Aleksandra❤️\nTo the Queen of the console:\n" +
                            "\n" +
                            "Thank you for being an amazing person.\n" +
                            "Thank you for always making sure that we are fine.\n" +
                            "Thank you for your openness and carrying everyone along. \n" +
                            "You made this work a lot easier and fun. For always looking and finding ways to help us improve, thank you.  \n" +
                            "I wish you all the best my friend ❤️", folderId = UUID.randomUUID()
                ),
            )
        )
    }
}