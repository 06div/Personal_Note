package com.example.personalnote.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.personalnote.ui.theme.LightBlue
import com.example.personalnote.ui.theme.LigthGreen
import com.example.personalnote.ui.theme.Pink40
import com.example.personalnote.ui.theme.RedOrange
import com.example.personalnote.ui.theme.RedPink
import com.example.personalnote.ui.theme.Violet

@Entity
data class Note(
    val title:String,
    val content:String,
    val timestamp : Long,
    val color:Int,
    @PrimaryKey val id: Int ?= null
){
    companion object{
         val noteColors = listOf(RedOrange, LigthGreen, Violet, LightBlue, RedPink, Pink40)
    }
}

class InvalidNoteException(message : String): Exception(message)