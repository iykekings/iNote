package com.example.inote.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inote.models.NotesFolder
import com.example.inote.ui.theme.INoteTheme

@Composable
fun FolderItem(folder: NotesFolder, onClick: (folderId: String) -> Unit = {}, isLast: Boolean = false) {
    Column(modifier = Modifier.clickable(enabled = true, onClick = {onClick(folder.uuid.toString())})) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Icon(
                Icons.Outlined.Folder,
                contentDescription = "folder",
                tint = MaterialTheme.colors.secondary
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = folder.name, color = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.weight(0.9f))
            Text(text = folder.count.toString(), color = Color.Gray)
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                Icons.Outlined.ArrowForwardIos,
                contentDescription = "folder",
                tint = Color.Gray.copy(alpha = 0.5f),
                modifier = Modifier.size(14.dp)
            )
        }
        if(!isLast) {
            HR()
        }
    }
}

@Preview()
@Composable
fun FolderPreview() {
    INoteTheme {
        Surface {
            FolderItem(NotesFolder(name = "Notes", count = 7))
        }
    }
}

@Preview()
@Composable
fun FolderPreviewDarkMode() {
    INoteTheme(darkTheme = true) {
        Surface {
            FolderItem(NotesFolder(name = "Notes", count = 7))
        }
    }
}