package com.example.workout.workoutsScreen.domain.model

data class Workout(
    val id: Int,
    val name: String,
    val icon: String,
    val exercises: List<Exercise> = emptyList()
)