package com.example.workout.exercises.presentation

sealed interface ExerciseAction {
    data object StartEdit : ExerciseAction
    data object EndEdit : ExerciseAction
    data object CancelEdit : ExerciseAction
}