package com.example.personalnote.feature_note.domain.repository

import com.example.personalnote.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

//To make testing easy we made repository in domain
// so that test cases can be run on them or we can make fake repository
interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id : Int): Note

    suspend fun insertNote(note:Note)

    suspend fun deleteNote(note:Note)
}