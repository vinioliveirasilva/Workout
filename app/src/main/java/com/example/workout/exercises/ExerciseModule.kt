package com.example.workout.exercises

import com.example.workout.exercises.presentation.ExerciseViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val ExerciseModule = module {
    viewModelOf(::ExerciseViewModel)
}