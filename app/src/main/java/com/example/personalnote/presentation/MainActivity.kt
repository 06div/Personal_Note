package com.example.personalnote.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.personalnote.presentation.add_edit_note.AddEditNoteScreen
import com.example.personalnote.presentation.notes.NotesScreen
import com.example.personalnote.presentation.util.Screen
import com.example.personalnote.ui.theme.PersonalNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            PersonalNoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    val navController = rememberNavController()
                    NavHost(navController = navController,
                        startDestination = Screen.NotesScreen.route
                    ){
                        composable(route= Screen.NotesScreen.route){
                            NotesScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditeNoteScreen.route +
                                    "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument(
                                    name  = "noteId"
                                ){
                                    type= NavType.IntType
                                    defaultValue= -1
                                },
                                navArgument(
                                    name  = "noteColor"
                                ){
                                    type= NavType.IntType
                                    defaultValue= -1
                                },
                            )
                        ){
                            val color = it.arguments?.getInt("noteColor") ?: -1
                            AddEditNoteScreen(navController = navController, noteColor = color)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PersonalNoteTheme {
        Greeting("Android")
    }
}