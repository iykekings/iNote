package com.example.inote.models

import java.util.*

data class NotesFolder(val uuid: UUID = UUID.randomUUID(), val name: String, var count: Int = 0)