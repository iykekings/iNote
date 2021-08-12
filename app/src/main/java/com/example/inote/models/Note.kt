package com.example.inote.models

import java.util.*

data class Note(
    val uuid: UUID = UUID.randomUUID(),
    val content: String,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
    val pinned: Boolean = false,
    val folderId: UUID
) {
    private val lines = content.lines()
    val title = lines.first()
    val body = lines.subList(1, lines.size).joinToString("\n")
}
