package com.example.inote.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inote.models.Note
import com.example.inote.models.NotesFolder
import com.example.inote.repositories.NotesRepository
import java.util.*

class FoldersViewModel(private val notesRepository: NotesRepository): ViewModel() {
    private var _folders = MutableLiveData(notesRepository.getFolders())
    val folders: LiveData<List<NotesFolder>> = _folders

    fun createFolder(name: String): NotesFolder {
        val newFolder = notesRepository.createFolder(name)
        _folders.value = notesRepository.getFolders()
        return  newFolder
    }
//    delete folder, edit
}