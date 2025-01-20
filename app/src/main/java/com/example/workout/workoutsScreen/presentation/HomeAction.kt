package com.example.workout.workoutsScreen.presentation

sealed interface HomeAction {
    data object StartEdit : HomeAction
    data object EndEdit : HomeAction
    data object CancelEdit : HomeAction
}