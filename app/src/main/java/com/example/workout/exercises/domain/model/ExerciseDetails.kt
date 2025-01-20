package com.example.workout.exercises.domain.model

data class ExerciseDetails(
    val id: Int,
    val name: String,
    val repetitions: Int,
    val restTime: Int,
    val load: Double,
)