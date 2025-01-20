package com.example.workout.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.workout.addWorkoutScreen.presentation.AddWorkoutScreen
import com.example.workout.exercises.presentation.ExerciseScreen
import com.example.workout.workoutsScreen.presentation.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Routes.Home) {
                composable<Routes.Home> {
                    HomeScreen(
                        onWorkoutAction = { id ->
                            navController.navigate(Routes.Exercises(id))
                        },
                        doOnAddWorkoutAction = {
                            navController.navigate(Routes.AddWorkout)
                        }
                    )
                }

                composable<Routes.Exercises> { args ->
                    val routeArgs = args.toRoute<Routes.Exercises>()
                    ExerciseScreen(routeArgs.id) { id ->
                        navController.navigate(Routes.Exercise(id))
                    }
                }

                composable<Routes.Exercise> { args ->
                    val routeArgs = args.toRoute<Routes.Exercise>()
                    Surface(modifier = Modifier.fillMaxSize()) {
                        Text(text = "TODO")
                    }
                }

                composable<Routes.AddWorkout> {
                    AddWorkoutScreen {
                        navController.navigateUp()
                    }
                }
            }
        }
    }
}