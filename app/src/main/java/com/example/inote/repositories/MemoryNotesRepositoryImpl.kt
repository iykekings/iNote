package com.example.inote.repositories

import com.example.inote.models.Note
import com.example.inote.models.NotesFolder
import java.util.*

val TESTFOLDER = NotesFolder(name = "Standup", count = 3)
val TESTNOTE = Note(
    content = "Aleksandra🔥️\nTo the Queen of the console:\n" +
            "\n" +
            "Thank you for being an amazing person.\n" +
            "Thank you for always making sure that we are fine.\n" +
            "Thank you for your openness and carrying everyone along. \n" +
            "You made this work a lot easier and fun. For always looking and finding ways to help us improve, thank you.  \n" +
            "I wish you all the best my friend ❤️", folderId = TESTFOLDER.uuid
)

class MemoryNotesRepositoryImpl : NotesRepository {
    private var folders: MutableList<NotesFolder> = mutableListOf(
        TESTFOLDER,
        NotesFolder(name = "Business", count = 4)
    )
    private var notes: MutableList<Note> = mutableListOf(
        TESTNOTE,
        Note(
            content = "You gave me Freedom\nI was leaving a lie\n" +
                    "\n" +
                    "Thank you for being an amazing person.\n" +
                    "Thank you for always making sure that we are fine.\n" +
                    "Thank you for your openness and carrying everyone along. \n" +
                    "You made this work a lot easier and fun. For always looking and finding ways to help us improve, thank you.  \n" +
                    "I wish you all the best my friend ❤️", folderId = TESTFOLDER.uuid
        ),
        Note(
            content = "Cooking List🥰\nBread, Floor, Maggi, Meat\n" +
                    "\n" +
                    "Thank you for being an amazing person.\n" +
                    "Thank you for always making sure that we are fine.\n" +
                    "Thank you for your openness and carrying everyone along. \n" +
                    "You made this work a lot easier and fun. For always looking and finding ways to help us improve, thank you.  \n" +
                    "I wish you all the best my friend ❤️", folderId = TESTFOLDER.uuid,
            pinned = true
        )
    )

    override fun createFolder(name: String): NotesFolder {
        val folder = NotesFolder(name = name)
        folders.add(folder)
        return folder
    }

    override fun createNote(note: Note): Note {
        notes.add(note)
        folders.find { it.uuid == note.folderId }?.let {
            it.count++
        }
        return note
    }

    override fun editNote(noteId: UUID, payload: Note) {
        notes = notes.map { if (it.uuid == noteId) payload else it }.toMutableList()
    }

    override fun deleteNote(noteId: UUID) {
        notes = notes.filter { it.uuid != noteId }.toMutableList()
    }


    override fun getNotesByFolderId(folderId: UUID): List<Note> {
        return notes.filter { it.folderId == folderId }
    }

    override fun getNoteById(id: UUID): Note? {
        return notes.find { it.uuid == id }
    }

    override fun getNotes(): List<Note> {
        return notes
    }

    override fun getFolders(): List<NotesFolder> {
        return folders
    }
}