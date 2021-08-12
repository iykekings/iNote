package com.example.inote.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inote.models.Note
import com.example.inote.models.NotesFolder
import com.example.inote.repositories.NotesRepository
import java.util.*

class NotesViewModel(private val notesRepository: NotesRepository): ViewModel() {
    private var _notes = MutableLiveData(notesRepository.getNotes())
    private var _folders = MutableLiveData(notesRepository.getFolders())
    val notes: LiveData<List<Note>> = _notes
    val folders: LiveData<List<NotesFolder>> = _folders

    fun createNote(note: Note): Note {
        val newNote  = notesRepository.createNote(note)
        _notes.value = notesRepository.getNotes()
        _folders.value = notesRepository.getFolders()
        return newNote
    }

    fun createFolder(name: String): NotesFolder {
        val newFolder = notesRepository.createFolder(name)
        _folders.value = notesRepository.getFolders()
        return  newFolder
    }

    fun editNote(noteId: UUID, payload: Note) {
        notesRepository.editNote(noteId, payload)
        _notes.value = notesRepository.getNotes()
    }


    fun getNotesByFolderId(folderId: UUID): List<Note> {
        return notes.value?.filter { it.folderId == folderId } ?: emptyList()
    }
    fun getFolderNotesCount(folderId: UUID): Int {
        return notes.value?.filter { it.folderId == folderId }?.size ?: 0
    }

    fun getNoteById(id: UUID): Note? {
        return _notes.value?.find { it.uuid == id }
    }
}