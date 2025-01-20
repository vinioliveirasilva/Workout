package com.example.workout

import com.example.workout.addWorkoutScreen.presentation.AddWorkoutViewModel
import com.example.workout.common.storage.StorageModule
import com.example.workout.exercises.ExerciseModule
import com.example.workout.workoutsScreen.HomeModule
import com.google.gson.Gson
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val FeatureModules = module {
    single<Gson> { Gson() }
    viewModelOf(::AddWorkoutViewModel)
    includes(
        StorageModule,
        HomeModule,
        ExerciseModule
    )
}