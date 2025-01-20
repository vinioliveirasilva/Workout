package com.example.workout.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object Home

    @Serializable
    object AddWorkout

    @Serializable
    data class Exercises(val id: Int)

    @Serializable
    data class Exercise(val id: Int)
}