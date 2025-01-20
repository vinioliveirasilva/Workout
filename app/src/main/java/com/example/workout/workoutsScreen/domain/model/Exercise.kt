package com.example.workout.workoutsScreen.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Exercise(
    val id: Int,
    val name: String,
    val icon: String,
    //@Transient
   // val status: ExerciseStatus = ExerciseStatus.ToDo
)