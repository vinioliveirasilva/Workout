package com.example.workout.workoutsScreen.domain.model

sealed interface ExerciseStatus {
    data object ToDo : ExerciseStatus
    data object Doing : ExerciseStatus
    data object Completed : ExerciseStatus
}