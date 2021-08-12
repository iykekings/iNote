package com.example.inote.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.inote.models.Note
import com.example.inote.ui.theme.INoteTheme
import java.util.*

class TextTransformation() : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            buildAnnotatedStringWithColors(text.toString()),
            OffsetMapping.Identity
        )
    }
}

fun buildAnnotatedStringWithColors(text: String): AnnotatedString {
    return buildAnnotatedString {
        val lines = text.lines()
        withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, fontSize = 24.sp)) {
            append(lines.first())
        }
        withStyle(style = SpanStyle(fontSize = 18.sp)) {
            append("\n")
            append(lines.subList(1, lines.size).joinToString("\n"))
            append("\n")
        }
    }
}

@Composable
fun Note(note: Note) {
    val content = rememberSaveable { mutableStateOf(note.content) }
    Column {
        BasicTextField(
            value = content.value,
            onValueChange = { content.value = it },
            visualTransformation = TextTransformation(),
            textStyle = TextStyle.Default.copy(color = MaterialTheme.colors.primary),
            cursorBrush = SolidColor(MaterialTheme.colors.primary)
        )
    }
}

@Preview
@Composable
fun NotePreview() {
    INoteTheme {
        Surface {

            Note(
                Note(
                    content = "Aleksandra🔥️\nTo the Queen of the console:\n" +
                            "\n" +
                            "Thank you for being an amazing person.\n" +
                            "Thank you for always making sure that we are fine.\n" +
                            "Thank you for your openness and carrying everyone along. \n" +
                            "You made this work a lot easier and fun. For always looking and finding ways to help us improve, thank you.  \n" +
                            "I wish you all the best my friend ❤️", folderId = UUID.randomUUID()
                )
            )
        }
    }
}

@Preview
@Composable
fun NotePreviewDark() {
    INoteTheme(darkTheme = true) {
        Surface {
            Note(
                Note(
                    content = "Aleksandra🔥️\nTo the Queen of the console:\n" +
                            "\n" +
                            "Thank you for being an amazing person.\n" +
                            "Thank you for always making sure that we are fine.\n" +
                            "Thank you for your openness and carrying everyone along. \n" +
                            "You made this work a lot easier and fun. For always looking and finding ways to help us improve, thank you.  \n" +
                            "I wish you all the best my friend ❤️", folderId = UUID.randomUUID()
                )
            )
        }
    }
}