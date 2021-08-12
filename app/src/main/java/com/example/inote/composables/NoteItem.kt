package com.example.inote.composables


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inote.models.Note
import com.example.inote.ui.theme.INoteTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun NoteItem(note: Note, folderName: String, onClick: (noteId: String) -> Unit = {}, isLast: Boolean = false, inGrid: Boolean = false, showFolderName: Boolean = false) {
    Column(Modifier.clickable { onClick(note.uuid.toString()) }) {
        if (!inGrid) {
            Column {
                Column(Modifier.padding(vertical = 10.dp)) {
                    Text(
                        note.title,
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                    Text(
                        "${SimpleDateFormat("dd/MM/yyyy").format(note.createdAt)} ${note.body}",
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Clip
                    )
                    if (showFolderName) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Outlined.Folder,
                                contentDescription = "folder",
                                //                    Modifier.size(16.dp),
                                tint = Color.Gray
                            )
                            Spacer(Modifier.width(5.dp))
                            Text(folderName, color = Color.Gray)
                        }
                    }
                }
                if (!isLast) {
                    HR(1f)
                }
            }
        } else {
            Note(note = note)
        }
    }
}

@Preview
@Composable
fun NoteItemPreview() {
    INoteTheme() {
    NoteItem(note = Note(content = "Aleksandra❤️\nTo the Queen of the console:\n" +
            "\n" +
            "Thank you for being an amazing person.\n" +
            "Thank you for always making sure that we are fine.\n" +
            "Thank you for your openness and carrying everyone along. \n" +
            "You made this work a lot easier and fun. For always looking and finding ways to help us improve, thank you.  \n" +
            "I wish you all the best my friend ❤️", folderId = UUID.randomUUID()), folderName = "Notes")
    }
}

@Preview
@Composable
fun NoteItemPreviewDark() {
    INoteTheme(darkTheme = true) {
        NoteItem(note = Note(content = "Aleksandra❤️\nTo the Queen of the console:\n" +
                "\n" +
                "Thank you for being an amazing person.\n" +
                "Thank you for always making sure that we are fine.\n" +
                "Thank you for your openness and carrying everyone along. \n" +
                "You made this work a lot easier and fun. For always looking and finding ways to help us improve, thank you.  \n" +
                "I wish you all the best my friend ❤️", folderId = UUID.randomUUID()), folderName = "Business")
    }
}

@Preview
@Composable
fun NoteItemPreviewGrid() {
    INoteTheme {
    NoteItem(note = Note(content = "Aleksandra❤️\nTo the Queen of the console:\n" +
            "\n" +
            "Thank you for being an amazing person.\n" +
            "Thank you for always making sure that we are fine.\n" +
            "Thank you for your openness and carrying everyone along. \n" +
            "You made this work a lot easier and fun. For always looking and finding ways to help us improve, thank you.  \n" +
            "I wish you all the best my friend ❤️", folderId = UUID.randomUUID()), folderName = "Notes", inGrid = true)
    }
}

@Preview
@Composable
fun NoteItemPreviewDarkGrid() {
    INoteTheme(darkTheme = true) {
        NoteItem(note = Note(content = "Aleksandra❤️\nTo the Queen of the console:\n" +
                "\n" +
                "Thank you for being an amazing person.\n" +
                "Thank you for always making sure that we are fine.\n" +
                "Thank you for your openness and carrying everyone along. \n" +
                "You made this work a lot easier and fun. For always looking and finding ways to help us improve, thank you.  \n" +
                "I wish you all the best my friend ❤️", folderId = UUID.randomUUID()), folderName = "Business", inGrid = true)
    }
}