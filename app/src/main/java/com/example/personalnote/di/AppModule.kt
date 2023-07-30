package com.example.personalnote.di

import android.app.Application
import androidx.room.Room
import com.example.personalnote.feature_note.data.data_source.NoteDatabase
import com.example.personalnote.feature_note.data.repository.NoteRepositoryImpl
import com.example.personalnote.feature_note.domain.repository.NoteRepository
import com.example.personalnote.feature_note.domain.use_case.AddNoteUseCase
import com.example.personalnote.feature_note.domain.use_case.DeleteNoteUseCase
import com.example.personalnote.feature_note.domain.use_case.GetNote
import com.example.personalnote.feature_note.domain.use_case.GetNotesUseCase
import com.example.personalnote.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase{
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    //we can use NoteDao to get the data but instead we use NoteDatabase to get its corresponding Dao
    fun provideNoteRepository(db:NoteDatabase) :NoteRepository
    {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun providedNoteUseCases(repository: NoteRepository): NoteUseCases{
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
           deleteNoteUseCase = DeleteNoteUseCase(repository),
            addNoteUseCase = AddNoteUseCase(repository),
            getNote = GetNote(repository)

        )
    }
}