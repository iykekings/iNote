package com.example.inote.repositories

import androidx.lifecycle.LiveData
import com.example.inote.models.Note
import com.example.inote.models.NotesFolder
import java.util.*

interface NotesRepository {
    fun createFolder(name: String): NotesFolder
    fun createNote(note: Note): Note
    fun editNote(noteId: UUID, payload: Note)
    fun deleteNote(noteId: UUID)
    fun getNotesByFolderId(folderId: UUID): List<Note>
    fun getNoteById(id: UUID): Note?
    fun getFolders(): List<NotesFolder>
    fun getNotes(): List<Note>
}