package com.example.personalnote.feature_note.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.personalnote.feature_note.domain.model.Note
import java.util.concurrent.Flow

@Dao
interface NoteDao {

    //Select all notes from the database
    @Query("Select * From note")
    fun getNotes(): kotlinx.coroutines.flow.Flow<List<Note>>

    //To select the specific note
    @Query("Select * From note Where id = :id")
    suspend fun getNoteById(id:Int):Note

    //If we call or insert the note with existing id we don't need to make update function it'll do that
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note:Note)

    //To delete the note
    @Delete
    suspend fun deleteNote(note:Note)
}