package com.example.workout.workoutsScreen

import com.example.workout.workoutsScreen.presentation.HomeViewModel
import com.example.workout.workoutsScreen.domain.repository.WorkoutRepository
import com.example.workout.workoutsScreen.data.source.WorkoutStorage
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val HomeModule = module {
    viewModelOf(::HomeViewModel)
    factoryOf(::WorkoutRepository)
    factoryOf(::WorkoutStorage)
}